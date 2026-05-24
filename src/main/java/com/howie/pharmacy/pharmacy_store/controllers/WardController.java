package com.howie.pharmacy.pharmacy_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.howie.pharmacy.pharmacy_store.entity.Ward;
import com.howie.pharmacy.pharmacy_store.services.WardService;

@RestController
@RequestMapping("/api/wards")
public class WardController {
    @Autowired
    private WardService wardService;

    @GetMapping()
    public List<Ward> getWardsByProvinceCode(@RequestParam String provinceCode) {
        return wardService.getWardsByProvinceCode(provinceCode);
    }
}
