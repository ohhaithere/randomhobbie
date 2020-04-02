package com.frostmaster.randomhobbie.service;

import com.frostmaster.randomhobbie.domain.Hobby;
import java.util.List;

public interface HobbyService {

  Hobby getRandomHobby();
  Hobby getHobby(Long id);
  Hobby saveHobby(Hobby hobby);
  List<Hobby> getAllHobbies();

}
