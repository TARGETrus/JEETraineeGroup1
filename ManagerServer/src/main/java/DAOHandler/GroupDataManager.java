package DAOHandler;

import DAO.*;
import model.Groupp;
import org.hibernate.HibernateException;

public class GroupDataManager {

    private GroupDAO groupDAO = new GroupDAOImpl();

    // Create foo
    public void saveNewGroup(Groupp groupp) {

        try {

            HibernateUtil.beginTransaction();
            groupDAO.save(groupp);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Get foo
    public Groupp getGroupCompleteData(String name) {

        Groupp groupp = null;

        try {

            HibernateUtil.beginTransaction();
            groupp = (Groupp) groupDAO.getCompleteGroupData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return groupp;

    }

}