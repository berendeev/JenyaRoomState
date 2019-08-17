package controller;

import daoimpl.Dao;
import model.Room;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.fail;

public class AddElementControllerUnitTest {
    @Test
    public void addNewElementTest() throws Exception {
        DaoStub daoStub = new DaoStub();
        AddElementController addElementController = new AddElementController(daoStub);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        addElementController.addNewElement(
                sdf.parse("12:00:00"),
                sdf.parse("13:30:00"),
                "1-341",
                "Programming",
                "Somebody first name",
                "Somebody last name");

        //check that save called once
        Assert.assertEquals(1, daoStub.getCallCount());
    }

    @Test
    public void addNewElementWithEndBeforeStartDateTest() throws ParseException {
        DaoStub daoStub = new DaoStub();
        AddElementController addElementController = new AddElementController(daoStub);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        try {
            addElementController.addNewElement(
                    sdf.parse("12:00:00"),
                    sdf.parse("11:30:00"),
                    "1-341",
                    "Programming",
                    "Somebody first name",
                    "Somebody last name");
            fail();
        } catch (IllegalArgumentException exc) {
            Assert.assertEquals("Incorrect date", exc.getMessage());
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void addNewElementWithNullDateTest() throws ParseException {
        DaoStub daoStub = new DaoStub();
        AddElementController addElementController = new AddElementController(daoStub);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        try {
            addElementController.addNewElement(
                    null,
                    sdf.parse("11:30:00"),
                    "1-341",
                    "Programming",
                    "Somebody first name",
                    "Somebody last name");
            fail();
        } catch (IllegalArgumentException exc) {
            Assert.assertEquals("Incorrect date", exc.getMessage());
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void addNewElementWithNoRoomTest() throws ParseException {
        DaoStub daoStub = new DaoStub();
        AddElementController addElementController = new AddElementController(daoStub);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        try {
            addElementController.addNewElement(
                    sdf.parse("12:00:00"),
                    sdf.parse("13:30:00"),
                    null,
                    "Programming",
                    "Somebody first name",
                    "Somebody last name");
            fail();
        } catch (IllegalArgumentException exc) {
            fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Some of arguments no have values", ex.getMessage());
        }
    }

    @Test
    public void addNewElementWithEmptyLessonName() throws ParseException {
        DaoStub daoStub = new DaoStub();
        AddElementController addElementController = new AddElementController(daoStub);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        try {
            addElementController.addNewElement(
                    sdf.parse("12:00:00"),
                    sdf.parse("13:30:00"),
                    "1-341",
                    "",
                    "Somebody first name",
                    "Somebody last name");
            fail();
        } catch (IllegalArgumentException exc) {
            fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Some of arguments no have values", ex.getMessage());
        }
    }


    private class DaoStub implements Dao {
        private int callCount;

        @Override
        public Room getRoomByNumber(String roomNumber) {
            return null;
        }

        @Override
        public void save(String roomNumber, String lessonName, Date dateStart, Date dateEnd, String firstName, String lastName) {
            callCount++;
        }

        public int getCallCount() {
            return callCount;
        }
    }
}