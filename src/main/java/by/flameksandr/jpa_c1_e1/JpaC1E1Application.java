package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Group;
import by.flameksandr.jpa_c1_e1.entities.User;
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

            User user1 = new User();
            user1.setName("Patrik");
            User user2 = new User();
            user2.setName("Helen");

            Group group1 = new Group();
            group1.setName("first_group");
            group1.setUsers(List.of(user1, user2));

            Group group2 = new Group();
            group2.setName("second_group");
            group2.setUsers(List.of(user2));

            user1.setGroups(List.of(group1, group2));
            user2.setGroups(List.of(group2));

            em.persist(user1);
            em.persist(user2);
            em.persist(group1);
            em.persist(group2);


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
