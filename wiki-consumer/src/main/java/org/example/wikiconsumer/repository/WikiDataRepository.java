package org.example.wikiconsumer.repository;

import org.example.wikiconsumer.entity.WikiData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiDataRepository extends JpaRepository<WikiData, Long> {
}
