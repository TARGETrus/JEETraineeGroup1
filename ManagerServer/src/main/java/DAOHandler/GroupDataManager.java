package DAOHandler;

import DAO.*;
import model.Groupp;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.acl.Group;
import java.util.List;

@Service
public class GroupDataManager {

    @Autowired
    private GroupDAO groupDAO;

    // Create foo
    @Transactional
    public void saveNewGroup(Groupp group) {

        try {

            groupDAO.save(group);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Get foo
    @Transactional
    public Groupp getGroupData(String name) {

        Groupp group = null;

        try {

            group = (Groupp) groupDAO.getGroupData(name);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return group;

    }

    @Transactional
    public Groupp getGroupCompleteData(String name) {

        Groupp group = null;

        try {

            group = (Groupp) groupDAO.getCompleteGroupData(name);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return group;

    }

    @Transactional
    public List<Groupp> getAllGroups() {

        List<Groupp> group = null;

        try {

            group = (List<Groupp>) groupDAO.findAll();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return group;

    }

    // Modify foo
    @Transactional
    public void modifyGroup(Groupp group) {

        try {

            groupDAO.merge(group);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Delete foo
    @Transactional
    public void deleteGroup(Groupp group) {

        try {

            groupDAO.delete(group);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

}