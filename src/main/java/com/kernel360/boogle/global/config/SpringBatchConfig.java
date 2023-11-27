package com.kernel360.boogle.global.config;

import com.kernel360.boogle.global.batch.MailForNewRelease;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MailForNewRelease mailForNewRelease;

    @Bean("monthlyMailing")
    public Job monthlyMailing(Step monthlyMailingStep) {
        return jobBuilderFactory.get("monthlyMailing")
                .incrementer(new RunIdIncrementer())
                .start(monthlyMailingStep)
                .build();
    }

    @JobScope
    @Bean("monthlyMailingStep")
    public Step monthlyMailingStep(Tasklet tasklet) {
        return stepBuilderFactory.get("monthlyMailingStep")
                .tasklet(tasklet)
                .build();
    }

    @StepScope
    @Bean
    public Tasklet tasklet() {
        return ((contribution, chunkContext) -> {
            // 메일링 서비스를 수행하시려면 다음의 코드를 삽입하세요.
            // mailForNewRelease.send();
            // boogle.kernel360@gmail.com 메일은 삭제됩니다.
            System.out.println("Spring Batch Job Done!");
            return RepeatStatus.FINISHED;
        });
    }
}
