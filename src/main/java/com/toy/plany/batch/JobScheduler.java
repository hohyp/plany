//package com.toy.plany.batch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobScheduler {
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job reminderJob;
//
//    @Scheduled(cron = "1 * * * * *")
//    public void jobSchduled() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
//            JobRestartException, JobInstanceAlreadyCompleteException {
//
//        Map<String, JobParameter> jobParametersMap = new HashMap<>();
//
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        Date time = new Date();
//
//        String time1 = format1.format(time);
//
//        jobParametersMap.put("date",new JobParameter(time1));
//
//        JobParameters parameters = new JobParameters(jobParametersMap);
//
//        JobExecution jobExecution = jobLauncher.run(reminderJob, parameters);
//
//        while (jobExecution.isRunning()) {
//            log.info("...");
//        }
//
//        log.info("Job Execution: " + jobExecution.getStatus());
//        log.info("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
//        log.info("Job getJobId: " + jobExecution.getJobId());
//        log.info("Job getExitStatus: " + jobExecution.getExitStatus());
//        log.info("Job getJobInstance: " + jobExecution.getJobInstance());
//        log.info("Job getStepExecutions: " + jobExecution.getStepExecutions());
//        log.info("Job getLastUpdated: " + jobExecution.getLastUpdated());
//        log.info("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
//
//    }
//}
