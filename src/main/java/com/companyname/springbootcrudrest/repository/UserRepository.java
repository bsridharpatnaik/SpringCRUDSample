package com.companyname.springbootcrudrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.companyname.springbootcrudrest.model.User;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findById(Long id);
}
