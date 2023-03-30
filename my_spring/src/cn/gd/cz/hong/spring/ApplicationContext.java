package cn.gd.cz.hong.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
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
                    String scope = Optional.ofNullable(scopeAnnotation).map(Scope::value).orElse("singleton");

                    String beanName = componentAnnotation.value();
                    if ("".equals(beanName)) {
                        beanName = Introspector.decapitalize(clazz.getSimpleName());
                    }
                    beanDefinitionMap.put(beanName, new BeanDefinition(clazz, scope));
                }
            }

            // 单例的bean首先被创建
            beanDefinitionMap.forEach((beanName, beanDefinition) -> {
                if ("singleton".equals(beanDefinition.getScope())) {
                    beanMap.put(beanName, createBean(beanName, beanDefinition));
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
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object result = null;
        Class clazz = beanDefinition.getClazz();
        try {
            result = clazz.getConstructor().newInstance();
            // 创建的时候注入 @Autowired
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    declaredField.setAccessible(true);
                    declaredField.set(result, getBean(declaredField.getName()));
                }
            }

            // Aware实现类 回调注入容器中的对象
            if (result instanceof BeanNameAware) {
                // 原本这个方法是没有 beanName 的, 向上层索取了属于是
                ((BeanNameAware) result).setBeanName(beanName);
            }

            // 初始化
            if (result instanceof InitializingBean) {
                // 执行用户自定义的初始化
                ((InitializingBean) result).afterPropertiesSet();
            }

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
                // 这里判断的原因是解决依赖过程中 可能需要依赖的对象还没创建, 原来在这里创建
                if (obj == null) {
                    obj = createBean(beanName, beanDefinition);
                    beanMap.put(beanName, obj);
                }
                return obj;
            }
            // 多例需要重新创建
            return createBean(beanName, definition);
        }).get();
        return bean;
    }
}
