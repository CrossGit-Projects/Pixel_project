package com.example.recrutation.pixel.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Practitioner {
    @Id
    public long practitionerId;

    public String specialization;

    @ManyToMany(mappedBy = "practitioners")
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "practitioner")
    private List<Visit> visits;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Practitioner that = (Practitioner) o;
        return practitionerId == that.practitionerId &&
                Objects.equals(specialization, that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(practitionerId, specialization);
    }
}
