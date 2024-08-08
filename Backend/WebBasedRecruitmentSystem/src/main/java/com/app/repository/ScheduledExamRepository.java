package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ScheduledExamEntity;

public interface ScheduledExamRepository extends JpaRepository<ScheduledExamEntity ,Long>{

}
