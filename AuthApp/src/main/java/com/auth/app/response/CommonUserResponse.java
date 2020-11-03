package com.auth.app.response;

import java.util.Set;

import com.auth.app.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserResponse {
 private long userId;
 private String emailId;
 private String UserName;
 private String firstName;
 private String lastName;
 private boolean active;
 private boolean emailVerified;
 private Set<Role> role;
	
	
}
