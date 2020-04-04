package com.frostmaster.randomhobbie.service.impl;

import com.frostmaster.randomhobbie.domain.Hobby;
import com.frostmaster.randomhobbie.dto.HobbyDto;
import com.frostmaster.randomhobbie.repository.HobbyRepository;
import com.frostmaster.randomhobbie.service.HobbyService;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService {

  private final HobbyRepository hobbyRepository;

  @Override
  public HobbyDto getRandomHobby() {
    Random rand = new Random();
    Integer randomInt = rand.nextInt(742);
    Hobby hobby = hobbyRepository.getOne(Long.valueOf(randomInt));
    HobbyDto hobbyDto = HobbyDto.builder()
        .name(hobby.getName()).build();
    return hobbyDto;
  }

  @Override
  public Hobby getHobby(Long id) {
    return null;
  }

  @Override
  public Hobby saveHobby(Hobby hobby) {
    return null;
  }

  @Override
  public List<Hobby> getAllHobbies() {
    return hobbyRepository.findAll();
  }
}
