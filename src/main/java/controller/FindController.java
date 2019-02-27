package controller;

import daoimpl.DaoImpl;
import model.Room;
import org.apache.commons.lang3.StringUtils;
import view.FindView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindController {
    private JFrame frame;
    private FindView findView;

    public FindController() {
        frame = new JFrame("Find");

        //by room
        JLabel roomIdLabel = new JLabel("room");
        JTextField roomId = new JTextField();
        JButton findByRoom = new JButton("find by room");

        // код сработает после нажатия на кнопку
        findByRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StringUtils.isEmpty(roomId.getText())) {
                    //find by room number
                    frame.dispose();
                    findByRoom(roomId.getText());   // запустит функцию поиска, она ниже
                }
            }
        });

        //by ??

        //draw
        findView = new FindView();  // тут создается вью
        findView.drawMainWindow(frame, roomIdLabel, roomId, findByRoom);    // отрисовка 

        //settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void findByRoom(String roomNumber) {    // функция поиска
        frame = new JFrame("Find by room");

        DaoImpl dao = DaoImpl.getInstance();    // для операций с бд
        Room room = dao.getRoomByNumber(roomNumber);    // вытаскивает аудитории из бд с данным номером

        findView.findByRoom(frame, room);   // отрисовка результата

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
