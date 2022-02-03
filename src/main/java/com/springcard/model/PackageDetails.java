package com.springcard.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class PackageDetails {
    private String skuId;
    private double width;
    private double height;
    private double weight;
    private double length;
}
