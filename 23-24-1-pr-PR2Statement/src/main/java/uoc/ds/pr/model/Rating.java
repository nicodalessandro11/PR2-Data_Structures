package uoc.ds.pr.model;

public class Rating {
    private int value;
    private String message;


    // Constructor
    public Rating( int value, String message) {
        this.value = value;
        this.message = message;

    }

    // Getters
    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }


    // Setters
    public void setValue(int value) {
        this.value = value;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
