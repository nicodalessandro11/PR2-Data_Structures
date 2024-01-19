package uoc.ds.pr.model;

public class Enrollment {
    private Worker worker;

   // Constructor
    public Enrollment( Worker worker) {
        this.worker = worker;
    }

    // Getters

    public Worker getWorker() {
        return this.worker;
    }

    // Setters

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

}
