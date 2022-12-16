package com.ll.re_batch.job.helloWorld;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloWorldJobConfig {
    private final JobBuilderFactory jobBuilderFactory; // Job Builder

    private final StepBuilderFactory stepBuilderFactory;

    // 하나의 Job 생성 -> 1job에는 다수의 step
    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .start(helloWorldStep1())
                .build();
    }

    // Job에 들어갈 step 생성
    @Bean
    public Step helloWorldStep1() {
        return stepBuilderFactory.get("helloWorldStep1")
                .tasklet(helloWorldTasklet())
                .build();
    }

    @Bean
    public Tasklet helloWorldTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("헬로월드!");

            return RepeatStatus.FINISHED;
        };
    }
}