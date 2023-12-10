package me.hong.yi.overdue_payment_statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class OverduePaymentStatisticsApplication {

  public static void main(String[] args) {
    SpringApplication.run(OverduePaymentStatisticsApplication.class, args);
  }

  @Scheduled(cron = "${test.cron}")
  public void test() {
    System.out.println("test");
  }

}
