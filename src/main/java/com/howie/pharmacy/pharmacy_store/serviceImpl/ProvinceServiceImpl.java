package com.howie.pharmacy.pharmacy_store.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howie.pharmacy.pharmacy_store.entity.Province;
import com.howie.pharmacy.pharmacy_store.repository.ProvinceRepository;
import com.howie.pharmacy.pharmacy_store.services.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> findAll() {
        List<Province> provinces = provinceRepository.findAllByOrderByNameAsc();
        return provinces;
    }

    @Override
    public Optional<Province> findByProvinceCode(String provinceCode) {
        return provinceRepository.findByProvinceCode(provinceCode);
    }
}
