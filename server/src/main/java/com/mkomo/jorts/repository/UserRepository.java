package com.mkomo.jorts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkomo.jorts.model.JortsUser;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Repository
public interface UserRepository extends JpaRepository<JortsUser, Long> {
	Optional<JortsUser> findByEmail(String email);

	Optional<JortsUser> findByUsernameOrEmail(String username, String email);

	Optional<JortsUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
