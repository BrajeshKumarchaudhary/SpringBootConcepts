package com.erp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.store.entity.UnitConversionMapping;

@Repository
public interface UnitConversionMappingRepo extends JpaRepository<UnitConversionMapping, Long> {

}
