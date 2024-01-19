package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;

import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String role;
    private final LinkedList<Room> assignedRooms;

    // Constructor
    public Employee(String employeeId, String name, String surname, LocalDate dateOfBirth, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.assignedRooms = new LinkedList<>();
    }


    // Getters
    public String getEmployeeId() {
        return employeeId;
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

    public String getRole() {
        return role;
    }

    public Iterator<Room> getAssignedRooms() {
        return this.assignedRooms.values();
    }


    // Setters
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate localDate) {
        this.dateOfBirth = localDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Other Methods
    public void assignRoom(Room room) {
        if (!isAssignedToRoom(room)) {
            this.assignedRooms.insertEnd(room);
        }
    }
    public boolean isAssignedToRoom(Room room) {
        Iterator<Room> it = this.assignedRooms.values();
        while (it.hasNext()) {
            Room assignedRoom = it.next();
            if (assignedRoom.equals(room)) return true;
        }
        return false;
    }

}
