package com.companyname.springbootcrudrest.repository;

import com.companyname.springbootcrudrest.model.LeaveDetails;
import com.companyname.springbootcrudrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveDetails, Long>{

}
