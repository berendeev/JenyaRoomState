package view;

import controller.FindController;
import daoimpl.DaoImpl;
import model.Lesson;
import model.Room;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class FindView {
    private FindController findController;
    private JFrame frame;
    private JLabel roomIdLabel;
    private JTextField roomId;
    private JButton findByRoom;

    public FindView() {
        findController = new FindController(DaoImpl.getInstance());
        frame = new JFrame("Find");

        roomIdLabel = new JLabel("room");
        roomId = new JTextField();
        findByRoom = new JButton("find by room");

        //by room
        findByRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StringUtils.isEmpty(roomId.getText())) {
                    //find by room number
                    frame.dispose();
                    Room room = findController.findByRoom(roomId.getText());
                    findByRoom(room);
                }
            }
        });

        //settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        drawMainWindow();
    }

    private void drawMainWindow() {
        frame.setBounds(100, 200, 250, 200);

        Panel panel = new Panel(new GridLayout(1, 3, 2, 2));

        panel.add(roomIdLabel);
        panel.add(roomId);
        panel.add(findByRoom);

        frame.setContentPane(panel);
        frame.pack();
    }

    private void findByRoom(Room room) {
        frame = new JFrame("Find by room");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setBounds(100, 200, 250, 200);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM hh:mm");

        String[] columnNames = {"lesson id", "lesson name", "date start", "date end"};
        Object[][] data = new Object[room.getLessons().size()][];
        for (int i = 0; i < room.getLessons().size(); i++) {
            Lesson lesson = room.getLessons().get(i);

            String dateStart = format.format(lesson.getDateStart());
            String dateEnd = format.format(lesson.getDateEnd());

            data[i] = new Object[]{lesson.getId(), lesson.getName(), dateStart, dateEnd};
        }
        JTable table = new JTable(data, columnNames);

//        frame.setContentPane();
        frame.add(new JScrollPane(table));
        frame.pack();
    }
}
