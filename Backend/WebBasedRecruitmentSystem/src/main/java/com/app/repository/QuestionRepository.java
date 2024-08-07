package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{

}
