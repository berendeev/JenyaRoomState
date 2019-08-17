import utils.MyBatisUtils;
import view.MenuView;

//точка входа
public class Main {
    public static void main(String[] args) {
        MyBatisUtils.initSqlSessionFactory();
        new MenuView();
    }
}
