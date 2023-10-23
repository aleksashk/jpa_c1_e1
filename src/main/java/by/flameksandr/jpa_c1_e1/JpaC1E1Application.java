package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Book;
import by.flameksandr.jpa_c1_e1.entities.ElectronicDevice;
import by.flameksandr.jpa_c1_e1.entities.Product;
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

            var sql = "select p from Product p";
            em.createQuery(sql, Product.class)
                    .getResultList().forEach(System.out::println);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
