package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Student;
import by.flameksandr.jpa_c1_e1.entities.keys.StudentKey;
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
        props.put("hibernate.hbm2ddl.auto", "none");//create, none, update

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName),
                        props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            StudentKey id = new StudentKey();
            id.setCode("ABC");
            id.setNumber(23);

//            Student student = new Student();
//            student.setId(id);
//            student.setName("Alex");
//
//            em.persist(student);
            Student student = em.find(Student.class, id);
            System.out.println(student);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
