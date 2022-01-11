package com.example.demo.service.impl;

import com.example.demo.model.GeneralStatus;
import com.example.demo.repository.IGeneralStatusRepository;
import com.example.demo.service.extend.IGeneralStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralStatusService implements IGeneralStatusService {

    @Autowired
    private IGeneralStatusRepository generalStatusRepository;

    @Override
    public List<GeneralStatus> findAll() {
        return generalStatusRepository.findAll();
    }

    @Override
    public Optional<GeneralStatus> findById(Long id) {
        return generalStatusRepository.findById(id);
    }

    @Override
    public GeneralStatus save(GeneralStatus generalStatus) {
        return generalStatusRepository.save(generalStatus);
    }

    @Override
    public void remove(Long id) {
        generalStatusRepository.deleteById(id);
    }
}
