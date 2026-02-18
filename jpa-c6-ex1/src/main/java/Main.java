import Persistence.CustomPersistenceUnitInfo;
import entities.Comment;
import entities.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
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
            Post post = new Post();
            post.setTitle("Post 1");
            post.setContent("Post 1 Desc");
            Comment comment = new Comment();
            comment.setContent("Content comment 1");
            comment.setPost(post);

            Comment comment2 = new Comment();
            comment2.setContent("Content comment 2");
            comment2.setPost(post);

            post.setComments(List.of(comment,comment2));

            em.persist(post);
            //em.persist(comment);  done with cascade

            // both comments added in the context due to cascade
            // then commit mirrored it to the DB

            em.getTransaction().commit();
        }finally{
            em.close();
        }

    }
}
