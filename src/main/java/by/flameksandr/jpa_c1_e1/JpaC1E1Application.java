package by.flameksandr.jpa_c1_e1;


import by.flameksandr.jpa_c1_e1.entities.Product;
import by.flameksandr.jpa_c1_e1.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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

            //select, update, delete
            /*
            String jpql = "select p from Product p";
            String jpql = "select p from Product p where p.price > 5";
            String jpql = "select p from Product p where p.price > :price and p.name like :name";

            select p from Product p ===>  Fetch all the attributes fof the Product entity from the current context
            select * from Product ===> Fetch all the columns from the table product
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("price", 5);
            query.setParameter("name", "%a%");

            List<Product> resultList = query.getResultList();
            for (Product p : resultList) {
                System.out.println(p);
            }
            */

            /*
            String jpql = "select avg(p.price) from Product p";// AVG, MIN, MAX, SUM...
            TypedQuery<Double> query = em.createQuery(jpql, Double.class);

            Double avg = query.getSingleResult();

            System.out.println(avg);

            */

            String jpql = "select count(p) from Product p";// AVG, MIN, MAX, SUM...
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);

            Long count = query.getSingleResult();

            System.out.println(count);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
