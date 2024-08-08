package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.LanguageEntity;

public interface LanguageEntityRepository extends JpaRepository<LanguageEntity, Long> {
	/**
	 * get language with name and proficiency using custom query
	 * */
	@Query("SELECT lang from LanguageEntity lang where name=:name and proficiency = :proficiency")
	Optional<LanguageEntity> getLanguage(@Param("name") String name, @Param("proficiency") String proficiency);
}
