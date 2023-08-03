package com.pblgllgs.departmentservice.repository;

import com.pblgllgs.departmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
