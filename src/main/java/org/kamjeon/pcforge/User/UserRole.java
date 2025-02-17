package org.kamjeon.pcforge.User;

import lombok.Getter;

@Getter
public enum UserRole {
	Admin("ROLE_Admin"),
	User("ROLE_User");
	
	UserRole(String value){
		this.value = value;
	}
	
	private String value;
}
