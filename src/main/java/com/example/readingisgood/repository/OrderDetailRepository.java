package com.example.readingisgood.repository;

import com.example.readingisgood.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findBycreateDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}
