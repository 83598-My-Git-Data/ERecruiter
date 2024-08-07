package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="department")
@Setter @Getter @NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity extends BaseEntity{

	@Column(name="department_name",length = 30)
	private String departmentName;
}
