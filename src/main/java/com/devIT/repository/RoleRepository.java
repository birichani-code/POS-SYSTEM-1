package com.devIT.repository;

import com.devIT.entity.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
