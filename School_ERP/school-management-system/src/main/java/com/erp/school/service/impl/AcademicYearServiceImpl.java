package com.erp.school.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.school.dto.AcademicYearRequestDTO;
import com.erp.school.dto.AcademicYearResponseDTO;
import com.erp.school.entity.AcademicYearEntity;
import com.erp.school.entityenum.Status;
import com.erp.school.exception.BadRequestException;
import com.erp.school.exception.ResourceNotFoundException;
import com.erp.school.repository.AcademicYearRepository;
import com.erp.school.service.AcademicYearServiceInt;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AcademicYearServiceImpl implements AcademicYearServiceInt {

    @Autowired
    private AcademicYearRepository repo;

    @Autowired
    private ModelMapper mapper;

    // ================= CREATE =================
    @Override
    public AcademicYearResponseDTO create(AcademicYearRequestDTO dto) {

        if (repo.existsByYearName(dto.getYearName())) {
            throw new BadRequestException("Academic year already exists");
        }

        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new BadRequestException("End date must be after start date");
        }

        AcademicYearEntity entity = mapper.map(dto, AcademicYearEntity.class);
        entity.setStatus(Status.ACTIVE);

        if (Boolean.TRUE.equals(dto.getCurrentYear())) {
            resetCurrentYear();
            entity.setCurrentYear(true);
        }

        AcademicYearEntity saved = repo.save(entity);

        return mapper.map(saved, AcademicYearResponseDTO.class);
    }

    // ================= UPDATE =================
    @Override
    public AcademicYearResponseDTO update(Long id, AcademicYearRequestDTO dto) {

        AcademicYearEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic year not found"));

        if (!entity.getYearName().equals(dto.getYearName())
                && repo.existsByYearName(dto.getYearName())) {
            throw new BadRequestException("Academic year already exists");
        }

        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new BadRequestException("End date must be after start date");
        }

        entity.setYearName(dto.getYearName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());

        if (Boolean.TRUE.equals(dto.getCurrentYear())) {
            resetCurrentYear();
            entity.setCurrentYear(true);
        }

        AcademicYearEntity updated = repo.save(entity);

        return mapper.map(updated, AcademicYearResponseDTO.class);
    }

    // ================= DELETE =================
    @Override
    public void softDelete(Long id) {

        AcademicYearEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic year not found"));

        entity.setStatus(Status.DELETED);
        entity.setDeletedAt(LocalDateTime.now());
        entity.setCurrentYear(false);

        repo.save(entity);
    }

    // ================= GET ACTIVE =================
    @Override
    public List<AcademicYearResponseDTO> getActive() {

        return repo.findByStatus(Status.ACTIVE)
                .stream()
                .map(e -> mapper.map(e, AcademicYearResponseDTO.class))
                .toList();
    }

    // ================= GET CURRENT =================
    @Override
    public AcademicYearResponseDTO getCurrentYear() {

        AcademicYearEntity entity = repo.findByCurrentYearTrue()
                .orElseThrow(() -> new ResourceNotFoundException("Current academic year not found"));

        return mapper.map(entity, AcademicYearResponseDTO.class);
    }

    @Override
    public AcademicYearResponseDTO setCurrentYear(Long id) {

        AcademicYearEntity entity = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Academic year not found"));

        if (entity.getStatus() != Status.ACTIVE) {
            throw new BadRequestException("Only ACTIVE academic year can be current");
        }

        // old current remove
        repo.findByCurrentYearTrue().ifPresent(old -> {
            old.setCurrentYear(false);
            repo.save(old);
        });

        // new current set
        entity.setCurrentYear(true);

        AcademicYearEntity updated = repo.save(entity);

        return mapper.map(updated, AcademicYearResponseDTO.class);
    }

    // ================= TOGGLE STATUS =================
    @Override
    public AcademicYearResponseDTO toggleStatus(Long id) {

        AcademicYearEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic year not found"));

        if (entity.getStatus() == Status.DELETED) {
            throw new BadRequestException("Deleted academic year cannot be toggled");
        }

        entity.setStatus(
                entity.getStatus() == Status.ACTIVE
                        ? Status.INACTIVE
                        : Status.ACTIVE
        );

        return mapper.map(repo.save(entity), AcademicYearResponseDTO.class);
    }

    // ================= PRIVATE METHOD =================
    private void resetCurrentYear() {

        repo.findByCurrentYearTrue().ifPresent(year -> {
            year.setCurrentYear(false);
            repo.save(year);
        });
    }
    
}