package cn.gd.cz.hong.springbootlearn.service.impl;

import cn.gd.cz.hong.springbootlearn.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用使用  @Async
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async("asyncPoolTaskExecutor")
    @Override
    public void invoke() {
        LOGGER.info("here is async: {}", Thread.currentThread().getName());
    }
}
