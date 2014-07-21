package DAO;

import model.User;

public class HibernateTest {

    public static void main(String[] args) {

        ServerDAOImpl serverDAO = new ServerDAOImpl();

        // Create something to add
        User user = new User();
        user.setUserName("Test1");
        user.setPassword("pass1");

        User user2 = new User();
        user2.setUserName("Test1");
        user2.setPassword("pass1");
        user2.setUserID(1);

        serverDAO.insertUser(user);
        serverDAO.selectUser(3);
        serverDAO.updateUser(user);
        serverDAO.removeUser(user2);

        HibernateUtil.closeFactory();

    }

}
