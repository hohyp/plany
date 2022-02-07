//package com.toy.plany.batch;
//
//import static com.toy.plany.entity.QReminder.reminder;
//
//import com.toy.plany.entity.Reminder;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.*;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
//import org.springframework.batch.item.querydsl.reader.QuerydslNoOffsetPagingItemReader;
//import org.springframework.batch.item.querydsl.reader.expression.Expression;
//import org.springframework.batch.item.querydsl.reader.options.QuerydslNoOffsetNumberOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManagerFactory;
//
//@Configuration
//@EnableBatchProcessing
//public class ReminderJobConfig {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public EntityManagerFactory entityManagerFactory;
//
//
//    private String readerJob = "REMINDER_JOB";
//    private String readerJobStep = readerJob + "_STEP";
//    private int chunkSize = 10;
//
//    @Bean
//    public Job reminderJob() {
//        Job reminderJob = jobBuilderFactory.get(readerJob)
//                .start(reminderStep())
//                .build();
//
//        return reminderJob;
//    }
//
//    @Bean
//    @JobScope
//    public Step reminderStep() {
//        return stepBuilderFactory.get(readerJobStep)
//                .startLimit(3)
//                .<Reminder, Reminder>chunk(chunkSize)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public QuerydslNoOffsetPagingItemReader<Reminder> reader() {
//        QuerydslNoOffsetNumberOptions<Reminder, Long> options =
//                new QuerydslNoOffsetNumberOptions<>(reminder.id, Expression.ASC);
//        return new QuerydslNoOffsetPagingItemReader<>(entityManagerFactory, chunkSize, options, jpaQueryFactory -> jpaQueryFactory
//                .selectFrom(reminder)
//                .where(reminder.id.eq(1L))
//        );
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<Reminder, Reminder> processor() {
//
//        return reminder -> {
//
//            //TODO slack api 메세지 보내기
//
//            return reminder;
//        };
//    }
//
//    @Bean
//    @StepScope
//    public JpaItemWriter<Reminder> writer() {
//        return new JpaItemWriterBuilder<Reminder>()
//                .entityManagerFactory(entityManagerFactory)
//                .build();
//    }
//}
