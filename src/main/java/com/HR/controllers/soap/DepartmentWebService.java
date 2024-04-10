package com.HR.controllers.soap;


import com.HR.dto.DepartmentDto;
import com.HR.services.DepartmentService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;


@WebService
public class DepartmentWebService {

    private final DepartmentService service = new DepartmentService();

    @WebMethod
    public List<DepartmentDto> getDepartments(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size) {
        return service.getAll(page, size);
    }

    @WebMethod
    public DepartmentDto getDepartment(@WebParam(name = "id") int id) {
        return service.get(id);
    }

    @WebMethod
    public DepartmentDto createDepartment(@WebParam(name = "departmentDto") DepartmentDto departmentDto) {
        return service.create(departmentDto);
    }

    @WebMethod
    public DepartmentDto updateDepartment(
            @WebParam(name = "departmentDto") DepartmentDto departmentDto) {
        return service.update(departmentDto, departmentDto.getId()==null?0:departmentDto.getId());
    }

    @WebMethod
    public void deleteDepartment(@WebParam(name = "id") int id) {
        service.delete(id);
    }
}
