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

		// этот блок кода - считай по ссылке перешел(тут по кнопке =) )
		addNew = new JButton("add new");
		addNew.addActionListener(e -> {
			frame.dispose();
			new AddElementController(); 
		});

		// тоже самое только в другое место, сработает только после нажатия
		find = new JButton("find");
		find.addActionListener(e -> {
			frame.dispose();
			new FindController();
		});

		// отображение маню
		MenuView menuView = new MenuView(frame, addNew, find);
		menuView.draw();
	}
}
