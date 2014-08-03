package DAOHandler;

import DAO.*;
import model.Group;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

public class GroupDataManager {

    private GroupDAO groupDAO = new GroupDAOImpl();

    // Create foo
    public void saveNewGroup(Group group) {

        try {

            HibernateUtil.beginTransaction();
            groupDAO.save(group);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Get foo
    public Group getGroupCompleteData(String name) {

        Group group = null;

        try {

            HibernateUtil.beginTransaction();
            group = (Group) groupDAO.getGroupData(name);
            Hibernate.initialize(group.getEvents());
            Hibernate.initialize(group.getUsers());
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return group;

    }

}