package com.frostmaster.randomhobbie.writer;

import com.frostmaster.randomhobbie.domain.Hobby;
import com.frostmaster.randomhobbie.repository.HobbyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

@Slf4j
@RequiredArgsConstructor
public class HobbyWriter implements ItemWriter<Hobby> {

  private final HobbyRepository hobbyRepository;

  @Override
  public void write(List<? extends Hobby> list) throws Exception {
    log.info("Received the information of {} hobby", list.size());
    hobbyRepository.saveAll(list);
    list.forEach(i -> log.debug("Received the information of a hobby: {}", i));
  }

}
