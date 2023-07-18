package com.masai.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorDetail {
	
	private String message;
	private LocalDateTime errorDateTime;
	private String description;
	
	
}
