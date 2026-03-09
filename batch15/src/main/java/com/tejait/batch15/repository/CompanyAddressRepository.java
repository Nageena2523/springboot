package com.tejait.batch15.repository;

import com.tejait.batch15.model.CompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Integer> {
    Optional<CompanyAddress> findByAppId(Integer appId);
}
