package com.stackroute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.PaymentModel;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, String>{

     List<PaymentModel> findByUserEmail(String userEmail);

}
