package com.pblgllgs.departmentservice.repository;

import com.pblgllgs.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {
    List<Department> departments = new ArrayList<>();

    public List<Department> findAll() {
        return departments;
    }

    public Department addDepartment(Department department) {
        departments.add(department);
        return department;
    }

    public Department findById(Long id) {
        return departments
                .stream()
                .filter(dep -> dep.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}
