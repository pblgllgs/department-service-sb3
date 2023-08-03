package com.pblgllgs.departmentservice.controller;

import com.pblgllgs.departmentservice.client.EmployeeClient;
import com.pblgllgs.departmentservice.model.Department;
import com.pblgllgs.departmentservice.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return departmentRepository.save(department);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Optional<Department>> findById(@PathVariable("departmentId") Long id) {
        return new ResponseEntity<>(departmentRepository.findById(id), HttpStatus.OK);
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
