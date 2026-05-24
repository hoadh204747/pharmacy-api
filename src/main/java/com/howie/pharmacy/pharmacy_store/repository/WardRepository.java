package com.howie.pharmacy.pharmacy_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howie.pharmacy.pharmacy_store.entity.Ward;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    List<Ward> findByProvinceCode(String provinceCode);
}
