package com.hibernate.jpac3ex1;

import com.hibernate.jpac3ex1.Entities.Employee;
import com.hibernate.jpac3ex1.Persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
//            var e1  = em.find(Employee.class,1);  // query will execute, but will return null, since  nothing in database
//            System.out.println(e1);      //
//            var e1 = new Employee();
//            e1.setId(1);
//            e1.setName("John Doe");
//            e1.setAddress("Maitri Nagar");
//            em.persist(e1);
//            var e3 = em.find(Employee.class, e1.getId());  // this query will not execute since entity  already exists in context



            // find vs getReference;
//            var e3 = em.getReference(Employee.class, 1);
            // it returns the proxy obj which contains the id only
            // it executes the query only when the entity is accessed
//            System.out.println(e3);
//
//            e3.setName("Daniel");
//            System.out.println("Before  " +e3);
//
//            em.refresh(e3); // undo to the state of that entity as the start of context
//            System.out.println("After  "+ e3);


            // ID generatedValue strategies.
            var e = new Employee();
            e.setName("John Doe");
            e.setAddress("Main Street");
            em.persist(e);

            // every DB has table so TABLE strategy works for every DB
            //  sequence (mysql don't have so it moves to Table strategy)
            // oracle supports sequence, but doesn't support identity;

            // UUID  universely unique identifiers.
            // generally used when you have v v v large data, long might be not enough.
            // use with fronted so that user can't guess the value and get the data  of another user.
            // they are difficult to guess

            // Best strategy.  int/long  for Id , for internal search , query
            //   UUID for Id  to work with frontend




            em.getTransaction().commit();
        } finally {
        em.close();
        }

    }

}
