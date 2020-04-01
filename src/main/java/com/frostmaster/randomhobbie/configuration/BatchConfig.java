package com.frostmaster.randomhobbie.configuration;

import com.frostmaster.randomhobbie.domain.Hobby;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@RequiredArgsConstructor
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Value("classPath:/input/hobbies.csv")
  private Resource inputResource;

  @Bean
  public Job readCSVFileJob() {
    return jobBuilderFactory
        .get("readCSVFileJob")
        .incrementer(new RunIdIncrementer())
        .start(step())
        .build();
  }

  @Bean
  public Step step() {
    return stepBuilderFactory
        .get("step")
        .<Hobby, Hobby>chunk(5)
        .reader(reader())
        .processor(processor())
        .writer(writer())
        .build();
  }

  @Bean
  public ItemProcessor<Hobby, Hobby> processor() {
    return new DBLogProcessor();
  }

  @Bean
  public FlatFileItemReader<Hobby> reader() {
    FlatFileItemReader<Hobby> itemReader = new FlatFileItemReader<Hobby>();
    itemReader.setLineMapper(lineMapper());
    itemReader.setLinesToSkip(1);
    itemReader.setResource(inputResource);
    return itemReader;
  }

  @Bean
  public LineMapper<Hobby> lineMapper() {
    DefaultLineMapper<Hobby> lineMapper = new DefaultLineMapper<Hobby>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setNames(new String[] { "id", "firstName", "lastName" });
    lineTokenizer.setIncludedFields(new int[] { 0, 1, 2 });
    BeanWrapperFieldSetMapper<Hobby> fieldSetMapper = new BeanWrapperFieldSetMapper<Hobby>();
    fieldSetMapper.setTargetType(Hobby.class);
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }

  @Bean
  public JdbcBatchItemWriter<Hobby> writer() {
    JdbcBatchItemWriter<Hobby> itemWriter = new JdbcBatchItemWriter<Hobby>();
    itemWriter.setDataSource(dataSource());
    itemWriter.setSql("INSERT INTO EMPLOYEE (ID, FIRSTNAME, LASTNAME) VALUES (:id, :firstName, :lastName)");
    itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Hobby>());
    return itemWriter;
  }

  @Bean
  public EmbeddedDatabase dataSource(){
    EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
    return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
        .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
        .addScript("classpath:employee.sql")
        .setType(EmbeddedDatabaseType.H2)
        .build();
  }

}
