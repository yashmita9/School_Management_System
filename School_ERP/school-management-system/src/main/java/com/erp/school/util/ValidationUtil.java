package com.erp.school.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.erp.school.common.ApiResponse;
import com.erp.school.exception.BadRequestException;

@Component
public class ValidationUtil {

    public ApiResponse validate(BindingResult bindingResult) {

        ApiResponse res = new ApiResponse(true);

        if (bindingResult.hasErrors()) {

            res.setSuccess(false);

            Map<String, String> errors = new HashMap<>();

            List<FieldError> list = bindingResult.getFieldErrors();

            list.forEach(e -> {
                errors.put(e.getField(), e.getDefaultMessage());
            });

            res.addInputErrors(errors);
        }

        return res;
    }
    
    public void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new BadRequestException("File is required");
        }

        if (file.getSize() > 2 * 1024 * 1024) {
            throw new BadRequestException("Max file size is 2MB allowed");
        }

        String type = file.getContentType();

        if (!List.of("image/jpeg", "image/png").contains(type)) {
            throw new BadRequestException("Only JPG/PNG allowed");
        }
    }
}