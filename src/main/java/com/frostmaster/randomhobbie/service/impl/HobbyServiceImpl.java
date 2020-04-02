package com.frostmaster.randomhobbie.service.impl;

import com.frostmaster.randomhobbie.domain.Hobby;
import com.frostmaster.randomhobbie.repository.HobbyRepository;
import com.frostmaster.randomhobbie.service.HobbyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService {

  private final HobbyRepository hobbyRepository;

  @Override
  public Hobby getRandomHobby() {
    return null;
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
