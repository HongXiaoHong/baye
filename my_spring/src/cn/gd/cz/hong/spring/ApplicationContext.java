package cn.gd.cz.hong.spring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器
 */
public class ApplicationContext {

    /**
     * 模拟配置
     */
    Class configClazz;
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();


    public ApplicationContext(Class configClazz) {
        this.configClazz = configClazz;
        try {
            // 模拟容器启动
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() throws ClassNotFoundException {
        // 获取扫描路径
        ComponentScan componentScan = (ComponentScan) configClazz.getAnnotation(ComponentScan.class);
        String scanPath = componentScan.value();

        // 通过类加载器获取需要扫描文件的根路径, 并获取文件
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL beanUrl = classLoader.getResource(scanPath.replace('.', '/'));
        String absolutePath = beanUrl.getFile();
        File parentFile = new File(absolutePath);

        if (parentFile.isDirectory()) {
            // 遍历扫描路径下的文件 其实这里应该用递归了
            for (File subFile : parentFile.listFiles()) {
                // 用类加载器通过类的全限定名即包路径.类名加载类
                String subPath = subFile.getPath();
                Class<?> clazz = classLoader.loadClass(subPath.substring(subPath.indexOf("cn"), subPath.indexOf(".class")).replace('\\', '.'));
                // 是否属于容器管理 + 单例/多例...
                Component componentAnnotation = clazz.getAnnotation(Component.class);
                if (componentAnnotation != null) {
                    Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                    // 因为原型模式也就是多例的情况下, 每次从容器中获取都会重新创建对象
                    // 不能每次创建都去扫描类路径, 所以这里需要把类相关的信息封装起来存储起来, 放到一个映射中
                    String scope = Optional.of(scopeAnnotation).map(Scope::value).orElse("singleton");
                    beanDefinitionMap.put(componentAnnotation.value(), new BeanDefinition(clazz, scope));
                }
            }

            // 单例的bean首先被创建
            beanDefinitionMap.forEach((beanName, beanDefinition) -> {
                if ("singleton".equals(beanDefinition.getScope())) {
                    beanMap.put(beanName, createBean(beanDefinition));
                }
            });
        }

    }

    /**
     * 创建bean
     *
     * @param beanDefinition
     * @return
     */
    private Object createBean(BeanDefinition beanDefinition) {
        Object result = null;
        Class clazz = beanDefinition.getClazz();
        try {
            result = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 从容器中获取bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Object bean = Optional.of(beanDefinition).map(definition -> {
            if ("singleton".equals(definition.getScope())) {
                Object obj = beanMap.get(beanName);
                // 有可能懒加载, 容器加载时没有创建, 这里创建
                if (obj == null) {
                    obj = createBean(beanDefinition);
                    beanMap.put(beanName, obj);
                }
                return obj;
            }
            // 多例需要重新创建
            return createBean(definition);
        }).get();
        return bean;
    }
}
