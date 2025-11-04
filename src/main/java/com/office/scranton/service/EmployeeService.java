package com.office.scranton.service;

import com.office.scranton.dto.EmployeeDTO;
import com.office.scranton.entity.EmployeeEntity;
import com.office.scranton.exception.ResourceNotFoundException;
import com.office.scranton.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        return employeeEntity
                .stream()
                .map(eachEmployEntity -> modelMapper.map(eachEmployEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id){
        doesEmployeeExist(id);
        return modelMapper.map(employeeRepository.findById(id), EmployeeDTO.class);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO newEmployeeDto){
        EmployeeEntity newEmployeeEntity = modelMapper.map(newEmployeeDto, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(newEmployeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public void updateEmployee(Long id, EmployeeDTO employeeDTO){
        doesEmployeeExist(id);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        employeeRepository.save(employeeEntity);
    }

    public EmployeeDTO updateEmployeePartially(Long id, Map<String, Object> fieldsTobeUpdated){
        doesEmployeeExist(id);
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        fieldsTobeUpdated.forEach((field, value) -> {
            Field fieldTobeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class,field);
            fieldTobeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldTobeUpdated,employeeEntity,value);
            fieldTobeUpdated.setAccessible(false);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public void deleteEmployee(Long id){
        doesEmployeeExist(id);
        employeeRepository.deleteById(id);
    }

    public void doesEmployeeExist(Long id){
        boolean doesEmployeeExist = employeeRepository.existsById(id);
        if(!doesEmployeeExist) throw new ResourceNotFoundException("Employee not found with the id: " + id);
    }
}
