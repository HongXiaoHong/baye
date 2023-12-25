package cn.hong.xxl.job.demo.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * 你好 elastic job
 */
@Component
public class HelloJob implements SimpleJob {

  @Override
  public void execute(ShardingContext shardingContext) {
    System.out.println("hello elastic job");
  }
}
