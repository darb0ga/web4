package com.example.web4.service;

import com.example.web4.entity.Results;
import com.example.web4.repository.ResultsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResultService {
    private ResultsRepository pointRepo;

    @Autowired
    public void PointService(ResultsRepository pointRepository) {
        this.pointRepo = pointRepository;
    }

    public Results findById(Long id) {
        return pointRepo.findPointById(id);
    }

    public List<Results> findPointByOwner(Long owner) {
        return pointRepo.findPointByOwner(owner);
    }

    public void deleteByOwner(Long idOwn) {
        pointRepo.deleteByOwner(idOwn);
    }
}
