package mappers;

import model.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoomMapper {

    @Select("SELECT * FROM room WHERE number = #{number}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(property = "lessons", column = "id", javaType = List.class,
                    many = @Many(select = "mappers.LessonMapper.getLessonByRoomId", fetchType = FetchType.LAZY))
    })
    Room getRoomByNumber(String number);

    @Insert("INSERT INTO room (number) VALUES (#{number})")
    @Options(useGeneratedKeys = true)
    void insertRoom(Room room);

}
