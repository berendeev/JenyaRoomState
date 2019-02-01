package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Room;
import view.MenuView;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuController {
	private JFrame frame;
	private JButton addNew;
	private JButton findByRoom;

	private List<Room> rooms;

	public MenuController() {
		rooms = loadData();

		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		addNew = new JButton("add new");
		addNew.addActionListener(e -> {
			frame.dispose();
			new AddElementController(rooms);
		});

		findByRoom = new JButton("find by room");
		findByRoom.addActionListener(e -> {
			frame.dispose();
			new FindByRoomController();
		});

		MenuView menuView = new MenuView(frame, addNew, findByRoom);
		menuView.draw();
	}

	private List<Room> loadData() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("Data").getFile());

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			Optional<String> dataOptional = reader.lines().reduce((s, s2) -> s + s2);
			if (dataOptional.isPresent()) {
				String dataString = dataOptional.get();
				return new Gson().fromJson(dataString, new TypeToken<List<Room>>() {}.getType());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

}
