package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Policyadding;
public interface PolicyaddRepo extends JpaRepository<Policyadding, Long> {
}
