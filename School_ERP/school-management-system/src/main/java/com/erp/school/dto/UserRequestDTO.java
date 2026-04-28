package com.erp.school.dto;

import com.erp.school.entityenum.Status;
import com.erp.school.entityenum.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO for user request data.
 */
public class UserRequestDTO {

	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50, message = "First name must be 2 to 50 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "First name cannot contain numbers")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50, message = "Last name must be 2 to 50 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Last name cannot contain numbers")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Enter valid email")
	private String email;

	@NotBlank(message = "Password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$", message = "Password must contain upper, lower, number, special character")
	private String password;

	@NotBlank(message = "Phone is required")
	@Pattern(regexp = "^[2-9][0-9]{9}$", message = "Phone must be 10 digits and cannot start with 0 or 1")
	private String phone;

	private Status status = Status.ACTIVE;

	@NotNull(message = "Role is required")
	private UserRole role;

	// Getters and Setters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}