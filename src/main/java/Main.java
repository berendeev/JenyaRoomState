import controller.MenuController;
import utils.MyBatisUtils;

public class Main {
	public static void main(String[] args) {
		MyBatisUtils.initSqlSessionFactory();
		new MenuController();
	}
}
