package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ExamEntity;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

}
