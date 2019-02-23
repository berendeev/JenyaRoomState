package controller;

import view.MenuView;

import javax.swing.*;

public class MenuController {
	private JFrame frame;
	private JButton addNew;
	private JButton find;

	public MenuController() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		addNew = new JButton("add new");
		addNew.addActionListener(e -> {
			frame.dispose();
			new AddElementController();
		});

		find = new JButton("find");
		find.addActionListener(e -> {
			frame.dispose();
			new FindController();
		});

		MenuView menuView = new MenuView(frame, addNew, find);
		menuView.draw();
	}
}
