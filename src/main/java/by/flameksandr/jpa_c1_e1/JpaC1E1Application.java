package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Comment;
import by.flameksandr.jpa_c1_e1.entities.Post;
import by.flameksandr.jpa_c1_e1.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class JpaC1E1Application {

    public static void main(String[] args) {
        String puName = "pu-name";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");//create, none, update

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName),
                        props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Post post = new Post();
            post.setTitle("Post 1");
            post.setContent("Post 1 desc");

            Comment comment = new Comment();
            comment.setContent("Content comment 1");
            comment.setPost(post);

            em.persist(comment);
            em.persist(post);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
