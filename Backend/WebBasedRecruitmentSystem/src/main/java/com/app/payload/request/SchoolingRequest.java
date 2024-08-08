package com.app.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class SchoolingRequest {
	
	@NotBlank(message = "class 10 board is required")
	@Size(min = 4,max = 255,message = "Class 10 board name is not valid")
	private String class10Board;
	
	@Past
	private LocalDate class10PassingYear;
	
	@NotNull(message="class 10 marks is required")
	private int class10Marks;
	
	
	private String class12Board;
	
	@Past
	private LocalDate class12PassingYear;
	
	
	private int class12Marks;
}
