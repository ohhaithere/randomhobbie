package com.frostmaster.randomhobbie.service.impl;

import com.frostmaster.randomhobbie.domain.Hobby;
import com.frostmaster.randomhobbie.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService {

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
}
