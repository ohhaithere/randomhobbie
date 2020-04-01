package com.frostmaster.randomhobbie.service;

import com.frostmaster.randomhobbie.domain.Hobby;

public interface HobbyService {

  Hobby getRandomHobby();
  Hobby getHobby(Long id);
  Hobby saveHobby(Hobby hobby);

}
