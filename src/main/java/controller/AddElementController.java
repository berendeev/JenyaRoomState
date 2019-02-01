package controller;

import com.google.gson.Gson;
import model.Lesson;
import model.Room;
import model.Teacher;
import org.apache.commons.lang3.StringUtils;
import view.AddElementView;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public class AddElementController {
	List<Room> rooms;
	//--
	private JFrame frame;
	private JButton submit;

	//---
	private TextField roomNumber;

	private TextField lessonName;
	private JSpinner lessonStart;
	private JSpinner lessonEnd;

	private TextField teacherFirstName;
	private TextField teacherLastName;

	public AddElementController(final List<Room> rooms) {
		this.rooms = rooms;

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
				Room room = getRoomByNumber(roomNumber.getText());
				if (room == null) {
					room = new Room(roomNumber.getText());
					rooms.add(room);
				}

				Lesson lesson = getLesson(room, lessonName.getText(), lessonStartDate, lessonStartDate,
						teacherFirstName.getText(), teacherLastName.getText());
				if (lesson == null) {
					room.getLessons().add(new Lesson(lessonName.getText(), lessonStartDate, lessonStartDate,
							new Teacher(teacherFirstName.getText(), teacherLastName.getText())));
				}

				save();
				//---
				frame.dispose();
				new MenuController();
			}
		});

		AddElementView addElementView = new AddElementView(frame, submit, roomNumber, lessonName, lessonStart, lessonEnd,
				teacherFirstName, teacherLastName);
		addElementView.draw();
	}

	private Lesson getLesson(Room room, String name, Date start, Date end, String teacherFirstName, String teacherLastName) {
		for (Lesson current : room.getLessons()) {
			if (current.getName().equals(name)
					&& current.getDateStart().equals(start)
					&& current.getDateEnd().equals(end)
					&& current.getTeacher().getFirstName().equals(teacherFirstName)
					&& current.getTeacher().getLastName().equals(teacherLastName)) {
				return current;
			}
		}
		return null;
	}

	private Room getRoomByNumber(String number) {
		for (Room current : rooms) {
			if (current.getNumber().equals(number)) {
				return current;
			}
		}
		return null;
	}

	private void save() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("Data").getFile());

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			String jsonData = new Gson().toJson(rooms);
			writer.write(jsonData);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
