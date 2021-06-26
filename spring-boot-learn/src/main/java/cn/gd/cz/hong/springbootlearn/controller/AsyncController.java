package cn.gd.cz.hong.springbootlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步测试
 * java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
 */
@Controller
@RequestMapping("/async")
public class AsyncController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AsyncController.class);

    @RequestMapping("/req")
    public void req(HttpServletRequest request,
                    HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                LOGGER.info("complete");
                try {
                    Thread.sleep(100);
                    LOGGER.info("内部线程：{}", Thread.currentThread().getName());
                    asyncContext.getResponse().setCharacterEncoding("utf-8");
                    asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("这是【异步】的请求返回");
                } catch (Exception e) {
                    LOGGER.error("异常：", e);
                }
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                LOGGER.info("timeout");
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                LOGGER.info("error");
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                LOGGER.info("start");
            }
        });
    }
}
