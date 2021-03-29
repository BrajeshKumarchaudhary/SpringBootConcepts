package com.erp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.store.entity.UnitType;

@Repository
public interface UnitTypeRepo extends JpaRepository<UnitType, Long> {

}
