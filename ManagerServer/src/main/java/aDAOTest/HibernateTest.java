package aDAOTest;

import DAO.HibernateUtil;
import model.User;

import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

        TestManager testManager = new TestManager();

        User testUser = new User();
        //testUser.setUserID(2);
        testUser.setUserName("name");
        testUser.setPassword("password");

        testManager.saveNewUser(testUser);

        User singleUser = testManager.findUserById(2);
        System.out.println(singleUser);

        testManager.mergeUser(singleUser);
        System.out.println(singleUser);

        List<User> allUsers = testManager.findAllUsers();
        System.out.println(allUsers.toString());

        HibernateUtil.closeFactory();

    }

}
