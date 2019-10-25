package edu.mass.doe.cap.batch;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.JobContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * The Class AutoCloseConfig.
 */
@Configuration
public class AutoCloseConfig {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	
    @Autowired private StepBuilderFactory steps;


	/**
	 * Cycle reader.
	 *
	 * @return the cycle reader
	 * @throws Exception the exception
	 */
	@Bean
	public CycleReader cycleReader() throws Exception {
		return new CycleReader();
	}

	/**
	 * Cycle item processor.
	 *
	 * @return the cycle item processor
	 * @throws Exception the exception
	 */
	@Bean
	public CycleItemProcessor cycleItemProcessor() throws Exception {
		return new CycleItemProcessor();
	}

	/**
	 * Cycle item writer.
	 *
	 * @return the cycle item writer
	 * @throws Exception the exception
	 */
	@Bean
	public CycleItemWriter cycleItemWriter() throws Exception {
		return new CycleItemWriter();
	}
	
	 /**
 	 * Read lines.
 	 *
 	 * @return the step
 	 * @throws Exception the exception
 	 */
 	@Bean
	    protected Step readLines() throws Exception {
	        return steps
	          .get("readLines")
	          .tasklet(cycleReader())
	          .build();
	    }

	    /**
    	 * Process lines.
    	 *
    	 * @return the step
    	 * @throws Exception the exception
    	 */
    	@Bean
	    protected Step processLines() throws Exception {
	        return steps
	          .get("processLines")
	          .tasklet(cycleItemProcessor())
	          .build();
	    }

	    /**
    	 * Write lines.
    	 *
    	 * @return the step
    	 * @throws Exception the exception
    	 */
    	@Bean
	    protected Step writeLines() throws Exception {
	        return steps
	          .get("writeLines")
	          .tasklet(cycleItemWriter())
	          .build();
	    }
	    
	    /**
    	 * Auto notification job.
    	 *
    	 * @return the job
    	 * @throws Exception the exception
    	 */
    	@Bean(name="dailyNotificationJob")
		Job autoNotificationJob() throws Exception {
			return  jobBuilderFactory
					.get("dailyNotificationJob")
					.incrementer(new RunIdIncrementer())
					.flow(readLines())
					.next(processLines())
					.next(writeLines())				
					.end()
					.build();

		}

	/**
	 * Auto close job.
	 *
	 * @return the job
	 * @throws Exception the exception
	 */
	@Bean(name="dailyAutoCloseJob")
	Job autoCloseJob() throws Exception {
		return  jobBuilderFactory
				.get("dailyAutoCloseJob")
				.incrementer(new RunIdIncrementer())
				.flow(readLines())
				.next(processLines())
				.next(writeLines())				
				.end()
				.build();
	}
	
	/**
	 * Annual job.
	 *
	 * @return the job
	 * @throws Exception the exception
	 */
	@Bean(name="annualNotificationJob")
	Job annualJob() throws Exception {
		return  jobBuilderFactory
				.get("annualNotificationJob")
				.incrementer(new RunIdIncrementer())
				.flow(readLines())
				.next(processLines())
				.end()
				.build();

	}

}
