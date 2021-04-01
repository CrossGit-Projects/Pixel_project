package com.example.recrutation.pixel.service;

import com.example.recrutation.pixel.payload.response.PatientsResponse;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface PatientService {
    void savePatients(Iterable<CSVRecord> patientsRecord);

    void savePatientsAndPractitioners(Iterable<CSVRecord> records);

    List<PatientsResponse> searchPatientsForCitiesAndSpecialization(String[] paramsCities, String[] paramsSpecialization);
}
