package com.toy.plany.batch;


import static com.toy.plany.entity.QSchedule.schedule;
import com.toy.plany.entity.Schedule;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.querydsl.reader.QuerydslNoOffsetPagingItemReader;
import org.springframework.batch.item.querydsl.reader.expression.Expression;
import org.springframework.batch.item.querydsl.reader.options.QuerydslNoOffsetNumberOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class ReminderJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EntityManagerFactory entityManagerFactory;


    private String readerJob = "REMINDER_JOB";
    private String readerJobStep = readerJob + "_STEP";
    private int chunkSize = 10;

    @Bean
    public Job reminderJob() {
        Job reminderJob = jobBuilderFactory.get(readerJob)
                .start(reminderStep())
                .build();

        return reminderJob;
    }

    @Bean
    @JobScope
    public Step reminderStep() {
        return stepBuilderFactory.get(readerJobStep)
                .startLimit(3)
                .<Schedule, Schedule>chunk(chunkSize)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Schedule> reader() {
        return new JpaPagingItemReaderBuilder<Schedule>()
                .queryString("SELECT s FROM Schedule s")
                .pageSize(chunkSize)
                .entityManagerFactory(entityManagerFactory)
                .name("reminderPagingReader")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Schedule, Schedule> processor() {

        return schedule -> {

            //TODO slack api 메세지 보내기
            System.out.println("실행됨");

            return schedule;
        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<Schedule> writer() {
        return new JpaItemWriterBuilder<Schedule>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
