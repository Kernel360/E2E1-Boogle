package com.kernel360.boogle.global.batch;

import com.kernel360.boogle.global.config.SpringBatchConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SpringBatchScheduler {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private SpringBatchConfig springBatchConfig;
    @Scheduled(
            initialDelay = 1000 * 60 * 60,
            fixedDelay = 1000 * 60 * 60
    )
    public void runJob() {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);

        try {
            jobLauncher.run(
                    springBatchConfig.monthlyMailing(
                            springBatchConfig.monthlyMailingStep(
                                    springBatchConfig.tasklet()
                            )
                    ),
                    jobParameters
            );
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
            log.error(e.getMessage());
        }
    }
}
