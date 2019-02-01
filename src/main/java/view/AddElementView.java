package view;

import javax.swing.*;
import java.awt.*;

public class AddElementView {
	private JFrame frame;
	private JButton submit;
	private TextField roomNumber;
	private TextField lessonName;
	private JSpinner lessonStart;
	private JSpinner lessonEnd;
	private TextField teacherFirstName;
	private TextField teacherLastName;

	public AddElementView(JFrame frame, JButton submit, TextField roomNumber, TextField lessonName, JSpinner lessonStart,
						  JSpinner lessonEnd, TextField teacherFirstName, TextField teacherLastName) {
		this.frame = frame;
		this.submit = submit;
		this.roomNumber = roomNumber;
		this.lessonName = lessonName;
		this.lessonStart = lessonStart;
		this.lessonEnd = lessonEnd;
		this.teacherFirstName = teacherFirstName;
		this.teacherLastName = teacherLastName;
	}

	public void draw() {
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
//		frame.pack();
		frame.setVisible(true);
	}
}
