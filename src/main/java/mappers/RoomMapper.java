package mappers;

import model.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface RoomMapper {

	@Select("SELECT * FROM room WHERE number = #{number}")
	Room getRoomByNumber(String number);

	@Insert("INSERT INTO room (number) VALUES (#{number})")
	@Options(useGeneratedKeys = true)
	void insertRoom(Room room);


}
