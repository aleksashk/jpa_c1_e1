package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Product;
import by.flameksandr.jpa_c1_e1.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
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

            //create
            String jpql = "select p from Product p";

            //select p from Product p ===>  Fetch all the attributes fof the Product entity from the current context
            //select * from Product ===> Fetch all the columns from the table product
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);

            List<Product> resultList = query.getResultList();
            for (Product p : resultList) {
                System.out.println(p);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
