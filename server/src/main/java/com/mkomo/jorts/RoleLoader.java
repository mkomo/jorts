package com.mkomo.jorts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mkomo.jorts.model.Role;
import com.mkomo.jorts.model.RoleName;
import com.mkomo.jorts.repository.RoleRepository;


@Component
public class RoleLoader implements CommandLineRunner {


	private RoleRepository roleRepo;

	@Autowired
	public RoleLoader(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		for (RoleName name : RoleName.values()) {
			this.roleRepo.save(new Role(name));
		}
	}
}
