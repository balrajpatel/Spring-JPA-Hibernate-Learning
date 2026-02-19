
import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String puName= "pu-name";
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        //properties.put("hibernate.hbm2ddl.auto","create");

        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            //SELECT, UPDATE, DELETE jpql queries are there, no insert query,
            // use .persist() method of entity manager to insert.

            //String jpql = "select p from Product p";
            // Product is the obj of Persistence context   -- fetch all the attributes of the product entity from current context
            // native query select * from Product ==> get/fetch all records/or columns from table Product.

//            String jpql = "select p from Product p where p.price>:price AND p.name like :name";    // add : just before variable to
//            // consider it as a Parameter
//            TypedQuery<Product> q = em.createQuery(jpql, Product.class);
//            q.setParameter("name", "%a%");
//            q.setParameter("price",4);
//            List<Product> products = q.getResultList();
//            for(Product p : products){
//                System.out.println(p);
//            }

//
//            String jpql = "select AVG(p.price) from Product p";   // avg ,  sum,  min, max, count
//
//            TypedQuery<Double> q = em.createQuery(jpql, Double.class);
//            Double avg = q.getSingleResult();
//            System.out.println(avg);



//
//            String jpql = "select COUNT(p) from Product p";   // avg ,  sum,  min, max, count
//
//            TypedQuery<Long> q = em.createQuery(jpql, Long.class);
//            Long count = q.getSingleResult();
//            System.out.println(count);
//
//            String jpql = "select p.price, p.name from Product p";
//            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
//            q.getResultList().forEach(objects ->
//                    System.out.println(objects[0]+" "+objects[1]));



//            String jpql = """
//            select p.name, avg(price)
//            from Product p group by p.name
//            """;
//            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
//            q.getResultList().forEach(objects ->
//                    System.out.println(objects[0]+" "+objects[1]));


            //   q.getSingleResult()   // either returns the result or exception (it doesn't return null)
            // .getResultList<>() return result or null



            // a good way to handle the getSingleResult
            String jpql = "select p from Product p where p.name like 'Candy'";
            TypedQuery<Product> q = em.createQuery(jpql, Product.class);

            Optional<Product> p;
            try{
                p = Optional.of(q.getSingleResult());
            }catch (NoResultException e){
                p= Optional.empty();
            }
            p.ifPresentOrElse(System.out::println, () -> System.out.println("No result"));

            em.getTransaction().commit();
        }finally{
            em.close();
        }

    }
}
