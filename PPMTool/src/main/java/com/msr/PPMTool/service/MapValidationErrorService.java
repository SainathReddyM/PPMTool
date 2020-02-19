package com.msr.PPMTool.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> mapValidationService(BindingResult theBindingResult){
        Map<String, String> errorMap= new HashMap<>();
        for (FieldError er: theBindingResult.getFieldErrors()) {
            errorMap.put(er.getField(),er.getDefaultMessage());
        }
        return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
