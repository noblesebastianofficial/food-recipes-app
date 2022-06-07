
package com.mycompany.authentication.service;

import com.mycompany.authentication.model.Role;
import com.mycompany.authentication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    /**
     * Find all roles from the database
     */
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

}
