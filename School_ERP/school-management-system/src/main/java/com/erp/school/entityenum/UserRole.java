package com.erp.school.entityenum;

import com.erp.school.exception.BadRequestException;

public enum UserRole {
    ADMIN,
    TEACHER;
	public static UserRole from(String role) {

	    if (role == null || role.isBlank()) {
	        throw new BadRequestException("Role cannot be null or empty");
	    }

	    role = role.trim().toUpperCase();

	    for (UserRole r : UserRole.values()) {
	        if (r.name().equals(role)) {
	            return r;
	        }
	    }

	    throw new BadRequestException("Invalid role! Allowed: ADMIN, TEACHER");
	}
}