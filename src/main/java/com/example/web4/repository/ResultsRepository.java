package com.example.web4.repository;

import com.example.web4.entity.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Long> {
    Results findPointById(Long id);
    List<Results> findPointByOwner(Long owner);

    void deleteByOwner(Long idOwn);
}