package controller;

import daoimpl.DaoImpl;
import org.apache.commons.lang3.StringUtils;
import view.AddElementView;

import javax.swing.*;
import java.awt.*;
import java.util.Date;


public class AddElementController {
    private JFrame frame;
    private JButton submit;

    //---
    private TextField roomNumber;

    private TextField lessonName;
    private JSpinner lessonStart;
    private JSpinner lessonEnd;

    private TextField teacherFirstName;
    private TextField teacherLastName;

    public AddElementController() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        roomNumber = new TextField();
        lessonName = new TextField();

        lessonStart = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditorLessonStart = new JSpinner.DateEditor(lessonStart, "HH:mm:ss");
        lessonStart.setEditor(timeEditorLessonStart);
        lessonStart.setValue(new Date());

        lessonEnd = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditorLessonEnd = new JSpinner.DateEditor(lessonEnd, "HH:mm:ss");
        lessonEnd.setEditor(timeEditorLessonEnd);
        lessonEnd.setValue(new Date());

        teacherFirstName = new TextField();
        teacherLastName = new TextField();

        submit = new JButton("submit");
        submit.addActionListener(e -> {
            Date lessonStartDate = (Date) lessonStart.getModel().getValue();
            Date lessonEndDate = (Date) lessonEnd.getModel().getValue();
            if (lessonStartDate == null || lessonEndDate == null || lessonStartDate.after(lessonEndDate)) {
                JFrame error = new JFrame("Error");
                error.add(new JLabel("Date not valid"));
                error.pack();
                error.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                error.setVisible(true);
            } else {
                if (StringUtils.isEmpty(roomNumber.getText())
                        || StringUtils.isEmpty(lessonName.getText())
                        || StringUtils.isEmpty(teacherFirstName.getText())
                        || StringUtils.isEmpty(teacherLastName.getText())) {
                    return;
                }
                //---
                DaoImpl dao = DaoImpl.getInstance();
                dao.save(roomNumber.getText(), lessonName.getText(), lessonStartDate, lessonEndDate,
                        teacherFirstName.getText(), teacherLastName.getText());
                //---
                frame.dispose();
                new MenuController();
            }
        });

        AddElementView addElementView = new AddElementView(frame, submit, roomNumber, lessonName, lessonStart, lessonEnd,
                teacherFirstName, teacherLastName);
        addElementView.draw();
    }
}
