package com.example.recrutation.pixel.controller;

import com.example.recrutation.pixel.payload.response.PatientsResponse;
import com.example.recrutation.pixel.service.PatientService;
import com.example.recrutation.pixel.service.PatientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = {"/patients"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientsResponse>> searchForCitiesAndSpecialization(
            @RequestParam(value = "cities", required = false) String[] searchCity,
            @RequestParam(value = "specialities", required = false) String[] searchSpecialization
    ) {
        List<PatientsResponse> pagedContent = this.patientService.searchPatientsForCitiesAndSpecialization(searchCity, searchSpecialization);
        return ResponseEntity.status(HttpStatus.OK).body(pagedContent);
    }

}
