package view;

import controller.AddElementController;
import daoimpl.DaoImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AddElementView {
    private AddElementController addElementController;

    private JFrame frame;
    private JButton submit;
    private TextField roomNumber;
    private TextField lessonName;
    private JSpinner lessonStart;
    private JSpinner lessonEnd;
    private TextField teacherFirstName;
    private TextField teacherLastName;

    public AddElementView() {
        addElementController = new AddElementController(DaoImpl.getInstance());

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

            try {
                addElementController.addNewElement(
                        lessonStartDate,
                        lessonEndDate,
                        roomNumber.getText(),
                        lessonName.getText(),
                        teacherFirstName.getText(),
                        teacherLastName.getText());

                frame.dispose();
                new MenuView();

            } catch (IllegalArgumentException exc) {
                JFrame error = new JFrame("Error");
                error.add(new JLabel("Date not valid"));
                error.pack();
                error.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                error.setVisible(true);
            }
        });

        draw();
    }

    private void draw() {
        frame.setTitle("Add new");
        frame.setSize(new Dimension(400, 500));

        JPanel panel = new JPanel();
        panel.setAlignmentX(1);

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxLayout);

        JPanel roomNumberPanel = new JPanel(new FlowLayout());
        roomNumberPanel.add(new JLabel("Number of the room"));
        roomNumber.setPreferredSize(new Dimension(150, 25));
        roomNumberPanel.add(roomNumber);

        JPanel lessonNamePanel = new JPanel(new FlowLayout());
        lessonNamePanel.add(new JLabel("Lesson name"));
        lessonName.setPreferredSize(new Dimension(150, 25));
        lessonNamePanel.add(lessonName);

        JPanel lessonStartPanel = new JPanel(new FlowLayout());
        lessonStartPanel.add(new JLabel("Lesson start date"));
        lessonStart.setPreferredSize(new Dimension(150, 25));
        lessonStartPanel.add(lessonStart);

        JPanel lessonEndPanel = new JPanel(new FlowLayout());
        lessonEndPanel.add(new JLabel("Lesson end date"));
        lessonEnd.setPreferredSize(new Dimension(150, 25));
        lessonEndPanel.add(lessonEnd);

        JPanel teacherFirstNamePanel = new JPanel(new FlowLayout());
        teacherFirstNamePanel.add(new JLabel("Teacher first name"));
        teacherFirstName.setPreferredSize(new Dimension(150, 25));
        teacherFirstNamePanel.add(teacherFirstName);

        JPanel teacherLastNamePanel = new JPanel(new FlowLayout());
        teacherLastNamePanel.add(new JLabel("Teacher last name"));
        teacherLastName.setPreferredSize(new Dimension(150, 25));
        teacherLastNamePanel.add(teacherLastName);

        panel.add(roomNumberPanel);
        panel.add(lessonNamePanel);
        panel.add(lessonStartPanel);
        panel.add(lessonEndPanel);
        panel.add(teacherFirstNamePanel);
        panel.add(teacherLastNamePanel);
        panel.add(submit);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
