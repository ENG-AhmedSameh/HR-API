package com.HR.controllers.soap;

import com.HR.dto.BenefitDto;
import com.HR.services.BenefitService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;


@WebService
public class BenefitWebService {

    private final BenefitService service = new BenefitService();

    @WebMethod
    public List<BenefitDto> getBenefits(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size) {
        return service.getAll(page, size);
    }

    @WebMethod
    public BenefitDto getBenefit(
            @WebParam(name = "id") int id) {
        return service.get(id);
    }

    @WebMethod
    public BenefitDto createBenefit(
            @WebParam(name = "benefitDto") BenefitDto benefitDto) {
        return service.create(benefitDto);
    }

    @WebMethod
    public BenefitDto updateBenefit(
            @WebParam(name = "benefitDto") BenefitDto benefitDto) {
        return service.update(benefitDto, benefitDto.getId()==null?0:benefitDto.getId());
    }

    @WebMethod
    public void deleteBenefit(
            @WebParam(name = "id") int id) {
        service.delete(id);
    }
}


