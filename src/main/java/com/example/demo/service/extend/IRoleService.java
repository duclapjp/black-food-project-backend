package com.example.demo.service.extend;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
    Role save(Role role);
    Optional<Role> findById(Long id);
    void remove(Long id);
}
