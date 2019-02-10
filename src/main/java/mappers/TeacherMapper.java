package mappers;

import model.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

	@Select("SELECT * FROM teacher WHERE first_name=#{firstName} AND last_name=#{lastName}")
	Teacher getTeacherByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Insert("INSERT INTO teacher (first_name, last_name) VALUES (#{firstName}, #{lastName})")
	@Options(useGeneratedKeys = true)
	void insertTeacher(Teacher teacher);
}
