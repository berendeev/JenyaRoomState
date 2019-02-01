package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private String number;
	private List<Lesson> lessons;

	public Room(String number, List<Lesson> lessons) {
		this.number = number;
		this.lessons = lessons;
	}

	public Room(String number) {
		this(number, new ArrayList<>());
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
}
