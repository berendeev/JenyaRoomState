package controller;

import daoimpl.Dao;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;


public class AddElementController {
    private Dao dao;

    public AddElementController(Dao dao) {
        this.dao = dao;
    }

    public void addNewElement(
            Date lessonStartDate,
            Date lessonEndDate,
            String roomNumber,
            String lessonName,
            String teacherFirstName,
            String teacherLastName) {

        if (lessonStartDate == null || lessonEndDate == null || !lessonStartDate.after(lessonEndDate)) {
            throw new IllegalArgumentException();
        } else {
            if (StringUtils.isEmpty(roomNumber)
                    || StringUtils.isEmpty(lessonName)
                    || StringUtils.isEmpty(teacherFirstName)
                    || StringUtils.isEmpty(teacherLastName)) {
                return;
            }
            //---
            dao.save(roomNumber, lessonName, lessonStartDate, lessonEndDate,
                    teacherFirstName, teacherLastName);
            //---
        }
    }
}
