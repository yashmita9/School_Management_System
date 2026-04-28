package com.erp.school.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.school.dto.StudentAttendanceRecordDTO;
import com.erp.school.dto.StudentAttendanceRequestDTO;
import com.erp.school.dto.StudentAttendanceResponseDTO;
import com.erp.school.entity.ClassEntity;
import com.erp.school.entity.SectionEntity;
import com.erp.school.entity.StudentAttendanceEntity;
import com.erp.school.entity.StudentEntity;
import com.erp.school.entity.UserEntity;
import com.erp.school.exception.ResourceNotFoundException;
import com.erp.school.repository.ClassRepository;
import com.erp.school.repository.SectionRepository;
import com.erp.school.repository.StudentAttendanceRepository;
import com.erp.school.repository.StudentRepository;
import com.erp.school.repository.UserRepository;
import com.erp.school.service.StudentAttendanceServiceInt;

@Service
@Transactional
public class StudentAttendanceServiceImpl implements StudentAttendanceServiceInt {

	@Autowired
	private StudentAttendanceRepository attendanceRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private SectionRepository sectionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String markAttendance(StudentAttendanceRequestDTO dto) {

		ClassEntity classEntity = classRepository.findById(dto.getClassId())
				.orElseThrow(() -> new ResourceNotFoundException("Class not found"));

		SectionEntity sectionEntity = sectionRepository.findById(dto.getSectionId())
				.orElseThrow(() -> new ResourceNotFoundException("Section not found"));

		// Temporary Teacher
		UserEntity teacher = userRepository.findById(1L)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

		List<StudentAttendanceEntity> attendanceList = new ArrayList<>();

		for (StudentAttendanceRecordDTO record : dto.getRecords()) {

			StudentEntity student = studentRepository.findById(record.getStudentId())
					.orElseThrow(() -> new ResourceNotFoundException("Student not found"));

			StudentAttendanceEntity entity;

			if (attendanceRepository.existsByStudentIdAndAttendanceDate(record.getStudentId(),
					dto.getAttendanceDate())) {

				entity = attendanceRepository
						.findByStudentIdAndAttendanceDate(record.getStudentId(), dto.getAttendanceDate()).get();

				entity.setStatus(record.getStatus());

			} else {

				entity = new StudentAttendanceEntity();

				entity.setStudent(student);
				entity.setClassEntity(classEntity);
				entity.setSectionEntity(sectionEntity);
				entity.setAttendanceDate(dto.getAttendanceDate());
				entity.setStatus(record.getStatus());
				entity.setMarkedBy(teacher);
			}

			attendanceList.add(entity);
		}

		attendanceRepository.saveAll(attendanceList);

		return "Attendance marked successfully";
	}

	@Override
	public List<StudentAttendanceResponseDTO> getClassAttendance(Long classId, Long sectionId,
			LocalDate attendanceDate) {

		return attendanceRepository
				.findByClassEntityIdAndSectionEntityIdAndAttendanceDate(classId, sectionId, attendanceDate).stream()
				.map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<StudentAttendanceResponseDTO> getStudentAttendanceHistory(Long studentId) {

		return attendanceRepository.findByStudentIdOrderByAttendanceDateDesc(studentId).stream().map(this::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentAttendanceResponseDTO> getStudentAttendanceByDateRange(Long studentId, LocalDate startDate,
			LocalDate endDate) {

		return attendanceRepository.findByStudentIdAndAttendanceDateBetween(studentId, startDate, endDate).stream()
				.map(this::convertToDto).collect(Collectors.toList());
	}

	/**
	 * Manual DTO Mapping
	 */
	private StudentAttendanceResponseDTO convertToDto(StudentAttendanceEntity entity) {

		StudentAttendanceResponseDTO dto = new StudentAttendanceResponseDTO();

		dto.setId(entity.getId());

		dto.setAttendanceDate(entity.getAttendanceDate());

		if (entity.getStatus() != null) {
			dto.setStatus(entity.getStatus().name());
		}

		// Student
		if (entity.getStudent() != null) {
			dto.setStudentId(entity.getStudent().getId());

			dto.setStudentName(entity.getStudent().getStudentName());
		}

		// Class
		if (entity.getClassEntity() != null) {
			dto.setClassId(entity.getClassEntity().getId());
			dto.setClassName(entity.getClassEntity().getClassName());
		}

		// Section
		if (entity.getSectionEntity() != null) {
			dto.setSectionId(entity.getSectionEntity().getId());
			dto.setSectionName(entity.getSectionEntity().getSectionName());
		}

		// Teacher
		if (entity.getMarkedBy() != null) {
			dto.setMarkedById(entity.getMarkedBy().getId());

			dto.setMarkedByName(entity.getMarkedBy().getFirstName() + " " + entity.getMarkedBy().getLastName());
		}

		return dto;
	}
}