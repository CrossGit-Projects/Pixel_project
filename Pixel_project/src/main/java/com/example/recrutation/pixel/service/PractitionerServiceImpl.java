package com.example.recrutation.pixel.service;

import com.example.recrutation.pixel.model.Practitioner;
import com.example.recrutation.pixel.repository.PractitionersRepository;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PractitionerServiceImpl implements PractitionerService {

    private final PractitionersRepository practitionersRepository;

    public PractitionerServiceImpl(PractitionersRepository practitionersRepository) {
        this.practitionersRepository = practitionersRepository;
    }

    @Override
    public void savePractitioners(Iterable<CSVRecord> practitionersRecord) {
        List<Practitioner> practitionerList = new ArrayList<>();

        for (CSVRecord csvRecord : practitionersRecord) {
            Practitioner practitioner = new Practitioner();
            practitioner.setPractitionerId(Long.parseLong(csvRecord.get("practitionerId")));
            practitioner.setSpecialization(csvRecord.get("specialization"));
            practitionerList.add(practitioner);
        }
        practitionersRepository.saveAll(practitionerList);
    }
}
