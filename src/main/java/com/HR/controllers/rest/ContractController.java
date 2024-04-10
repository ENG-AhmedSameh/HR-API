package com.HR.controllers.rest;

import com.HR.dto.ContractDto;
import com.HR.services.ContractService;
import jakarta.ws.rs.Path;

@Path("contracts")
public class ContractController extends ResourceController<ContractDto, ContractService>{
    public ContractController(){
        super(new ContractService());
    }
}
