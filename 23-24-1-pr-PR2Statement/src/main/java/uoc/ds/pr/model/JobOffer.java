package uoc.ds.pr.model;

import edu.uoc.ds.adt.nonlinear.PriorityQueue;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.adt.sequential.Queue;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.util.QueueLinkedList;

import java.time.LocalDate;
import java.util.Comparator;

import static java.time.temporal.ChronoUnit.DAYS;

public class JobOffer implements Comparable<JobOffer>  {

    private String jobOfferId;
    private Company company;
    private String description;
    private CTTCompaniesJobs.Qualification minQualification;
    private int maxWorkers;
    private LocalDate startDate;
    private LocalDate endDate;

    private Queue<Enrollment> enrollments;
    private PriorityQueue<Enrollment> substitutes;
    private List<Rating> ratings;
    private double sumRating;

    // Comparators
    public static final Comparator<JobOffer> CMP_V = Comparator.comparingDouble(JobOffer::rating);

    public static final Comparator<Enrollment> CMP_L =
            Comparator.comparingInt(e -> -e.getWorker().getLevel().ordinal());


    // Constructor
    public JobOffer(String jobOfferId, Company company, String description,
                    CTTCompaniesJobs.Qualification minQualification, int maxWorkers,
                    LocalDate startDate, LocalDate endDate) {
        this.jobOfferId = jobOfferId;
        this.company = company;
        this.description = description;
        this.minQualification = minQualification;
        this.maxWorkers = maxWorkers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrollments = new QueueLinkedList<>();
        this.substitutes = new PriorityQueue<>(CMP_L);
        this.ratings = new LinkedList<>();
    }

    // Getters
    public String getJobOfferId() {
        return jobOfferId;
    }

    public Company getCompany() {
        return this.company;
    }
    public Iterator<Rating> getRatings() {
        return ratings.values();
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public CTTCompaniesJobs.Qualification getMinQualification() {
        return minQualification;
    }

    public int getNumWorkers() {
        return this.enrollments.size();
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }
    public int getNumSubstitutes() {
        return substitutes.size();
    }

    public double getTotalRating() {
        return (!this.ratings.isEmpty() ?this.sumRating/this.ratings.size():0);
    }

    public long getWorkingDays() {
        return DAYS.between(this.getStartDate(), this.getEndDate());
    }

    public String getDescription() {
        return this.description;
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

    public void setEnrollments(Queue<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void setSubstitutes(PriorityQueue<Enrollment> substitutes) {
        this.substitutes = substitutes;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setSumRating(double sumRating) {
        this.sumRating = sumRating;
    }

    // Other Methods
    public void addRegistration(Worker worker, CTTCompaniesJobs.Response response) {
        Enrollment enrollment = new Enrollment(worker);
        if (response == CTTCompaniesJobs.Response.SUBSTITUTE) {
            substitutes.add(enrollment);
        } else {
            enrollments.add(enrollment);
        }
    }

    public double rating() {
        return (!this.ratings.isEmpty() ?(sumRating / this.ratings.size()):0);
    }

    @Override
    public int compareTo(JobOffer o) {
        return this.jobOfferId.compareTo(o.jobOfferId);
    }

    public void addRating(int value, String message, Worker worker) {
        this.ratings.insertEnd(new Rating(value, message));
        this.sumRating+=value;
    }

    public boolean workerHasMinimumQualification(Worker worker) {
        return worker.getQualification().getValue() >= this.minQualification.getValue();
    }

    public boolean isFull() {
        return (getNumWorkers()>=this.getMaxWorkers());
    }

    public Iterator<Enrollment> substitutes() {
        return substitutes.values();
    }

    public Iterator<Enrollment> enrollments() {
        return enrollments.values();
    }

}
