package com.frostmaster.randomhobbie.service;

import com.frostmaster.randomhobbie.domain.Hobby;
import com.frostmaster.randomhobbie.dto.HobbyDto;
import java.util.List;

public interface HobbyService {

  HobbyDto getRandomHobby();
  Hobby getHobby(Long id);
  Hobby saveHobby(Hobby hobby);
  List<Hobby> getAllHobbies();

}
