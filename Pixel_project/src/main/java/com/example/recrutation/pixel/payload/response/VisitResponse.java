package com.example.recrutation.pixel.payload.response;

public class VisitResponse {

    public String visit;
    public Long numberOfVisits;

    public VisitResponse(String visit, Long numberOfVisits) {
        this.visit = visit;
        this.numberOfVisits = numberOfVisits;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public Long getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Long numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    @Override
    public String toString() {
        return "VisitResponse{" +
                "visit='" + visit + '\'' +
                ", numberOfVisits=" + numberOfVisits +
                '}';
    }
}
