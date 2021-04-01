package com.example.recrutation.pixel.service;

import org.apache.commons.csv.CSVRecord;

public interface PractitionerService {

    void savePractitioners(Iterable<CSVRecord> practitionersRecord);
}
