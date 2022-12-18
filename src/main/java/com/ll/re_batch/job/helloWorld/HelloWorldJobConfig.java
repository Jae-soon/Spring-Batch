package com.ll.re_batch.job.helloWorld;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
    @Bean // 공유자원 -> 해당 객체를 어디서든지 사용할 수 있음
    @JobScope // Bean, Session 등 각 영역마다 객체 생성
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                // Job이 성공하면 다시 실행되지 않는다. 하지만, increment로 다시 실행시킬 수 있다.
                .incrementer(new RunIdIncrementer()) // 강제로 매번 다른 ID를 실행시에 파라미터로 부여
                .start(helloWorldStep1())
                .build();
    }

    // Job에 들어갈 step 생성
    @Bean
    @StepScope
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