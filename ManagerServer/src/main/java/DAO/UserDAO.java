package DAO;

import model.User;

public interface UserDAO extends GenericDAO<User> {

    public User getLoginData(String name);

}