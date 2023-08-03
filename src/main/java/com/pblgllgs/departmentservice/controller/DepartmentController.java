package com.pblgllgs.departmentservice.controller;

import com.pblgllgs.departmentservice.client.EmployeeClient;
import com.pblgllgs.departmentservice.model.Department;
import com.pblgllgs.departmentservice.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    private final EmployeeClient employeeClient;

    public DepartmentController(DepartmentRepository departmentRepository, EmployeeClient employeeClient) {
        this.departmentRepository = departmentRepository;
        this.employeeClient = employeeClient;
    }


    @GetMapping
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        return departmentRepository.addDepartment(department);
    }

    @GetMapping("/{departmentId}")
    public Department findById(@PathVariable("departmentId") Long id) {
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        List<Department> departments = departmentRepository.findAll();
        departments
                .forEach(
                        department -> department
                                .setEmployees(
                                        employeeClient
                                                .findByDepartment(
                                                        department.getId())));
        return departments;
    }

}
