package DAO;

import model.User;

public interface UserDAO extends GenericDAO<User> {

    public User getUserData(String name);

}