package com.example.recrutation.pixel.service;

import com.example.recrutation.pixel.payload.response.VisitResponse;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface VisitService {
    void saveVisit(Iterable<CSVRecord> records);

    List<VisitResponse> searchVisitsForSpecialization(String paramSpecialization);
}