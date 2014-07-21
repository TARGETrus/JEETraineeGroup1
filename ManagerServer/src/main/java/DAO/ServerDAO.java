package DAO;

public interface ServerDAO {

    void insertUser();
    void selectUser();
    void removeUser();
    void updateUser();

    void insertEvent();
    void selectEvent();
    void removeEvent();
    void updateEvent();

    void insertGroup();
    void selectGrouo();
    void removeGroup();
    void updateGroup();

    void insertComment();
    void selectComment();
    void removeComment();
    void updateComment();

    //or just

    //void insert(String str);//parse str
    //void select(String str);
    //void remove(String str);
    //void update(String str);

}