package com.webischia.analysisbackend.Repositories;

import com.webischia.analysisbackend.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findById(int id);
    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String email);
    Optional<User> getAllByAccessLevel(int level);
}
