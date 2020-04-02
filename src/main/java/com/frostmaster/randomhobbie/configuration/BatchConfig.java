package com.frostmaster.randomhobbie.configuration;

import com.frostmaster.randomhobbie.domain.Hobby;
import com.frostmaster.randomhobbie.repository.HobbyRepository;
import com.frostmaster.randomhobbie.writer.HobbyWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final HobbyRepository hobbyRepository;

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
        .writer(writer())
        .build();
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
    lineTokenizer.setNames(new String[] { "name"});
    lineTokenizer.setIncludedFields(new int[] { 0 });
    BeanWrapperFieldSetMapper<Hobby> fieldSetMapper = new BeanWrapperFieldSetMapper<Hobby>();
    fieldSetMapper.setTargetType(Hobby.class);
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }

  @Bean
  ItemWriter<Hobby> writer() {
    return new HobbyWriter(hobbyRepository);
  }

}
