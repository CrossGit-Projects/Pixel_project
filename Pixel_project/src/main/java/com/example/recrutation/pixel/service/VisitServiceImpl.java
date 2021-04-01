package com.example.recrutation.pixel.service;

import com.example.recrutation.pixel.exception.LoadDataException;
import com.example.recrutation.pixel.model.Patient;
import com.example.recrutation.pixel.model.Practitioner;
import com.example.recrutation.pixel.model.Visit;
import com.example.recrutation.pixel.payload.response.VisitResponse;
import com.example.recrutation.pixel.repository.PatientsRepository;
import com.example.recrutation.pixel.repository.PractitionersRepository;
import com.example.recrutation.pixel.repository.VisitsRepository;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {
    private final PatientsRepository patientsRepository;

    private final PractitionersRepository practitionersRepository;

    private final VisitsRepository visitsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public VisitServiceImpl(PatientsRepository patientsRepository, PractitionersRepository practitionersRepository, VisitsRepository visitsRepository) {
        this.patientsRepository = patientsRepository;
        this.practitionersRepository = practitionersRepository;
        this.visitsRepository = visitsRepository;
    }

    @Override
    public void saveVisit(Iterable<CSVRecord> records) {
        List<Patient> listIdPatients = patientsRepository.findAll();
        List<Practitioner> listIdPractitioners = practitionersRepository.findAll();
        for (CSVRecord csvRecord : records) {
            Optional<Patient> patient = listIdPatients.stream()
                    .filter(a -> a.getPatientId() == Long.parseLong(csvRecord.get("patientId")))
                    .findFirst();
            Optional<Practitioner> practitioner = listIdPractitioners.stream()
                    .filter(a -> a.getPractitionerId() == Long.parseLong(csvRecord.get("practitionerId")))
                    .findFirst();
            if (!patient.isPresent()) {
                throw new LoadDataException("Cannot find patient with id :" + csvRecord.get("patientId"));
            }
            if (!practitioner.isPresent()) {
                throw new LoadDataException("Cannot find practitioner with id :" + csvRecord.get("practitionerId"));
            }
            Visit visit = new Visit();
            visit.setVisitId(Long.parseLong(csvRecord.get("visitId")));
            visit.setPatient(patient.get());
            visit.setPractitioner(practitioner.get());
            visitsRepository.save(visit);
        }
    }

    public List<VisitResponse> searchVisitsForSpecialization(String paramSpecialization) {
        List<VisitResponse> visitResponseList = new ArrayList<>();
        List l = entityManager.createQuery(
                "SELECT new com.example.recrutation.pixel.payload.response.VisitResponse(p.specialization, COUNT(e.visitId)) "
                        + "FROM Patient d JOIN d.visits e  JOIN e.practitioner p WHERE p.specialization = (?1) " +
                        " GROUP BY p.specialization")
                .setParameter(1, paramSpecialization)
                .getResultList();

        for (Object p : l) {
            visitResponseList.add((VisitResponse) p);
        }
        return visitResponseList;
    }
}
