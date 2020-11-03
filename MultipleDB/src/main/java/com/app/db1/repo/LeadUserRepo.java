package com.app.db1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.db1.entity.LeadUsers;

@Repository
public interface LeadUserRepo extends JpaRepository<LeadUsers,Long> {

}
