package com.example.recrutation.pixel.controller;

import com.example.recrutation.pixel.payload.response.VisitResponse;
import com.example.recrutation.pixel.service.VisitService;
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
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @RequestMapping(value = {"/visits"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VisitResponse>> searchVisitsForSpecialization(
            @RequestParam(value = "specialities") String searchSpecialization
    ) {
        List<VisitResponse> visits = this.visitService.searchVisitsForSpecialization(searchSpecialization);
        return ResponseEntity.status(HttpStatus.OK).body(visits);
    }
}
