package com.masai.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@NotBlank(message = "House Number can not be blank")
	@Min(value = 1,message = "house number can not be negative")
	private String houseNumber;
	
	@NotBlank(message = "landmark can not be blank")
	@Size(min = 2,max = 100,message = "please provide a valid landmark")
	private String landmark;
	
	@NotBlank(message = "city can not be blank")
	@Size(min = 2,max = 100,message = "please provide a valid city")
	private String city;
	
	@NotBlank(message = "state can not be blank")
	@Size(min = 2,max = 100,message = "please provide a valid state")
	private String state;
	
	@NotBlank(message = "pincode can not be blank")
	@Pattern(regexp = "\\d{6}", message = "Invalid PIN code")
	private String pincode;
}
