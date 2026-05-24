package com.howie.pharmacy.pharmacy_store.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howie.pharmacy.pharmacy_store.entity.Ward;
import com.howie.pharmacy.pharmacy_store.repository.WardRepository;
import com.howie.pharmacy.pharmacy_store.services.WardService;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> getWardsByProvinceCode(String provinceCode) {
        return wardRepository.findByProvinceCode(provinceCode);
    }
}
