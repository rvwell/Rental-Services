package com.infnet.rental.repository;

import com.infnet.rental.domain.model.RentalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {
    List<RentalRequest> findByRenterId(Long renterId);
}