package com.bug_tracker.service;

import com.bug_tracker.model.UserRole;
import com.bug_tracker.repository.spring_data.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleService {

    private RoleRepository roleRepository;

    public UserRole getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public UserRole getRoleById(long id){
        return roleRepository.findById(id).get();
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
