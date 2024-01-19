package uoc.ds.pr.model;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import uoc.ds.pr.CTTCompaniesJobsPR2;

public class Room {
    private String roomId;
    private String name;
    private String description;
    private CTTCompaniesJobsPR2.RoomType roomType;
    private final LinkedList<Equipment> equipments;
    private final LinkedList<Employee> employees;

    // Constructor
    public Room(String roomId, String name, String description, CTTCompaniesJobsPR2.RoomType roomType) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.roomType = roomType;
        this.equipments = new LinkedList<>();
        this.employees = new LinkedList<>();
    }

    // Getters
    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CTTCompaniesJobsPR2.RoomType getRoomType() {
        return roomType;
    }

    public LinkedList<Equipment> getEquipments() {
        return equipments;
    }

    public LinkedList<Employee> getEmployees() {
        return employees;
    }

    // Setters
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoomType(CTTCompaniesJobsPR2.RoomType roomType) {
        this.roomType = roomType;
    }

    // Other methods
    public int numEquipments() {
        return equipments.size();
    }
    public void addEquipment(Equipment equipment) {
        for (Iterator<Equipment> it = equipments.values(); it.hasNext(); )
            if (it.next().getEquipmentId().equals(equipment.getEquipmentId())) return;
        this.equipments.insertEnd(equipment);
    }
    public void assignEmployee(Employee employee) {
        this.employees.insertEnd(employee);
    }
    public void removeEquipment(Equipment equipment) {
        Traversal<Equipment> traversal = equipments.positions();

        while (traversal.hasNext()) {
            Position<Equipment> currentPos = traversal.next();
            Equipment currentEquip = currentPos.getElem();

            if (currentEquip.getEquipmentId().equals(equipment.getEquipmentId())) {
                equipments.delete(currentPos);
                return;
            }
        }
    }

}
