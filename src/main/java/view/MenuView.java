package view;

import controller.AddElementController;
import daoimpl.Dao;
import daoimpl.DaoImpl;

import javax.swing.*;
import java.awt.*;

public class MenuView {
    private JFrame frame;
    private JButton addNew;
    private JButton findByRoom;

    public MenuView() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addNew = new JButton("add new");
        addNew.addActionListener(e -> {
            frame.dispose();
            new AddElementView();
        });

        findByRoom = new JButton("find");
        findByRoom.addActionListener(e -> {
            frame.dispose();
            new FindView();
        });

        draw();
    }

    private void draw() {
        frame.setTitle("Menu");
        frame.setBounds(100, 100, 300, 300);
        //---

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel addNewPanel = new JPanel(new FlowLayout());
        addNewPanel.add(new JLabel("Add new element"));
        addNewPanel.add(addNew);

        JPanel findByRoomPanel = new JPanel(new FlowLayout());
        findByRoomPanel.add(new JLabel("Find by room"));
        findByRoomPanel.add(findByRoom);

        panel.add(addNewPanel);
        panel.add(findByRoomPanel);
        //---
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
