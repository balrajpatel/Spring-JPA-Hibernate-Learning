import Persistence.CustomPersistenceUnitInfo;
import entities.Passport;
import entities.Person;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String puName= "pu-name";
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto","create");


        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            Person person = new Person();
            Passport passport = new Passport();
            passport.setNumber("ABC123");
            person.setPassport(passport);
            person.setName("John Doe");
            passport.setPerson(person);
            em.persist(person);
            // em.persist(passport); done with cascade

            TypedQuery<Person> query = em.createQuery("select p from Person p where p.passport.number= :number", Person.class);
            query.setParameter("number", person.getPassport().getNumber());
            System.out.println(query.getSingleResult());



            User user = new User();
            user.setName("John Doe");
            user.setDescription("my name is John Doe");
            em.persist(user);
            em.getTransaction().commit();
        }finally{
            em.close();
        }

    }
}
