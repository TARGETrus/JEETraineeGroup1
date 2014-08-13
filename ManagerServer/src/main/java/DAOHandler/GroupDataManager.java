package DAOHandler;

import DAO.*;
import model.Groupp;
import org.hibernate.HibernateException;

import java.security.acl.Group;
import java.util.List;

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

    public List<Groupp> getAllGroups() {

        List<Groupp> group = null;

        try {

            HibernateUtil.beginTransaction();
            group = (List<Groupp>) groupDAO.findAll(Groupp.class);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return group;

    }

    // Modify foo
    public void modifyGroup(Groupp group) {

        try {

            HibernateUtil.beginTransaction();
            groupDAO.merge(group);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

}