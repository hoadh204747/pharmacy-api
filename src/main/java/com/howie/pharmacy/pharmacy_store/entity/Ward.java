package com.howie.pharmacy.pharmacy_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wards")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "province_code", nullable = false)
    private String provinceCode;

    @Column(name = "ward_code", nullable = false, unique = true)
    private String wardCode;

    @Column(nullable = false)
    private String name;

    @Column(name = "location_slug", nullable = false)
    private String locationSlug;

    @Column(name = "legacy_address")
    private String legacyAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "province_code", referencedColumnName = "province_code", insertable = false, updatable = false)
    private Province province;

}
