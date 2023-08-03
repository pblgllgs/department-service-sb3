package com.pblgllgs.departmentservice.model;

public record Employee(
        Long id,
        long departmentId,
        String name,
        int age,
        String position) {
}
