package com.erp.school.dto;

public class AuthResponseDTO {

	private String token;
	private String role;
	private boolean firstLogin;

	public AuthResponseDTO() {
	}

	public AuthResponseDTO(String token, String role, boolean firstLogin) {
		this.token = token;
		this.role = role;
		this.firstLogin = firstLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
}