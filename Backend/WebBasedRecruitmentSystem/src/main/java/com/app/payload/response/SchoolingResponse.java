package com.app.payload.response;



import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SchoolingResponse {
	
	private String class10Board;
	private LocalDate class10PassingYear;
	private int class10Marks;
	private String class12Board;
	private LocalDate class12PassingYear;
	private int class12Marks;
}
