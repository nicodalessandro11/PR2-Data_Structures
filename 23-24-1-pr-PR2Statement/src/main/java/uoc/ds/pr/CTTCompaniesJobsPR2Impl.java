package uoc.ds.pr;

import edu.uoc.ds.adt.nonlinear.graphs.*;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;

import edu.uoc.ds.traversal.Iterator;

import edu.uoc.ds.adt.nonlinear.DictionaryAVLImpl;
import edu.uoc.ds.adt.nonlinear.HashTable;
import edu.uoc.ds.adt.sequential.LinkedList;

import uoc.ds.pr.util.OrderedVector;
import uoc.ds.pr.util.DSArray;

import java.time.LocalDate;
import java.util.Comparator;

public class CTTCompaniesJobsPR2Impl extends CTTCompaniesJobsImpl implements CTTCompaniesJobsPR2  {

    private final DictionaryAVLImpl<String, Equipment> equipments;

    private final DSArray<Role> roles;

    private final HashTable<String, Employee> employees;

    private final HashTable<String, Room> rooms;

    private final DirectedGraph<Employee, String> graph;


    public CTTCompaniesJobsPR2Impl() {
        roles = new DSArray<>(MAX_NUM_ROLES);
        employees = new HashTable<>(HashTable.DEFAULT_CAPACITY);
        rooms = new HashTable<>(HashTable.DEFAULT_CAPACITY);
        equipments = new DictionaryAVLImpl<>();
        graph = new DirectedGraphImpl<>();
    }


    /* --------------------------------------------  PR2 OPERATIONS ----------------------------------------------- */

    /**
     - @pre true.
     - @post if the role code is new, the roles will remain the same plus a new role with the indicated data.
       If not, the role data will have been updated with the new data.
    */
    @Override
    public void addRole(String roleId, String description) {

        Role existingRole = roles.get(roleId);

        if (existingRole == null) {
            Role newRole = new Role(roleId, description);
            roles.put(roleId, newRole);
        } else {
            existingRole.setDescription(description);
            roles.update(roleId, existingRole);
        }
    }

    /**
     - @pre the role exists.
     - @post if the employee's ID is new, the employees will remain the same plus a new employee;
       the number of employees will be the same plus one, and the number of employees in a role will be the same plus one.
       If not, the employee data will have been updated with the new data, and if the role is modified, the number of
       employees in the old role will be the same minus one, and the number of employees in the new role will be the
       same plus one.
     */
    @Override
    public void addEmployee(String employeeId, String name, String surname, LocalDate localDate, String role) {

        Employee existingEmployee = employees.get(employeeId);

        if (existingEmployee == null) {
            Employee newEmployee = new Employee(employeeId, name, surname, localDate, role);
            employees.put(employeeId, newEmployee);
        } else {
            existingEmployee.setName(name);
            existingEmployee.setSurname(surname);
            existingEmployee.setDateOfBirth(localDate);
            existingEmployee.setRole(role);
            employees.put(employeeId, existingEmployee);
        }
    }

    /**
     - @pre true.
     - @post if the roomId is new, the rooms will remain the same plus a new room with the indicated data.
       If not, the room data will have been updated with the new data.
     */
    @Override
    public void addRoom(String roomId, String name, String description, RoomType roomType) {

        Room existingRoom = rooms.get(roomId);

        if (existingRoom == null) {
            Room newRoom = new Room(roomId, name, description, roomType);
            rooms.put(roomId, newRoom);
        } else {
            existingRoom.setName(name);
            existingRoom.setDescription(description);
            existingRoom.setRoomType(roomType);
            rooms.put(roomId, existingRoom);
        }
    }


    /**
     - @pre true.
     - @post if the employee is not assigned to the room, the employees in the room will remain the same plus a new one.
       If the employee is already assigned to the room, an error will be displayed. If the employee or the room does
       not exist, an error will be indicated.
     */
    @Override
    public void assignEmployee(String employeeId, String roomId) throws EmployeeAlreadyAssignedException,
            EmployeeNotFoundException, RoomNotFoundException {

        Employee employee = employees.get(employeeId);
        Room room = rooms.get(roomId);

        if (employee == null) throw new EmployeeNotFoundException();
        if (room == null) throw new RoomNotFoundException();
        if (employee.isAssignedToRoom(room)) throw new EmployeeAlreadyAssignedException();

        employee.assignRoom(room);
        room.assignEmployee(employee);
    }


    /**
     - @pre true.
     - @post returns an iterator to traverse all employees assigned to a room. In case there are no employees or the
       room does not exist, an error should be indicated.
     */
    @Override
    public Iterator<Employee> getEmployeesByRoom(String roomId) throws RoomNotFoundException, NOEmployeeException {

        Room room = rooms.get(roomId);
        if (room == null) throw new RoomNotFoundException();

        LinkedList<Employee> employeesInRoom = room.getEmployees();
        if (employeesInRoom.isEmpty()) throw new NOEmployeeException();

        return employeesInRoom.values();
    }

    /**
     - @pre the role exists.
     - @post returns an iterator to traverse all employees of a role. If there are no employees with that role,
       an error should be indicated.
     */
    @Override
    public Iterator<Employee> getEmployeesByRole(String roleId) throws NOEmployeeException {

        LinkedList<Employee> employeesWithRole = new LinkedList<>();

        Iterator<Employee> it = employees.values();
        while (it.hasNext()) {
            Employee employee = it.next();
            if (employee.getRole().equals(roleId)) employeesWithRole.insertEnd(employee);
        }
        
        if (employeesWithRole.isEmpty()) throw new NOEmployeeException();
        
        return employeesWithRole.values();
    }

    /**
     - @pre true.
     - @post if the equipmentId is new, the equipments will remain the same plus new equipment with the indicated data.
       If not, the equipment data will have been updated with the new data
     */
    @Override
    public void addEquipment(String equipmentId, String name, String description) {

        Equipment existingEquipment = equipments.get(equipmentId);

        if (existingEquipment == null) {
            Equipment newEquipment = new Equipment(equipmentId, name, description);
            equipments.put(equipmentId, newEquipment);
        } else {
            existingEquipment.setName(name);
            existingEquipment.setDescription(description);
            equipments.put(equipmentId, existingEquipment);
        }
    }


    /**
     - @pre true.
     - @post if the equipment is not assigned to the room, the equipments in the room will remain the same plus a new one.
       If the equipment is already assigned to the room, an error will be displayed. If the equipment is already assigned
       to another room, it will be unlinked from it and assigned to the new one, and the number of equipments in the old
       room is the same minus one, and the number of equipments in the new assigned room is the same plus one.
       If the equipment or the room does not exist, an error will be indicated.
     */
    @Override
    public AssignEquipmentResponse assignEquipment(String equipmentId, String roomId)
            throws EquipmentNotFoundException, RoomNotFoundException, EquipmentAlreadyAssignedException {

        Equipment equipment = equipments.get(equipmentId);
        Room room = rooms.get(roomId);

        if (equipment == null) throw new EquipmentNotFoundException();
        if (room == null) throw new RoomNotFoundException();

        Room oldRoom = equipment.getAssignedRoom();
        if (oldRoom != null) if (oldRoom.getRoomId().equals(roomId)) throw new EquipmentAlreadyAssignedException();
        
        if (oldRoom != null) oldRoom.removeEquipment(equipment);
        equipment.assignToRoom(room);

        return oldRoom == null ? AssignEquipmentResponse.ASSIGNED : AssignEquipmentResponse.REASSIGNED;

    }


    /**
     - @pre true.
     - @post returns the level associated with the worker. If the worker does not exist, an error should be indicated.
     */
    @Override
    public CTTCompaniesJobsPR2.Level getLevel(String workerId) throws WorkerNotFoundException {
        Worker worker = getWorker(workerId);
        if (worker == null) {
            throw new WorkerNotFoundException();
        }
        return worker.getLevel();
    }


    /**
     - @pre true.
     - @post returns an iterator to traverse all non-substitute workers enrolled in the job offer jobOfferId.
       If the job offer does not exist or no worker is enrolled, an error should be indicated.
     */
    @Override
    public Iterator<Enrollment> getWorkersByJobOffer(String jobOfferId) throws JobOfferNotFoundException, NoWorkerException {

        JobOffer jobOffer = jobOffers.get(jobOfferId);

        if (jobOffer == null) throw new JobOfferNotFoundException();

        if (jobOffer.getNumWorkers() == 0) throw new NoWorkerException();

        return jobOffer.enrollments();
    }

    /**
     - @pre true.
     - @post returns an iterator to traverse all substitute workers enrolled in the job offer jobOfferId.
       If the job offer does not exist or no substitute is enrolled, an error should be indicated.
     */
    @Override
    public Iterator<Enrollment> getSubstitutesByJobOffer(String jobOfferId) throws JobOfferNotFoundException,
            NoWorkerException {


        JobOffer jobOffer = getJobOffer(jobOfferId);
        if (jobOffer == null) throw new JobOfferNotFoundException();

        int numSubstitutes = jobOffer.getNumSubstitutes();

        if (numSubstitutes == 0) throw new NoWorkerException();
        Iterator<Enrollment> it = jobOffer.substitutes();
        while (it.hasNext()) {
            Enrollment enrollment = it.next();
        }

        return jobOffer.substitutes();
    }


    /**
     - @pre true.
     - @post returns an iterator to traverse all rooms that have no assigned employees.
       If there are no rooms without any employees, an error should be indicated.
     */
    @Override
    public Iterator<Room> getRoomsWithoutEmployees() throws NoRoomsException {

        LinkedList<Room> roomsWithoutEmployees = new LinkedList<>();

        for (Iterator<Room> it = rooms.values(); it.hasNext(); ) {
            Room room = it.next();
            if (room.getEmployees().isEmpty()) roomsWithoutEmployees.insertEnd(room);
        }

        if (roomsWithoutEmployees.isEmpty()) throw new NoRoomsException();

        return roomsWithoutEmployees.values();
    }


    /**
     - @pre true.
     - @post returns an iterator to traverse the top 5 rooms with the most assigned materials.
       If there are no rooms or no materials assigned, an error will be indicated.
     */
    @Override
    public Iterator<Room> best5EquippedRooms() throws NoRoomsException {
        if (rooms.isEmpty()) throw new NoRoomsException();

        OrderedVector<Room> bestEquippedRooms = new OrderedVector<>(MAX_BEST5_EQUIPMENT,
                Comparator.comparingInt(Room::numEquipments));

        Iterator<Room> roomIterator = rooms.values();
        while (roomIterator.hasNext()) {
            Room room = roomIterator.next();
            bestEquippedRooms.update(room);
        }

        return bestEquippedRooms.values();
    }


    /**
     - @pre employeeDni is not a follower of employeeFollowerDni.
     - @post the number of followers of employeeDni will be the same plus one, and the number of employees
       followed (followings) by employeeFollowerDni will be the same plus one. In case the employee to follow or the
       followed employee does not exist, an error will be indicated.
     */
    @Override
    public void addFollower(String followerId, String followedId) throws FollowerNotFound, FollowedException {

        Employee follower = getEmployee(followerId);
        Employee followed = getEmployee(followedId);

        if (follower == null) throw new FollowerNotFound();

        if (followed == null) throw new FollowedException();

        Vertex<Employee> followerVertex = graph.getVertex(follower);
        if (followerVertex == null) followerVertex = graph.newVertex(follower);

        Vertex<Employee> followedVertex = graph.getVertex(followed);
        if (followedVertex == null) followedVertex = graph.newVertex(followed);

        if (graph.getEdge(followerVertex, followedVertex) != null) throw new FollowedException();

        graph.newEdge(followerVertex, followedVertex);
    }


    /**
     - @pre true.
     - @post returns an iterator to traverse the followers of an employee.
       If the employee does not exist or has no followers, an error should be indicated.
     */
    @Override
    public Iterator<Employee> getFollowers(String followedId) throws EmployeeNotFoundException, NoFollowersException {

        Employee followed = getEmployee(followedId);
        if (followed == null) throw new EmployeeNotFoundException();

        Vertex<Employee> followedVertex = graph.getVertex(followed);
        if (followedVertex == null) throw new NoFollowersException();

        LinkedList<Employee> followers = new LinkedList<>();
        Iterator<Edge<String, Employee>> edges = graph.edges();
        while (edges.hasNext()) {
            DirectedEdge<String, Employee> edge = (DirectedEdge<String, Employee>) edges.next();
            if (edge.getVertexDst().equals(followedVertex)) followers.insertEnd(edge.getVertexSrc().getValue());
        }

        if (followers.isEmpty()) throw new NoFollowersException();

        return followers.values();
    }


    /**
     - @pre true.
     - @post returns an iterator to traverse the employees that an employee is following.
       If the employee does not exist or has no employees following, an error should be indicated.
     */
    @Override
    public Iterator<Employee> getFollowings(String followerId) throws EmployeeNotFoundException, NoFollowedException {

        Employee follower = getEmployee(followerId);
        if (follower == null) throw new EmployeeNotFoundException();

        Vertex<Employee> followerVertex = graph.getVertex(follower);
        if (followerVertex == null) throw new NoFollowedException();

        LinkedList<Employee> followings = new LinkedList<>();
        Iterator<Edge<String, Employee>> edges = graph.edgesWithSource(followerVertex);
        while (edges.hasNext()) {
            DirectedEdge<String, Employee> edge = (DirectedEdge<String, Employee>) edges.next();
            followings.insertEnd(edge.getVertexDst().getValue());
        }

        if (followings.isEmpty()) throw new NoFollowedException();

        return followings.values();
    }

     /**
      - @pre true.
      - @post returns an iterator to traverse suggested employees who are followers of employees that employeeDni is
        following and who are currently not followers. If the employee does not exist or has no employees following,
        an error will be indicated.
     */
    @Override
    public Iterator<Employee> recommendations(String followerId) throws EmployeeNotFoundException, NoFollowedException {
        Employee follower = getEmployee(followerId);
        if (follower == null) throw new EmployeeNotFoundException();

        Vertex<Employee> followerVertex = graph.getVertex(follower);
        if (followerVertex == null) throw new NoFollowedException();

        LinkedList<Employee> recommendations = new LinkedList<>();
        Iterator<Edge<String, Employee>> edges = graph.edgesWithSource(followerVertex);

        while (edges.hasNext()) {
            DirectedEdge<String, Employee> edge = (DirectedEdge<String, Employee>) edges.next();
            Employee followed = edge.getVertexDst().getValue();

            Iterator<Edge<String, Employee>> followedEdges = graph.edgesWithSource(graph.getVertex(followed));
            while (followedEdges.hasNext()) {
                DirectedEdge<String, Employee> followedEdge = (DirectedEdge<String, Employee>) followedEdges.next();
                Employee recommended = followedEdge.getVertexDst().getValue();

                if (!recommended.equals(follower) && graph.getEdge(followerVertex, graph.getVertex(recommended)) == null) {
                    recommendations.insertEnd(recommended);
                }
            }
        }

        if (recommendations.isEmpty()) throw new NoFollowedException();

        return recommendations.values();
    }


    /**
     - @pre true.
     - @post returns an iterator to traverse employees working in the same rooms as employeeDni and whom he/she is
       not following. If the employee does not exist or there are no employees not followed assigned to the same rooms, an error will be indicated.
     */
    @Override
    public Iterator<Employee> getUnfollowedColleagues(String employeeId) throws EmployeeNotFoundException, NOEmployeeException, RoomNotFoundException {
        Employee employee = getEmployee(employeeId);
        if (employee == null) throw new EmployeeNotFoundException();

        LinkedList<Employee> unfollowedColleagues = new LinkedList<>();
        Iterator<Room> assignedRoomsIterator = employee.getAssignedRooms();

        while (assignedRoomsIterator.hasNext()) {
            Room room = assignedRoomsIterator.next();
            Iterator<Employee> roomEmployeesIterator = getEmployeesByRoom(room.getRoomId());

            while (roomEmployeesIterator.hasNext()) {
                Employee colleague = roomEmployeesIterator.next();
                if (!colleague.getEmployeeId().equals(employeeId) && !isAlreadyInList(unfollowedColleagues, colleague)) {
                    Vertex<Employee> employeeVertex = graph.getVertex(employee);
                    Vertex<Employee> colleagueVertex = graph.getVertex(colleague);
                    if (graph.getEdge(employeeVertex, colleagueVertex) == null) {
                        unfollowedColleagues.insertEnd(colleague);
                    }
                }
            }
        }

        if (unfollowedColleagues.isEmpty()) throw new NOEmployeeException();

        return unfollowedColleagues.values();
    }


    private boolean isAlreadyInList(LinkedList<Employee> list, Employee employee) {
        Iterator<Employee> it = list.values();
        while (it.hasNext()) if (it.next().getEmployeeId().equals(employee.getEmployeeId())) return true;
        return false;
    }


    /** -------------------------------------  PR2 AUXILIARY OPERATIONS ------------------------------------------- */

    @Override
    public int numRoles() {
        return this.roles.size();
    }

    @Override
    public int numEmployees() {
        return this.employees.size();
    }

    @Override
    public int numEmployeesByRole(String roleId) {
        int count = 0;
        for (Iterator<Employee> it = employees.values(); it.hasNext();) {
            Employee employee = it.next();
            if (employee.getRole().equals(roleId)) count++;
        }
        return count;
    }

    @Override
    public int numRooms() {
        return this.rooms.size();
    }

    @Override
    public int numEquipments() {
        return this.equipments.size();
    }

    public int numEquipmentsByRoom(String roomId) {
        Room room = rooms.get(roomId);
        if (room == null) return 0;
        return room.getEquipments().size();
    }

    @Override
    public int numFollowers(String employeeId) {

        Employee employee = getEmployee(employeeId);
        if (employee == null) return 0;

        Vertex<Employee> employeeVertex = graph.getVertex(employee);
        Iterator<Edge<String, Employee>> edges = graph.edgedWithDestination(employeeVertex);

        int count = 0;
        while (edges.hasNext()) {
            edges.next();
            count++;
        }

        return count;
    }


    @Override
    public int numFollowings(String employeeId) {

        Employee employee = getEmployee(employeeId);
        if (employee != null) {
            Vertex<Employee> employeeVertex = graph.getVertex(employee);
            Iterator<Edge<String, Employee>> edges = graph.edgesWithSource(employeeVertex);

            int count = 0;
            while (edges.hasNext()) {
                edges.next();
                count++;
            }

            return count;

        } else return 0;

    }


    @Override
    public int numRoomsByEmployee(String employeeId) {
        Employee employee = employees.get(employeeId);

        if (employee == null) return 0;

        int roomCount = 0;

        Iterator<Room> roomIterator = employee.getAssignedRooms();

        while (roomIterator.hasNext()) {
            roomIterator.next();
            roomCount++;
        }

        return roomCount;
    }

    @Override
    public Room whereIs(String equipmentId) {
        for (Iterator<Room> itRooms = rooms.values(); itRooms.hasNext();) {
            Room room = itRooms.next();

            Iterator<Equipment> itEquipments = room.getEquipments().values();

            while (itEquipments.hasNext()) {
                Equipment equipment = itEquipments.next();
                if (equipment.getEquipmentId().equals(equipmentId)) {
                    return room;
                }
            }
        }
        return null;
    }

    @Override
    public Role getRole(String role) {
        return roles.get(role);
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return employees.get(employeeId);
    }

    @Override
    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    @Override
    public Equipment getEquipment(String equipmentId) {
        return equipments.get(equipmentId);
    }
}
