package com.HR.services;

import com.HR.dto.BenefitDto;
import com.HR.persistence.Database;
import com.HR.persistence.entities.Benefit;
import com.HR.persistence.repo.BenefitRepo;
import com.HR.util.mappers.BenefitMapper;

import java.util.NoSuchElementException;

public class BenefitService extends GenericResourceService<BenefitDto,Benefit,BenefitRepo>{

    public BenefitService() {
        super(new BenefitRepo(), BenefitMapper.INSTANCE);
    }


//    @Override
//    public BenefitDto get(int id) {
//        return Database.doInTransaction(em->{
//            BenefitRepo benefitRepo = (BenefitRepo) new BenefitRepo().withEntityManager(em);
//            Benefit benefit = benefitRepo.findOne(id).orElseThrow(()->
//                    new NoSuchElementException("No Benefit found with id: " + id));
//            return BenefitMapper.INSTANCE.toDto(benefit);
//        });
//    }
//
//    @Override
//    public BenefitDto create(BenefitDto benefitDto) {
//        return Database.doInTransaction(em->{
//            BenefitRepo benefitRepo = (BenefitRepo) new BenefitRepo().withEntityManager(em);
//            Benefit benefit = BenefitMapper.INSTANCE.toEntity(benefitDto);
//            benefitRepo.create(benefit);
//            return BenefitMapper.INSTANCE.toDto(benefit);
//        });
//    }
//
//    @Override
//    public List<BenefitDto> getAll(int page, int size) {
//        return Database.doInTransaction(em->{
//            BenefitRepo benefitRepo = (BenefitRepo) new BenefitRepo().withEntityManager(em);
//            return BenefitMapper.INSTANCE.toDtoList(benefitRepo.findAllWithPagination(page, size));
//        });
//    }

    @Override
    public BenefitDto update(BenefitDto benefit, int id) {
        return Database.doInTransaction(em->{
            boolean hasChange = false;
            BenefitRepo benefitRepo = (BenefitRepo) new BenefitRepo().withEntityManager(em);
            Benefit benefitEntity = benefitRepo.findOne(id).orElseThrow(()->
                    new NoSuchElementException("No Benefit found with id: " + id));
            if(benefit.getBenefitName() != null && !benefit.getBenefitName().equals(benefitEntity.getBenefitName())){
                benefitEntity.setBenefitName(benefit.getBenefitName());
                hasChange = true;
            }
            if(benefit.getDescription() != null && !benefit.getDescription().equals(benefitEntity.getDescription())){
                benefitEntity.setDescription(benefit.getDescription());
                hasChange = true;
            }
            if(hasChange)
                benefitRepo.update(benefitEntity);
            else
                throw new IllegalArgumentException("No changes found to be update");
            return BenefitMapper.INSTANCE.toDto(benefitEntity);
        });
    }

//    @Override
//    public void delete(int id) {
//        Database.doInTransactionWithoutResult(em->{
//            BenefitRepo benefitRepo = (BenefitRepo) new BenefitRepo().withEntityManager(em);
//            Benefit benefit = benefitRepo.findOne(id).orElseThrow(()->
//                    new NoSuchElementException("No Benefit found with id: " + id));
//            benefitRepo.delete(benefit);
//        });
//    }
}
