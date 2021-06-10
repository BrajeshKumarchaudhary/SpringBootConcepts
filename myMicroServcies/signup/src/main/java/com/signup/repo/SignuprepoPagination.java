package com.signup.repo;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.signup.model.SignUp;
@Component
public interface SignuprepoPagination extends PagingAndSortingRepository<SignUp, Long> {
	
}
