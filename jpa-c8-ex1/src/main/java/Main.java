
import entities.Book;
import entities.ElectronicDevice;
import entities.Product;
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
        properties.put("hibernate.hbm2ddl.auto","create");
        // i real world never allow hibernate  to create table,
        //you create a table and, work on it

        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

//            Book book = new Book();
//            book.setAuthor("Laur");
//            book.setId(1);
//            em.persist(book);
//
//            ElectronicDevice device = new ElectronicDevice();
//            device.setId(2);
//            device.setVoltage(100);
//            em.persist(device);


            // SingleTable inheritance Strategy.  // disadv null values

            //Remember we have to think the ORM way,and with that the Discriminator column doesn't exist to refer.
            // so we can get specific implementation of Product directly by referring , don't need to use discriminator column.

//            var sql = "SELECT b FROM Book b";   //see don't need to go through Product and then access Book
//            //thinking that only Product table exist, think the ORM way, and in it Book entity exists.
//            // but internally it will  pur the condition on DType/ProductType in our case.
//            em.createQuery(sql,Book.class).getResultList().forEach(System.out::println);


            //Joined inheritance strategy.  // adv it keeps records separate in DB
            // internally join query will always be executed to get the value specific implementation.
            // since we want full object of child class, also the attributes of (parent class) Product,
            // and we get that from parent table
//            Book book = new Book();
//            book.setAuthor("Laur");
//            book.setId(1);
//            em.persist(book);
//
//            ElectronicDevice device = new ElectronicDevice();
//            device.setId(2);
//            device.setVoltage(100);
//            em.persist(device);


           // table per class strategy creates a table per child class and attributes of parent are in each child.
            // duplicates the parent attribute , purpose of inheritance looses
            // when we query parent class, union query is used.  and in jpql no union query is there , mysql has it
            // very rarely used,
            Book book = new Book();
            book.setAuthor("Laur");
            book.setId(1);
            book.setName("Spring JPA");
            em.persist(book);

            ElectronicDevice device = new ElectronicDevice();
            device.setId(2);
            device.setVoltage(100);
            device.setName("TV");
            em.persist(device);

            var sql = "SELECT p FROM Product p";
            em.createQuery(sql, Product.class).getResultList().forEach(System.out::println);

            em.getTransaction().commit();
        }finally{
            em.close();
        }

        /*
        In JPA:

        Queries on a parent entity automatically include all subclasses.
        This is by design.
        So:
        SELECT p FROM Product p
        means:
        Give me all Products,
        including all subclasses of Product.
         */
    }
}
