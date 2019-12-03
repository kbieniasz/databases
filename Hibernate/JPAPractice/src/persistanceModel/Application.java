package persistanceModel;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Application {


    public static void main(String[] args) {

        Session session = HibernateFactory.getSessionFactory().openSession();
        session.close();
        /*
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Product product1 = session.get(Product.class, "Fisher 360");
            Product product2 = session.get(Product.class, "Fisher 480");
            Product product3 = session.get(Product.class, "Fisher Snowboard");


            Invoice invoice1 = new Invoice();
            Invoice invoice2 = new Invoice();
            invoice1.addProduct(product1);
            invoice1.addProduct(product2);
            invoice2.addProduct(product2);
            invoice2.addProduct(product3);
            session.save(invoice1);
            session.save(invoice2);


            session.update(product1);
            session.update(product2);
            session.update(product3);
            tx.commit();
        }

         */



        /*
        try (Session session = HibernateFactory.getSessionFactory().openSession())
        {
            Transaction tx = session.beginTransaction();
            Product product1 =session.get(Product.class, "Fisher 360");
            Query  query1 = session.createQuery("select c from Categories c where c.categoryID=:catID");
            query1.setParameter("catID", product1.getCategory().getCategoryID());
            // wlaściwie problem wyłuskiwania jest sztuczny, bo mamy odpowiednie pola zarowno w produkcie jak i kategorii

            Category associatedCategory = (Category) query1.getResultList().get(0);

            Query query2 = session.createQuery("select p from Products p where p.category=:cat");
            query2.setParameter("cat", associatedCategory);
            List<Product> associatedProduct = query2.getResultList();
            tx.commit();
            System.out.println(associatedCategory.getName());
            for(Product product : associatedProduct )
            {
                System.out.println(product.getProductName());
            }

        }

         */



        /*

        {
            Transaction tx = session.beginTransaction();
            Product product1 = new Product("Fisher 360",5);
            Product product2 = new Product("Fisher 480",5);
            Product product3 = new Product("Fisher Snowboard",5);
            session.save(product1);
            session.save(product2);
            session.save(product3);
            Supplier supplier = new Supplier("Fisher","Bergisel 100","Innsbruck");
            session.save(supplier);
            Category skiing = new Category("Skiing");
            Category snowboarding = new Category("Snowboarding");
            session.save(skiing);
            session.save(snowboarding);

            tx.commit();


        }

        System.out.println("Udane dodanie do bazy");


        try (Session session = HibernateFactory.getSessionFactory().openSession())
        {
            Transaction tx = session.beginTransaction();
            Supplier supplier = session.get(Supplier.class, "Fisher");
            supplier.addProductToProductSet(session.get(Product.class, "Fisher 360"));
            supplier.addProductToProductSet(session.get(Product.class, "Fisher 480"));
            supplier.addProductToProductSet(session.get(Product.class, "Fisher Snowboard"));

            Category skiing = session.get(Category.class, 7);
            skiing.addProductToProductSet(session.get(Product.class, "Fisher 360"));
            skiing.addProductToProductSet(session.get(Product.class, "Fisher 480"));
            Category snowboarding = session.get(Category.class, 8);
            snowboarding.addProductToProductSet(session.get(Product.class, "Fisher Snowboard"));

            tx.commit();
        }


         */




        /*
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Supplier supplier = new Supplier("Adidas Corporation", "Broadway 16", "New York");
            session.save(supplier);
            tx.commit();
        }

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Product product = new Product("Adidas 11pro", 20);
            session.save(product);
            tx.commit();
        }
        */

        /*
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, "Nike Revolution");
            Supplier supplier = session.get(Supplier.class, "Nike Corporation");
            Transaction tx = session.beginTransaction();
            product.setSupplier(supplier);
            session.update(product);
            tx.commit();
        }
        */




        /*
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Product Name:");
            String productName = inputScanner.nextLine();
            System.out.println("Units in Stock:");
            int productQuantity = inputScanner.nextInt();
            Product product = new Product(productName, productQuantity);
            Transaction tx = session.beginTransaction();
            session.save(product);
            tx.commit();
        }

        */

    }



}