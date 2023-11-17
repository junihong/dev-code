package com.tamsil.springbootsqlinit;

import com.tamsil.springbootsqlinit.job.Job;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Map;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootSqlInitApplication implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public SpringbootSqlInitApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSqlInitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Job> jobTypeBean = applicationContext.getBeansOfType(Job.class);
        jobTypeBean.keySet().forEach(key -> jobTypeBean.get(key).execute());
    }
}
