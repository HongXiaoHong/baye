package cn.gd.cz.hong.springbootlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步调用配置
 * EnableAsync 允许在调用方法的时候使用异步
 * 这里先试一下 没有配置直接调用是个什么情况
 * 没有配置前 使用的是同一个进程
 * 21:19:13.694 [http-nio-9000-exec-1] [INFO ] cn.gd.cz.hong.springbootlearn.controller.AsyncController:85 --- here is controller ：http-nio-9000-exec-1
 * 21:19:13.694 [http-nio-9000-exec-1] [INFO ] cn.gd.cz.hong.springbootlearn.service.impl.AsyncServiceImpl:19 --- here is async: http-nio-9000-exec-1
 * <p>
 * 配置了之后 便是用了两个进程进行处理
 * 21:20:47.055 [http-nio-9000-exec-1] [INFO ] cn.gd.cz.hong.springbootlearn.controller.AsyncController:85 --- here is controller ：http-nio-9000-exec-1
 * 21:20:47.057 [task-1] [INFO ] cn.gd.cz.hong.springbootlearn.service.impl.AsyncServiceImpl:19 --- here is async: task-1
 * <p>
 * 第二次就是
 * 21:23:03.791 [http-nio-9000-exec-4] [INFO ] cn.gd.cz.hong.springbootlearn.controller.AsyncController:85 --- here is controller ：http-nio-9000-exec-4
 * 21:23:03.792 [task-2] [INFO ] cn.gd.cz.hong.springbootlearn.service.impl.AsyncServiceImpl:19 --- here is async: task-2
 * <p>
 * 看到这里进程已经变为2 也就是没有复用 每一次都是新建一个进程给我用
 * <p>
 * 这里配置一个线程池给进程使用 看到线程已经得到了复用 额~ 满意
 * 21:29:47.620 [http-nio-9000-exec-10] [INFO ] cn.gd.cz.hong.springbootlearn.controller.AsyncController:85 --- here is controller ：http-nio-9000-exec-10
 * 21:29:47.620 [hong-20] [INFO ] cn.gd.cz.hong.springbootlearn.service.impl.AsyncServiceImpl:19 --- here is async: hong-20
 * 21:29:48.254 [http-nio-9000-exec-1] [INFO ] cn.gd.cz.hong.springbootlearn.controller.AsyncController:85 --- here is controller ：http-nio-9000-exec-1
 * 21:29:48.254 [hong-1] [INFO ] cn.gd.cz.hong.springbootlearn.service.impl.AsyncServiceImpl:19 --- here is async: hong-1
 * <p>
 * 回调
 * 可以使用 Future
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.setKeepAliveSeconds(200);
        taskExecutor.setThreadNamePrefix("hong-");
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
