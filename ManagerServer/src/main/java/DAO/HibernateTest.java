package DAO;

import model.User;

public class HibernateTest {

    public static void main(String[] args) {

        ServerDAOImpl serverDAO = new ServerDAOImpl();

        // Create something to add
        User user = new User();
        user.setUserName("Test1");
        user.setPassword("pass1");

        serverDAO.insertUser(user);

    }

}
