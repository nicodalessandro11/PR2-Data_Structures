package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;

import java.time.LocalDate;

public class Worker {

    private String id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private CTTCompaniesJobs.Qualification qualification;
    private CTTCompaniesJobsPR2.Level level;
    private List<JobOffer> jobOffers;
    private long workingDays;


    // Constructor
    public Worker(String id, String name, String surname,
                  LocalDate dateOfBirth, CTTCompaniesJobs.Qualification qualification) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setQualification(qualification);
        this.level = CTTCompaniesJobsPR2.Level.BEGINNER;
        this.jobOffers = new LinkedList<>();
        this.workingDays = 0;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public CTTCompaniesJobs.Qualification getQualification() {
        return qualification;
    }

    public CTTCompaniesJobsPR2.Level getLevel() {
        return level;
    }

    public long getWorkingDays() {
        return this.workingDays;
    }

    public Iterator<JobOffer> getJobOffers() {
        return jobOffers.values();
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    private void setQualification(CTTCompaniesJobs.Qualification qualification) {
        this.qualification = qualification;
    }

    public void setLevel(CTTCompaniesJobsPR2.Level level) {
        this.level = level;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public void setWorkingDays(long workingDays) {
        this.workingDays = workingDays;
    }

    // Other Methods
    public void update(String name, String surname,
                       LocalDate dateOfBirth, CTTCompaniesJobs.Qualification qualification) {
        this.setName(name);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setQualification(qualification);
    }

    public void addJobOffer(JobOffer jobOffer) {
        long workingDaysToAdd = jobOffer.getWorkingDays();
        this.workingDays += workingDaysToAdd;
        this.jobOffers.insertEnd(jobOffer);
        setLevelBasedOnWorkingHours();
    }

    private void setLevelBasedOnWorkingHours() {
        long workingHours = this.workingDays * CTTCompaniesJobsPR2.HOURS_PER_DAY;
        CTTCompaniesJobsPR2.Level newLevel;

        if (workingHours >= 1000) {
            newLevel = CTTCompaniesJobsPR2.Level.EXPERT;
        } else if (workingHours >= 500) {
            newLevel = CTTCompaniesJobsPR2.Level.SENIOR;
        } else if (workingHours >= 200) {
            newLevel = CTTCompaniesJobsPR2.Level.JUNIOR;
        } else if (workingHours >= 10) {
            newLevel = CTTCompaniesJobsPR2.Level.INTERN;
        } else {
            newLevel = CTTCompaniesJobsPR2.Level.BEGINNER;
        }
        setLevel(newLevel);
    }

    public boolean isInJobOffer(Object obj) {
        if (obj instanceof JobOffer) {
            Iterator<Enrollment> it = ((JobOffer) obj).enrollments();
            return isInJobOffer(it);
        } else if (obj instanceof Iterator<?>) {
            Iterator<Enrollment> it = (Iterator<Enrollment>) obj;
            boolean found = false;
            while (!found && it.hasNext()) {
                Enrollment enrollment = it.next();
                found = enrollment.getWorker().getId().equals(this.id);
            }
            return found;
        }
        return false;
    }

    public boolean isInJobOfferAsSubstitute(JobOffer jobOffer) {
        Iterator<Enrollment> it = jobOffer.substitutes();
        return isInJobOffer(it);
    }

}