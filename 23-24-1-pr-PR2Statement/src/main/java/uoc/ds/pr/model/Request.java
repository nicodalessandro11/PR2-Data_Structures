package uoc.ds.pr.model;

import uoc.ds.pr.CTTCompaniesJobs;

import java.time.LocalDate;

public class Request {

    private String jobOfferId;
    private Company company;
    private String description;
    private CTTCompaniesJobs.Qualification minQualification;
    private int maxWorkers;
    private LocalDate startDate;
    private LocalDate endDate;
    private CTTCompaniesJobs.Status status;
    private String descriptionStatus;
    private LocalDate dateStatus;


    // Constructor
    public Request(String jobOfferId, Company company, String description, CTTCompaniesJobs.Qualification minQualification,
                   int maxWorkers, LocalDate startDate, LocalDate endDate) {
        this.jobOfferId = jobOfferId;
        this.company = company;
        this.description = description;
        this.minQualification = minQualification;
        this.maxWorkers = maxWorkers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = CTTCompaniesJobs.Status.PENDING;
    }

    // Getters
    public CTTCompaniesJobs.Status getStatus() {
        return status;
    }

    public LocalDate getDateStatus() {
        return dateStatus;
    }

    public String getDescriptionStatus() {
        return descriptionStatus;
    }

    public String getJobOfferId() {
        return jobOfferId;
    }

    public Company getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public CTTCompaniesJobs.Qualification getMinQualification() {
        return minQualification;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // Setters

    public void setJobOfferId(String jobOfferId) {
        this.jobOfferId = jobOfferId;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinQualification(CTTCompaniesJobs.Qualification minQualification) {
        this.minQualification = minQualification;
    }

    public void setMaxWorkers(int maxWorkers) {
        this.maxWorkers = maxWorkers;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(CTTCompaniesJobs.Status status) {
        this.status = status;
    }

    public void setDescriptionStatus(String descriptionStatus) {
        this.descriptionStatus = descriptionStatus;
    }

    public void setDateStatus(LocalDate dateStatus) {
        this.dateStatus = dateStatus;
    }

    // Other Methods

    public JobOffer getJobOffer() {
        return new JobOffer(this.jobOfferId, this.company, this.description,
                this.minQualification, this.maxWorkers, this.startDate, this.endDate );
    }
    public void update(CTTCompaniesJobs.Status status, LocalDate date, String message) {
        this.status = status;
        this.dateStatus = date;
        this.descriptionStatus = message;
    }

    public boolean isEnabled() {
        return this.status == CTTCompaniesJobs.Status.ENABLED;
    }


}
