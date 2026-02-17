import Entities.Product;
import Persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;


public class Main {
    public static void main(String[] args) {

        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");   // it helps to create the EntityManager.

        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),new HashMap<>());

        EntityManager em = emf.createEntityManager();    // it is the manager of Persistence context where entities are stored
        // it represents the Persistence context.

        try {
            em.getTransaction().begin();
            Product product = new Product();
            product.setId(2L);
            product.setName("Chocolate");
            em.persist(product);
            //add this to Persistence context,  to insert query to DB
            em.getTransaction().commit();   // this line mirrors the Persistence context to DB and execute the Query.
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally { em.close(); }

    }
}
