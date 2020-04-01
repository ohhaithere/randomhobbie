package com.frostmaster.randomhobbie.repository;

import com.frostmaster.randomhobbie.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
