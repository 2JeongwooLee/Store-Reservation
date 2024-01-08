package com.zerobase.mission.manager.repository;

import com.zerobase.mission.manager.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    boolean existsByEmail(String email);
    Optional<Manager> findByEmail(String email);
}
