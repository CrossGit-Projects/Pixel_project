package com.example.recrutation.pixel.init.data;

import com.example.recrutation.pixel.service.*;
import com.example.recrutation.pixel.utilities.CSVConstants;
import com.example.recrutation.pixel.utilities.CSVHelper;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;


@Component
public class InitializationLoader {

    private static final Logger log = LoggerFactory.getLogger(InitializationLoader.class);

    private final PatientService patientService;

    private final PractitionerService practitionerService;

    private final VisitService visitService;

    public InitializationLoader(PatientServiceImpl patientService, PractitionerServiceImpl practitionerService, VisitServiceImpl visitService) {
        this.patientService = patientService;
        this.practitionerService = practitionerService;
        this.visitService = visitService;
    }

    @PostConstruct
    public void init() {
        try {
            patientService.savePatients(getCsvRecords(CSVConstants.PATIENTS_CSV_PATH));
            practitionerService.savePractitioners(getCsvRecords(CSVConstants.PRACTITIONERS_CSV_PATH));
            patientService.savePatientsAndPractitioners(getCsvRecords(CSVConstants.PATIENT_2_PRACTITIONER_CSV_PATH));
            visitService.saveVisit(getCsvRecords(CSVConstants.VISITS_CSV_PATH));
            log.info("Uploaded the file successfully: ");

        } catch (Exception e) {
            log.error("Could not upload the file: " + e + "!");
        }
    }

    private Iterable<CSVRecord> getCsvRecords(String string) throws FileNotFoundException, URISyntaxException {
        File fileTmp = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(string)).toURI()).toFile();
        return CSVHelper.csvReadFile(new FileInputStream(fileTmp));
    }

}
