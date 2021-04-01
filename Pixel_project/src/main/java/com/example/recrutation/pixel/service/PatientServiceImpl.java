package com.example.recrutation.pixel.service;

import com.example.recrutation.pixel.model.Patient;
import com.example.recrutation.pixel.model.Practitioner;
import com.example.recrutation.pixel.payload.response.PatientsResponse;
import com.example.recrutation.pixel.repository.PatientsRepository;
import com.example.recrutation.pixel.repository.PractitionersRepository;
import com.example.recrutation.pixel.utilities.CSVHelper;
import com.example.recrutation.pixel.utilities.QueryConstants;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;


@Service
public class PatientServiceImpl implements PatientService {

    private final PatientsRepository patientsRepository;

    private final PractitionersRepository practitionersRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PatientServiceImpl(PatientsRepository patientsRepository, PractitionersRepository practitionersRepository) {
        this.patientsRepository = patientsRepository;
        this.practitionersRepository = practitionersRepository;
    }

    @Override
    public void savePatients(Iterable<CSVRecord> patientsRecord) {
        List<Patient> patientList = new ArrayList<>();

        for (CSVRecord csvRecord : patientsRecord) {
            Patient patient = new Patient();
            patient.setPatientId(Long.parseLong(csvRecord.get("patientId")));
            patient.setFirstName(csvRecord.get("firstName"));
            patient.setLastName(csvRecord.get("lastName"));
            patient.setCity(csvRecord.get("city"));
            patient.setCreatedAt(CSVHelper.parseDate(csvRecord.get("createdAt")));
            patientList.add(patient);
        }
        patientsRepository.saveAll(patientList);
    }

    @Override
    public void savePatientsAndPractitioners(Iterable<CSVRecord> records) {
        List<Patient> listIdPatients = patientsRepository.findAll();
        List<Practitioner> listIdPractitioners = practitionersRepository.findAll();
        for (CSVRecord csvRecord : records) {
            Optional<Patient> patient = listIdPatients.stream()
                    .filter(a -> a.getPatientId() == Long.parseLong(csvRecord.get("patientId")))
                    .findFirst();
            Optional<Practitioner> practitioner = listIdPractitioners.stream()
                    .filter(a -> a.getPractitionerId() == Long.parseLong(csvRecord.get("practitionerId")))
                    .findFirst();
            if (patient.isPresent() && practitioner.isPresent()) {
                Patient patients = patient.get();
                List<Practitioner> practitionerSet = patients.getPractitioners();
                practitionerSet.add(practitioner.get());
                patients.setPractitioners(practitionerSet);
                patientsRepository.save(patients);
                practitionerSet.clear();
            }
        }
    }

    public List<PatientsResponse> searchPatientsForCitiesAndSpecialization(String[] paramsCities, String[] paramsSpecialization) {
        List<PatientsResponse> patientsResponseList = new ArrayList<>();
        List<String> paramCityList = new ArrayList<>();
        List<String> paramSpecializationList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT new com.example.recrutation.pixel.payload.response.PatientsResponse (p.firstName, p.lastName, COUNT(v)) ");
        queryBuilder.append("FROM Patient p JOIN p.visits v JOIN v.practitioner pr ");
        if (isExistParameters(paramsCities)) {
            paramCityList = Arrays.asList(paramsCities);
            queryBuilder.append("WHERE p.city IN (?1) ");
        }
        if (isExistParameters(paramsSpecialization)) {
            if (isExistParameters(paramsCities)) {
                queryBuilder.append("AND ");
            } else {
                queryBuilder.append("WHERE ");
            }
            queryBuilder.append("pr.specialization IN(?2) ");
            paramSpecializationList = Arrays.asList(paramsSpecialization);
        }
        queryBuilder.append("GROUP BY p.firstName, p.lastName");
        Query query = entityManager.createQuery(
                queryBuilder.toString());
        if (nonNull(paramsCities) && !paramsCities[0].equals(QueryConstants.ALL)) {
            query.setParameter(1, paramCityList);
        }
        if (nonNull(paramsSpecialization) && !paramsSpecialization[0].equals(QueryConstants.ALL)) {
            query.setParameter(2, paramSpecializationList);
        }

        List l = query.getResultList();

        for (Object p : l) {
            patientsResponseList.add((PatientsResponse) p);
        }
        return patientsResponseList;
    }

    private boolean isExistParameters(String[] parameters) {
        return nonNull(parameters) && !parameters[0].equals(QueryConstants.ALL);
    }

}