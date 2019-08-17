package daoimpl;

import model.Room;

import java.util.Date;

public interface Dao {
    Room getRoomByNumber(String roomNumber);

    void save(
            String roomNumber,
            String lessonName,
            Date dateStart,
            Date dateEnd,
            String firstName,
            String lastName);
}
