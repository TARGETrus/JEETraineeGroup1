package DAOHandler;

import DAO.GroupDAO;
import model.Groupp;
import org.hibernate.HibernateException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            group = (List<Groupp>) groupDAO.findAll(Groupp.class);
        } catch (HibernateException e) {
            System.out.println("Hibernate exception: " + e.getMessage());
        }

        return group;

    }

    @Transactional
    public List<Groupp> getAllGroupsJoin() {

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

    @Transactional
    public void changeGroupName(String groupName, String name) {

        Groupp group = null;

        try {

            group = groupDAO.getGroupData(groupName);
            group.setGroupName(name);
            groupDAO.merge(group);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    @Transactional
    public void changeGroupAdmin(String groupName, String admin) {

        Groupp group = null;

        try {

            group = groupDAO.getGroupData(groupName);
            group.setGroupAdmin(admin);
            groupDAO.merge(group);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Delete foo
    @Transactional
    public boolean deleteGroup(Groupp group) {

        try {

            group = (Groupp) groupDAO.getCompleteGroupData(group.getGroupName());
            groupDAO.remove(group);

            return true;

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

            return false;

        }

    }

}