package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Employee;
import by.flameksandr.jpa_c1_e1.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class JpaC1E1Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(),
                        new HashMap());

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Employee employee = em.find(Employee.class, 1);
            System.out.println(employee);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
