package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Employee;
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
        props.put("hibernate.hbm2ddl.auto", "update");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName),
                        props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            //find vs getReference
            var e1 = em.find(Employee.class, 1);
//            var e2 = em.getReference(Employee.class, 1);

//            em.persist();       -> Adding an entity in the context
//            em.find();          -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();        -> Marking entity for removal
//            em.merge();         -> Merges an entity from outside the context to the context
//            em.refresh();       -> Mirror the context from the adtabase
//            em.detach();        -> Taking the entity out of the context
//            em.getReference()   ->;

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
