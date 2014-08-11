package DAOHandler;

import DAO.*;
import model.Groupp;
import org.hibernate.HibernateException;

public class GroupDataManager {

    private GroupDAO groupDAO = new GroupDAOImpl();

    // Create foo
    public void saveNewGroup(Groupp group) {

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
    public Groupp getGroupData(String name) {

        Groupp group = null;

        try {

            HibernateUtil.beginTransaction();
            group = (Groupp) groupDAO.getGroupData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return group;

    }

    public Groupp getGroupCompleteData(String name) {

        Groupp group = null;

        try {

            HibernateUtil.beginTransaction();
            group = (Groupp) groupDAO.getCompleteGroupData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return group;

    }

}