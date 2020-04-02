package com.frostmaster.randomhobbie.resource;

import com.frostmaster.randomhobbie.domain.Hobby;
import com.frostmaster.randomhobbie.service.HobbyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hobby/")
@RequiredArgsConstructor
public class HobbyController {

  private final HobbyService hobbyService;

  @GetMapping
  public List<Hobby> getAllHobbies() {
    return hobbyService.getAllHobbies();
  }

  @GetMapping
  public Hobby getRandomHobby() {
    return hobbyService.getRandomHobby();
  }

}
