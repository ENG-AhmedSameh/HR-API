package com.HR.controllers.soap;

import com.HR.dto.VacationDto;
import com.HR.services.VacationService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public class VacationWebService {

    private final VacationService service = new VacationService();

    @WebMethod
    public List<VacationDto> getVacations(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size) {
        return service.getAll(page, size);
    }

    @WebMethod
    public VacationDto getVacation(@WebParam(name = "id") int id) {
        return service.get(id);
    }

    @WebMethod
    public VacationDto createVacation(@WebParam(name = "vacationDto") VacationDto vacationDto) {
        return service.create(vacationDto);
    }

    @WebMethod
    public VacationDto updateVacation(
            @WebParam(name = "vacationDto") VacationDto vacationDto) {
        return service.update(vacationDto, vacationDto.getId()==null?0:vacationDto.getId());
    }

    @WebMethod
    public void deleteVacation(@WebParam(name = "id") int id) {
        service.delete(id);
    }
}
