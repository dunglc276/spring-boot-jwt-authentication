package com.lcd.jwt.rq;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.Getter;

@Getter
public class SignupRequest {

	@NotBlank
	@NotNull
	@Length(min = 3, max = 20)
  private String username;

  @NotBlank
  @Email
  @NotNull
  @Length(min = 6, max = 50)
  private String email;
  
  private Set<String> roles;
  
  @NotBlank
  @NotNull
  @Length(min = 6, max = 50)
  private String password;
}
