package jpac4ex1;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jpac4ex1.Entities.Employee;
import jpac4ex1.Entities.Product;
import jpac4ex1.Entities.Student;
import jpac4ex1.Entities.keys.StudentKey;
import jpac4ex1.Persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String puName= "pu-name";
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto","none");


        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

//         var p = new Product();
//         p.setCode("ABC");
//         p.setNumber(10);
//         p.setColor("Red");
//         em.persist(p);


              StudentKey id = new StudentKey();
              id.setCode("ABC");
              id.setNumber(11L);
//
//            var s = new Student();
//            s.setId(id);
//            s.setName("Balraj");
//            em.persist(s);
            var s = em.find(Student.class, id);
            System.out.println(s);


            em.getTransaction().commit();
        } finally {
        em.close();
        }

    }

}
