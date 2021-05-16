package com.example.recrutation.pixel.controller;


import com.example.recrutation.pixel.payload.response.PatientsResponse;
import com.example.recrutation.pixel.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api1")
public class Karol2Project {

    private final PatientService patientService;


    public Karol2Project(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = {"/patients"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientsResponse>> karol2Method(
            @RequestParam(value = "cities", required = false) String[] searchCity,
            @RequestParam(value = "specialities", required = false) String[] searchSpecialization
    ) {
        List<PatientsResponse> pagedContent = this.patientService.searchPatientsForCitiesAndSpecialization(searchCity, searchSpecialization);
        return ResponseEntity.status(HttpStatus.OK).body(pagedContent);
    }
}
