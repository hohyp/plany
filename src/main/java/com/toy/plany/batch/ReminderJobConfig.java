package com.toy.plany.batch;


import static com.toy.plany.entity.QSchedule.schedule;

import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.enums.AlarmStatus;
import com.toy.plany.service.EventService;
import com.toy.plany.service.SendAlarmService;
import org.apache.tomcat.jni.Local;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.querydsl.reader.QuerydslNoOffsetPagingItemReader;
import org.springframework.batch.item.querydsl.reader.QuerydslPagingItemReader;
import org.springframework.batch.item.querydsl.reader.expression.Expression;
import org.springframework.batch.item.querydsl.reader.options.QuerydslNoOffsetNumberOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class ReminderJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EntityManagerFactory entityManagerFactory;

    @Autowired
    public SendAlarmService sendAlarmService;


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
    public Step reminderStep() {
        return stepBuilderFactory.get(readerJobStep)
                .startLimit(3)
                .<Schedule, Schedule>chunk(chunkSize)
                .reader(reader(null))
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public QuerydslPagingItemReader<Schedule> reader(@Value("#{jobParameters[date]}") String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        // 1. No Offset Option
        QuerydslNoOffsetNumberOptions<Schedule, Long> options =
                new QuerydslNoOffsetNumberOptions<>(schedule.id, Expression.ASC);

        // 2. Querydsl Reader
        return new QuerydslNoOffsetPagingItemReader<>(entityManagerFactory, chunkSize, options, queryFactory -> queryFactory
                .selectFrom(schedule)
                .where(schedule.remindTime.eq(dateTime)
                        .and(schedule.status.eq(AlarmStatus.CREATED))));

    }


    @Bean
    @StepScope
    public ItemProcessor<Schedule, Schedule> processor() {
        return item -> {
            sendAlarmService.sendAlarm(item, AlarmStatus.REMINDED);
            item.updateStatus(AlarmStatus.REMINDED);
            return item;
        };
    }


    @Bean
    public JpaItemWriter<Schedule> writer() {
        return new JpaItemWriterBuilder<Schedule>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
