package com.HR.controllers.soap;

import com.HR.dto.ContractDto;
import com.HR.services.ContractService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;


@WebService
public class ContractWebService {

    private final ContractService service = new ContractService();

    @WebMethod
    public List<ContractDto> getContracts(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size) {
        return service.getAll(page, size);
    }

    @WebMethod
    public ContractDto getContract(
            @WebParam(name = "id") int id) {
        return service.get(id);
    }

    @WebMethod
    public ContractDto createContract(
            @WebParam(name = "contractDto") ContractDto contractDto) {
        return service.create(contractDto);
    }

    @WebMethod
    public ContractDto updateContract(
            @WebParam(name = "contractDto") ContractDto contractDto) {
        return service.update(contractDto, contractDto.getId()==null?0:contractDto.getId());
    }

    @WebMethod
    public void deleteContract(
            @WebParam(name = "id") int id) {
        service.delete(id);
    }
}
