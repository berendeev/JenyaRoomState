package controller;

import model.Room;
import view.MenuView;

import javax.swing.*;
import java.util.List;

public class MenuController {
	private JFrame frame;
	private JButton addNew;
	private JButton findByRoom;

	private List<Room> rooms;

	public MenuController() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		addNew = new JButton("add new");
		addNew.addActionListener(e -> {
			frame.dispose();
			new AddElementController();
		});

		findByRoom = new JButton("find by room");
		findByRoom.addActionListener(e -> {
			frame.dispose();
			new FindByRoomController();
		});

		MenuView menuView = new MenuView(frame, addNew, findByRoom);
		menuView.draw();
	}
}
