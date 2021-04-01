package com.example.recrutation.pixel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long patientId;

    public String firstName;

    public String lastName;

    public String city;

    public LocalDateTime createdAt;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Practitioner> practitioners = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Visit> visits;

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Practitioner> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(List<Practitioner> practitioners) {
        this.practitioners = practitioners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patientId == patient.patientId &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(city, patient.city) &&
                Objects.equals(createdAt, patient.createdAt) &&
                Objects.equals(practitioners, patient.practitioners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, firstName, lastName, city, createdAt, practitioners);
    }
}
