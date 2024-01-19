package uoc.ds.pr.model;

public class Equipment {
    private String equipmentId;
    private String name;
    private String description;
    private Room assignedRoom;

    // Constructor
    public Equipment(String equipmentId, String name, String description) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.description = description;
        this.assignedRoom = null;
    }

    // Getters
    public String getEquipmentId() {
        return equipmentId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getAssignedRoom() {
        return assignedRoom;
    }

    // Setters
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedRoom(Room assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    // Other methods
    public void assignToRoom(Room room) {
        if (this.assignedRoom != null) this.assignedRoom.removeEquipment(this);
        this.assignedRoom = room;
        if (room != null) room.addEquipment(this);
    }


}
