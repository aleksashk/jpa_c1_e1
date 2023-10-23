package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Passport;
import by.flameksandr.jpa_c1_e1.entities.Person;
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
        props.put("hibernate.hbm2ddl.auto", "create");//create, none, update

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName),
                        props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Person person = new Person();
            person.setName("Simon");

            Passport passport = new Passport();
            passport.setNumber("ABC12345");

            person.setPassport(passport);

            em.persist(person);
            em.persist(passport);

            TypedQuery<Person> query = em.createQuery("select p from  Person p where p.passport.number = :number", Person.class);
            query.setParameter("number", "ABC12345");
            System.out.println(query.getResultList());

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
