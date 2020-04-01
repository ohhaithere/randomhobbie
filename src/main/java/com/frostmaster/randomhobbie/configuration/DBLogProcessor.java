package com.frostmaster.randomhobbie.configuration;

import com.frostmaster.randomhobbie.domain.Hobby;
import org.springframework.batch.item.ItemProcessor;

public class DBLogProcessor implements ItemProcessor<Hobby, Hobby>
{
  public Hobby process(Hobby hobby) throws Exception
  {
    System.out.println("Inserting hobby : " + hobby);
    return hobby;
  }
}
