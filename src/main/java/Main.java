import models.User;
import org.hibernate.Session;
import services.UserService;
import utils.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        Session session=HibernateSessionFactoryUtil.getSessionFactory().openSession();

        UserService userService=new UserService();
        User user=new User("Pasha",20);
        user.setAge(34);
        userService.saveUser(user);
        user.setName("Olaz");
        userService.updateUser(user);
        userService.deleteUser(user);
        ArrayList<User> users=new ArrayList<User>();
        for (int i=0;i<3;i++){
            users.add(userService.findAllUsers().get(i));
        }
        for (User item:users){
            System.out.println(item);
        }
    }
}
