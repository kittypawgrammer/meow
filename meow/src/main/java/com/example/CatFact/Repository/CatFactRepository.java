package com.example.CatFact.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CatFact.Entity.CatFacts;

public interface CatFactRepository extends JpaRepository<CatFacts, Long> {
    Optional<CatFacts> findByText(String text);
}
