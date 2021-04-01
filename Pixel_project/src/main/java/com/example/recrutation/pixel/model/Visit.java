package com.example.recrutation.pixel.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Visit {

    @Id
    public long visitId;

    @ManyToOne
    private Practitioner practitioner;

    @ManyToOne
    private Patient patient;


    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return visitId == visit.visitId &&
                Objects.equals(patient, visit.patient) &&
                Objects.equals(practitioner, visit.practitioner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId, patient, practitioner);
    }
}
