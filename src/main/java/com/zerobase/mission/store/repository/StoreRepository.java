package com.zerobase.mission.store.repository;

import com.zerobase.mission.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreName(String storeName);

    boolean existsByStoreName(String storeName);

    List<Store> findAllByManagerId(Long managerId);


}
