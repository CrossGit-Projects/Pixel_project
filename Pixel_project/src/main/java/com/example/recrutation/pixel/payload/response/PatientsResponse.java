package com.example.recrutation.pixel.payload.response;

public class PatientsResponse {
    private String firstName;
    private String lastName;
    private Long countVisit;


    public PatientsResponse(String firstName, String lastName, Long countVisit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.countVisit = countVisit;
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

    public Long getCountVisit() {
        return countVisit;
    }

    public void setCountVisit(Long countVisit) {
        this.countVisit = countVisit;
    }

    @Override
    public String toString() {
        return "PatientsResponse{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", countVisit=" + countVisit +
                '}';
    }
}
