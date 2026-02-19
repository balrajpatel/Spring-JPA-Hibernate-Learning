
import entities.Group;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String puName= "pu-name";
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        //properties.put("hibernate.hbm2ddl.auto","create");
        // i real world never allow hibernate  to create table,
        //you create a table and, work on it

        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            User u1 = new User();
            u1.setName("User 1");
            User u2 = new User();
            u2.setName("User 2");

            Group group1 = new Group();
            group1.setName("Group 1");
            Group group2 = new Group();
            group2.setName("Group 2");

            group1.setUsers(List.of(u1, u2));
            group2.setUsers(List.of(u2));
            u1.setGroups(List.of(group1));
            u2.setGroups(List.of(group1,group2));
            // In a bidirectional Relationship Many to Many, just remember to have correct connections.

            em.persist(u1);
            em.persist(u2);
            em.persist(group1);
            em.persist(group2);

            em.getTransaction().commit();
        }finally{
            em.close();
        }

    }
}
