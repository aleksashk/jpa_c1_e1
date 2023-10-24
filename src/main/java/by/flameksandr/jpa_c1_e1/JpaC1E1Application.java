package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class JpaC1E1Application {

    public static void main(String[] args) {
        String puName = "pu-name";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none");//create, none, update

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName),
                        props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//        String jpql = """
//                select s, e from Student s inner join s.enrollments e
//                """;
//            String jpql = """
//                    select s, e from Student s join s.enrollments e
//                    """;
//            String jpql = """
//                    select s, e from Student s, Enrollment e
//                    where s.id = e.student.id
//                    """;

            String jpql = """
                    select s, e from Student s, Enrollment e
                    where s = e.student
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.getResultList().forEach(o -> System.out.println(o[0] + " " + o[1]));

            /**
             * [s1, e1],
             * [s2, e2],
             */

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
