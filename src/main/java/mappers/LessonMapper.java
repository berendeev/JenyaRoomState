package mappers;

import model.Lesson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface LessonMapper {

	@Select("SELECT * FROM lesson " +
			"WHERE name=#{name} AND date_start=#{start} AND date_end=#{end} AND room_id=#{roomId}")
	Lesson getLessonByNameDateAndRoom(@Param("name") String lessonName, @Param("start") Date dateStart,
									  @Param("end") Date dateEnd, @Param("roomId") int roomId);

	@Insert("INSERT INTO lesson(name, date_start, date_end, room_id, teacher_id) " +
			"VALUES(#{lesson.name}, #{lesson.dateStart}, #{lesson.dateEnd}, #{roomId}, #{teacherId})")
	@Options(useGeneratedKeys = true, keyColumn = "lesson.id")
	void insertLesson(@Param("lesson") Lesson lesson, @Param("roomId") int roomId, @Param("teacherId") int teacherId);
}
