package com.auth.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
