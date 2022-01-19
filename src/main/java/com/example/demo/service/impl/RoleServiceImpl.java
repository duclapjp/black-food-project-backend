package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.extend.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void remove(Long id) {

        roleRepository.deleteById(id);
    }
}
