package uoc.ds.pr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;

import java.time.LocalDate;
import edu.uoc.ds.traversal.Iterator;

public class CTTCCompaniesJobsPR2AdditionalTest extends CTTCCompaniesJobsPR2Test {

    @Before
    public void setUp() throws Exception {
        cttCompaniesJobs = FactoryCTTCCompaniesJobs.getCTTCompaniesJobs();
    }

    @After
    public void tearDown() {
        cttCompaniesJobs = null;
    }

    @Test
    public void assignEquipmentToDifferentRoomsTest() throws DSException {
        cttCompaniesJobs.addEquipment("E1", "Projector", "High-quality projector");
        cttCompaniesJobs.addRoom("R1", "Conference Room", "Room for meetings", CTTCompaniesJobsPR2.RoomType.OFFICE);
        cttCompaniesJobs.addRoom("R2", "Lab Room", "Room for experiments", CTTCompaniesJobsPR2.RoomType.LABORATORY);

        cttCompaniesJobs.assignEquipment("E1", "R1");
        Assert.assertEquals("R1", cttCompaniesJobs.whereIs("E1").getRoomId());

        // Reassign the equipment to another room
        cttCompaniesJobs.assignEquipment("E1", "R2");
        Assert.assertEquals("R2", cttCompaniesJobs.whereIs("E1").getRoomId());
    }

    @Test
    public void updateRoomInformationAndVerifyAssignmentsTest() throws DSException {
        cttCompaniesJobs.addRoom("R1", "Old Room", "Old Description", CTTCompaniesJobsPR2.RoomType.OFFICE);
        cttCompaniesJobs.addEmployee("EMP1", "John", "Doe", LocalDate.now(), "Role1");
        cttCompaniesJobs.assignEmployee("EMP1", "R1");

        cttCompaniesJobs.addRoom("R1", "New Room", "New Description", CTTCompaniesJobsPR2.RoomType.LABORATORY);

        Room updatedRoom = cttCompaniesJobs.getRoom("R1");
        Assert.assertEquals("New Room", updatedRoom.getName());
        Assert.assertEquals("New Description", updatedRoom.getDescription());
        Assert.assertEquals(CTTCompaniesJobsPR2.RoomType.LABORATORY, updatedRoom.getRoomType());

        Iterator<Employee> employeesInRoom = cttCompaniesJobs.getEmployeesByRoom("R1");
        Assert.assertTrue(employeesInRoom.hasNext());
        Employee assignedEmployee = employeesInRoom.next();
        Assert.assertEquals("EMP1", assignedEmployee.getEmployeeId());
        Assert.assertFalse(employeesInRoom.hasNext());
    }
}
