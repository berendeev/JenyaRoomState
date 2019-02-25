package daoimpl;

import mappers.LessonMapper;
import mappers.RoomMapper;
import mappers.TeacherMapper;
import model.Lesson;
import model.Room;
import model.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import utils.MyBatisUtils;

import java.util.Date;
import java.util.List;

//класс работы с бд
public class DaoImpl {
    private static DaoImpl ourInstance = new DaoImpl(); //это для синглтона
    private SqlSessionFactory sqlSessionFactory; // сессия для транзакций

    private DaoImpl() {
        sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    }

    public static DaoImpl getInstance() {
        return ourInstance;
    }

    private SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

    public void save(String roomNumber, String lessonName, Date dateStart, Date dateEnd, String firstName, String lastName) {
        try (SqlSession session = getSession()) {
            try {
                Room room = session.getMapper(RoomMapper.class).getRoomByNumber(roomNumber);
                if (room == null) {
                    room = new Room(roomNumber);
                    session.getMapper(RoomMapper.class).insertRoom(room);
                }

                Teacher teacher = session.getMapper(TeacherMapper.class).getTeacherByName(firstName, lastName);
                if (teacher == null) {
                    teacher = new Teacher(firstName, lastName);
                    session.getMapper(TeacherMapper.class).insertTeacher(teacher);
                }

                Lesson lesson = session.getMapper(LessonMapper.class)
                        .getLessonByNameDateAndRoom(lessonName, dateStart, dateEnd, room.getId());
                if (lesson == null) {
                    lesson = new Lesson(lastName, dateStart, dateEnd, teacher);
                    session.getMapper(LessonMapper.class).insertLesson(lesson, room.getId(), teacher.getId());
                }
            } catch (RuntimeException exc) {
                session.rollback();
                throw exc;
            }
            session.commit();
        }
    }

     public Room getRoomByNumber(String roomNumber) {
        try (SqlSession sqlSession = getSession()) {
            Room room = sqlSession.getMapper(RoomMapper.class).getRoomByNumber(roomNumber);

            List<Lesson> lessons = sqlSession.getMapper(LessonMapper.class).getLessonByRoomId(room.getId());
            room.setLessons(lessons);

            return room;
        } catch (RuntimeException exc) {
            exc.printStackTrace();
            throw exc;
        }
    }
}
