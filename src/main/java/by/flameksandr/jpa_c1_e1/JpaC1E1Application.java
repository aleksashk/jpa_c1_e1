package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Comment;
import by.flameksandr.jpa_c1_e1.entities.Post;
import by.flameksandr.jpa_c1_e1.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
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

            Comment comment1 = new Comment();
            comment1.setContent("Content comment 1");
            Comment comment2 = new Comment();
            comment2.setContent("Content comment 1");

            post.setComments(List.of(comment1, comment2));
            comment1.setPost(post);
            comment2.setPost(post);

            em.persist(post);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
