package com.hibernate.jpac2ex1;

import com.hibernate.jpac2ex1.Entities.Employee;
import com.hibernate.jpac2ex1.Persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),new HashMap<>());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, 1);      //employee instance is in the persistence context
            // so any changes in it , is inside the context

            // Employee employee = new Employee()   // any changes in it will not get mirrored in the end
            // since its not in the context , and its the  context which gets mirrored

            // for these cases .merge() exist, since it works for entity which exists in the DB but not in the Context
            // //so it adds in the context.
            System.out.println(employee);
            employee.setName("Laurentiu Spilca");    // update happened in the context only right now

            employee.setName("John Doe");
            employee.setName("Laurentiu Spilca");
            // since in the start and in the end no change in same
            // same context at start and at end

            //so no mirroring to DB  so no query executed;

            System.out.println(employee);
            //add this to Persistence context,  to insert query to DB
            em.getTransaction().commit();   // this line mirrors the Persistence context to DB and execute the Query.
            // will se all the changes happened in the context and map/mirror them in the
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally { em.close(); }

        // Persistence is the place where Hibernate/jpa works with.

        //   .find() find by PK from context, if not found then query executed to find from DB
        // .persist  add an entity in the context
        //  .remove()  mark entity for removal from context
        //  .merge()  Merges an entity from outside context to the context  // in it entity should already exist in DB
        //  .detach()  taking the entity out of the context.
        //  .refresh()  mirror the context from database
        //  .getReference()

    }
}
