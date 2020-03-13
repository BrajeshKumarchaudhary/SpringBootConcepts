package com.java.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.java.itemprocessor.UserItemProcessor;
import com.java.listner.JobCompletionListener;
import com.java.model.Employee;

@Configuration
@EnableBatchProcessing
public class BatchConfiguartion {

	@Autowired
	public JobBuilderFactory jobbuilderfactory;
	@Autowired
	public StepBuilderFactory stepbuilderfactory;
	@Autowired
	public DataSource datasource;

	@Bean
	public FlatFileItemReader<Employee> reader() {
		FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("User.csv"));
		reader.setLineMapper(new DefaultLineMapper<Employee>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "firstName", "lastName", "email", "age" });
					}
				}

				);
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
					{
						setTargetType(Employee.class);
					}
				});
			}
		}

		);
		return reader;

	}
	@Bean
	public UserItemProcessor processor()
	{
		return new UserItemProcessor();
	}
	@Bean
	public JdbcBatchItemWriter<Employee> writer()
	{
		JdbcBatchItemWriter<Employee> writer=new JdbcBatchItemWriter<>();
		try {
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
		writer.setSql("insert into Employee(first_name,last_name,email,age) values(:firstname,:lastname,:email,:age)");
	    writer.setDataSource(datasource);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	    return writer;
	}
	@Bean 
	public Job importUserJob(JobCompletionListener listener)
	{
		return jobbuilderfactory.get("importUserJob").incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1())
				.end()
				.build();
		
	}
	@Bean
	public Step step1()
	{
		return stepbuilderfactory.get("step1")
				.<Employee,Employee>chunk(1)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
}
