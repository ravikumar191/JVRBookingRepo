package com.jvr.booking.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jvr.booking.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}
