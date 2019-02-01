package model;

import java.util.Date;

public class Lesson {
	private String name;
	private Date dateStart;
	private Date dateEnd;
	private Teacher teacher;

	public Lesson(String name, Date dateStart, Date dateEnd, Teacher teacher) {
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.teacher = teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
