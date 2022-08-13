package com.barclays.paymentssystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.MasterBillerList;

@Repository
public interface MasterBillRepo extends JpaRepository<MasterBillerList, String> {

}
