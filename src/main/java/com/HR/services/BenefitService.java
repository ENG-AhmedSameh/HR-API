package com.HR.services;

import com.HR.dto.BenefitDto;
import com.HR.persistence.Database;
import com.HR.persistence.entities.Benefit;
import com.HR.persistence.repo.BenefitRepo;
import com.HR.util.mappers.BenefitMapper;

public class BenefitService {

    public BenefitDto getBenefit(int id) {
        return Database.doInTransaction(em->{
            BenefitRepo benefitRepo = new BenefitRepo(em);
            Benefit benefit = benefitRepo.findOne(id).orElseThrow();
            return BenefitMapper.INSTANCE.toDto(benefit);
        });
    }
}
