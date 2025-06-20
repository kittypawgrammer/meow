package com.example.CatFact.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CatFact.Entity.CatFacts;

@Repository
public interface CatFactRepository extends JpaRepository<CatFacts, Long> {
    Optional<CatFacts> findByText(String text);
}
