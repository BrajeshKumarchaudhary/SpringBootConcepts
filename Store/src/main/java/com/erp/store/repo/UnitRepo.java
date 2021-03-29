package com.erp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.store.entity.Unit;

@Repository
public interface UnitRepo extends JpaRepository<Unit, Long>{

}
