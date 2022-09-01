package com.example.demo.service;

import com.example.demo.Repository.RoleRepository;
import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleByName() {
        return roleRepository.findByName("USER");
    }
    public Role findRoleByNameForAdmin(){
        return roleRepository.findByName("ADMIN");
    }
}
