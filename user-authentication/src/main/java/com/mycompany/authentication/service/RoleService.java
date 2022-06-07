package com.mycompany.authentication.service;

import com.mycompany.authentication.model.Role;

import java.util.Collection;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface RoleService {
    public Collection<Role> findAll();
}
