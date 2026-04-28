package com.erp.school.constant;

public class AppConstants {

	private AppConstants() {
	}

	// ================= BASE =================
	public static final String API = "/api";

	// ================= USER MODULE =================
	public static final String USERS = API + "/users";

	public static final String USER_BY_ID = "/{id}";
	public static final String USER_BY_EMAIL = "/email";
	public static final String USER_STATUS = "/{id}/status";

	public static final String USER_TOGGLE_STATUS = "/{id}/toggle-status";
	public static final String USER_PROFILE = "/profile";
	public static final String USER_CHANGE_PASSWORD = "/change-password";

	// ================= AUTH MODULE =================
	public static final String AUTH = API + "/auth";
	public static final String LOGIN = "/login";

	// ================= SCHOOL MODULE =================
	public static final String SCHOOL = API + "/school";

	public static final String SCHOOL_CREATE = "/create";
	public static final String SCHOOL_UPLOAD_LOGO = "/upload-logo";

	public static final String SCHOOL_BY_ID = "/{id}";
	public static final String SCHOOL_UPDATE = "/update/{id}";

	// ================= CLASS MODULE =================
	public static final String CLASSES = API + "/classes";

	public static final String CLASS_BULK = "/bulk";
	public static final String CLASS_ACTIVE = "/active";
	public static final String CLASS_TOGGLE_STATUS = "/toggle-status/{id}";
	public static final String CLASS_BY_ID = "/{id}";

	// ================= ENQUIRY MODULE =================
	public static final String ENQUIRY = API + "/enquiry";

	public static final String ENQUIRY_BY_ID = "/{id}";
	public static final String ENQUIRY_STATUS_UPDATE = "/{id}/status";
	public static final String ENQUIRY_BY_STATUS = "/status/{status}";

	// ================= STUDENT ENROLLMENT MODULE =================
	public static final String STUDENT_ENROLLMENT = API + "/student-enrollment";

	public static final String ENROLLMENT_CURRENT = "/current/{studentId}";
	public static final String ENROLLMENT_HISTORY = "/history/{studentId}";
	public static final String ENROLLMENT_BY_CLASS = "/class/{classId}";
	public static final String ENROLLMENT_BY_SECTION = "/section/{sectionId}";
	public static final String ENROLLMENT_PROMOTE = "/promote";
	public static final String ENROLLMENT_TRANSFER_SECTION = "/{enrollmentId}/section/{sectionId}";

}