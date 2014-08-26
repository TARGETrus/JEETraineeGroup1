package DAOHandler;

import DAO.*;
import model.Event;
import model.Groupp;
import model.User;
import org.hibernate.HibernateException;

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
            group = (List<Groupp>) groupDAO.findAll();
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
            groupDAO.update(group);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeGroupName(String groupName, String name) {

        Groupp group = null;

        try {

            HibernateUtil.beginTransaction();
            group = groupDAO.getGroupData(groupName);

            group.setGroupName(name);

            groupDAO.update(group);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeGroupAdmin(String groupName, String admin) {

        Groupp group = null;

        try {

            HibernateUtil.beginTransaction();
            group = groupDAO.getGroupData(groupName);

            group.setGroupAdmin(admin);

            groupDAO.update(group);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Delete foo
    public boolean deleteGroup(Groupp group) {

        try {

            HibernateUtil.beginTransaction();

            group = (Groupp) groupDAO.getCompleteGroupData(group.getGroupName());

            groupDAO.delete(group);

            HibernateUtil.commitTransaction();

            return true;

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

            return false;

        }

    }

}