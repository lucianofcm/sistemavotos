package com.sistemavotos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
public class SchedulerConfig {
	@Value("${jobs.enabled:true}")
	  private boolean isEnabled;

	  @Scheduled(fixedDelay = 60000)
	  public void cleanTempDirectory() {
	    if(isEnabled) {
	      // do work here
	    }
	  }
}
