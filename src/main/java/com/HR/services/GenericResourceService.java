package com.HR.services;

import com.HR.persistence.Database;
import com.HR.persistence.repo.GenericRepo;
import com.HR.util.mappers.GenericMapper;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class GenericResourceService<DTO, Entity, Repo extends GenericRepo<Entity>> {

        private final Repo repo;
        private final GenericMapper<DTO, Entity> mapper;

        protected GenericResourceService(Repo repo, GenericMapper<DTO, Entity> mapper) {
                this.repo = repo;
                this.mapper = mapper;
        }

        public DTO get(int id) {
                return Database.doInTransaction(em -> {
                        Entity entity = this.repo.withEntityManager(em).findOne(id).orElseThrow(() ->
                                new NoSuchElementException("No entity found with id: " + id));
                        return this.mapper.toDto(entity);
                });
        }

        public DTO create(DTO dto) {
                return Database.doInTransaction(em -> {
                        Entity entity = this.mapper.toEntity(dto);
                        this.repo.withEntityManager(em).create(entity);
                        return this.mapper.toDto(entity);
                });
        }

        public List<DTO> getAll(int page, int size) {
                return Database.doInTransaction(em -> {
                        return this.mapper.toDtoList(this.repo.withEntityManager(em).findAllWithPagination(page, size));
                });
        }

        public abstract DTO update(DTO dto, int id);

//        public DTO update(DTO dto, int id) {
//                return Database.doInTransaction(em -> {
//                        Entity entity = this.repo.withEntityManager(em).findOne(id).orElseThrow(() ->
//                                new NoSuchElementException("No entity found with id: " + id));
//                        entity = this.mapper.updateEntity(dto, entity);
//                        this.repo.withEntityManager(em).update(entity);
//                        return this.mapper.toDto(entity);
//                });
//        }

        public void delete(int id) {
                Database.doInTransactionWithoutResult(em -> {
                        Entity entity = this.repo.withEntityManager(em).findOne(id).orElseThrow(() ->
                                new NoSuchElementException("No entity found with id: " + id));
                        this.repo.withEntityManager(em).delete(entity);
                });
        }
}


