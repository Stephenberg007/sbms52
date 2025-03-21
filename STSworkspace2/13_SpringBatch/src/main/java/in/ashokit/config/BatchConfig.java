package in.ashokit.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import in.ashokit.Beans.PersonItemProcessor;
import in.ashokit.record.Person;

@Configuration
public class BatchConfig {// Here we want to read the Data
	
	
	
	@Bean
	public FlatFileItemReader<Person> reader(){
		
		return new FlatFileItemReaderBuilder<Person>()// Predefined class
				.name("personItemReader")
				.resource(new ClassPathResource("Data.csv"))
				.delimited()
				.names("firstName","lastName")
				.targetType(Person.class)//Storing the data as a Spring Bean
				.build();
	}
	
	@Bean
	public PersonItemProcessor person() {
		
		return new PersonItemProcessor();// we just made our class as a Spring Bean
	}
	
	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Person>()
				.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
				.dataSource(dataSource)
				.beanMapped()
				.build();
		
	}
	
	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			FlatFileItemReader<Person> reader, PersonItemProcessor processor, JdbcBatchItemWriter<Person> writer) {
		return new StepBuilder("step1",jobRepository)
				.<Person,Person>chunk(3, transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1) {
		return new JobBuilder("importUserJob",jobRepository)
				.start(step1)
				.build();
	}
	
	
}
