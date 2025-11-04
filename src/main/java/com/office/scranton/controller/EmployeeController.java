package com.office.scranton.controller;

import com.office.scranton.dto.EmployeeDTO;
import com.office.scranton.exception.ResourceNotFoundException;
import com.office.scranton.service.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    public final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employee) {
        return new ResponseEntity<>(employeeService.createNewEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<HttpStatus> updateEmployeeById(@PathVariable Long employeeId, @Valid @RequestBody EmployeeDTO employeeDto) {
        employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartiallyEmployeeById(@PathVariable Long employeeId, @RequestBody Map<String, Object> updates) {
        if(employeeService.updateEmployeePartially(employeeId, updates) == null) throw new ResourceNotFoundException("Employee with id: " + employeeId + " not found");
        return new ResponseEntity<>(employeeService.updateEmployeePartially(employeeId, updates), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
