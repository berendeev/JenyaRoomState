package view;

import model.Lesson;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class FindView {
    public void drawMainWindow(JFrame frame, JLabel roomIdLabel, JTextField roomId, JButton findByRoom) {
        frame.setBounds(100, 200, 250, 200);

        Panel panel = new Panel(new GridLayout(1, 3, 2, 2));

        panel.add(roomIdLabel);
        panel.add(roomId);
        panel.add(findByRoom);

        frame.setContentPane(panel);
        frame.pack();
    }

    public void findByRoom(JFrame frame, Room room) {
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
