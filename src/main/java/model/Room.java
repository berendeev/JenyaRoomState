package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private int id;
	private String number;
	private List<Lesson> lessons;

	public Room() {
	}

	public Room(int id, String number) {
		this.id = id;
		this.number = number;
	}

	public Room(String number, List<Lesson> lessons) {
		this(0, number);
		this.lessons = lessons;
	}

	public Room(String number) {
		this(number, new ArrayList<>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
