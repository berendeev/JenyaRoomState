package controller;

import daoimpl.Dao;
import model.Room;

public class FindController {
    private Dao dao;

    public FindController(Dao dao) {
        this.dao = dao;
    }

    public Room findByRoom(String roomNumber) {
        return dao.getRoomByNumber(roomNumber);
    }
}
