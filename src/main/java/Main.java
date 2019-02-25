import controller.MenuController;
import utils.MyBatisUtils;

//точка входа
public class Main {
	public static void main(String[] args) {
		MyBatisUtils.initSqlSessionFactory();	// настройка подключения к бд
		new MenuController();	
	}
}
