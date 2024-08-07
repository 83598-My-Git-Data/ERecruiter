package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DepartmentEntity;

public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Long>{

}
