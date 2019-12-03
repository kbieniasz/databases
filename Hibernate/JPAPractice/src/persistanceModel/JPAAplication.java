package persistanceModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPAAplication {
    public static void main(String[] args) {

        EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction1 = entityManager.getTransaction();
        entityTransaction1.begin();
        Supplier supplier1 = new Supplier("Puma", "Sportowa", "12", "Rzeszów", "36-084", "123456789");
        Supplier supplier2 = new Supplier("Asisc", "Sportowa", "13", "Rzeszów", "36-084","123456788");
        Customer customer1 = new Customer("Runner Shop", "Sportowa", "14", "Rzeszów", "36-084",0.5);
        Customer customer2 = new Customer("Sport Shop", "Sportowa", "14", "Rzeszów", "36-084",0.4);
        entityManager.persist(supplier1);
        entityManager.persist(supplier2);
        entityManager.persist(customer1);
        entityManager.persist(customer2);
        entityTransaction1.commit();
        entityManager.close();
        /*
        EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction1 = entityManager.getTransaction();
        entityTransaction1.begin();
        Supplier supplier1 = new Supplier("Puma", "Sportowa", "12", "Rzeszów", "36-084");
        Supplier supplier2 = new Supplier("Asisc", "Sportowa", "13", "Rzeszów", "36-084");
        entityManager.persist(supplier1);
        entityManager.persist(supplier2);
        entityTransaction1.commit();
        entityManager.close();


         */

        /*
        Product product1 = new Product("Elana ice skates 400",5);
        Product product2 = new Product("Elana ice skates 500",5);
        Invoice invoice = new Invoice();
        invoice.addProduct(product1);
        invoice.addProduct(product2);
        entityManager.persist(invoice);
        entityTransaction1.commit();
        entityManager.close();


         */



        /*
        EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction1 = entityManager.getTransaction();
        entityTransaction1.begin();
        Product product1 = new Product("Elana ice skates 100",5);
        Product product2 = new Product("Elana ice skates 200",5);
        Product product3 = new Product("Elana ice skates 300",5);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        Supplier supplier = new Supplier("Elana","Bergisel 123","Innsbruck");
        entityManager.persist(supplier);
        //Category skiing = new Category("Skiing");
        //Category snowboarding = new Category("Snowboarding");
        Category iceskating = new Category("Ice Skating");
        entityManager.persist(iceskating);

        supplier.addProductToProductSet(product1);
        supplier.addProductToProductSet(product2);
        supplier.addProductToProductSet(product3);

        iceskating.addProductToProductSet(product1);
        iceskating.addProductToProductSet(product2);
        iceskating.addProductToProductSet(product3);


        entityManager.persist(skiing);
        entityManager.persist(snowboarding);

        entityTransaction1.commit();

        entityManager.close();

        */

        /*
        EntityTransaction entityTransaction2 = entityManager.getTransaction();
        Supplier supplierElana = entityManager.find(Supplier.class, "Elana");
        supplierElana.addProductToProductSet(entityManager.find(Product.class, "Elana 360"));
        supplierElana.addProductToProductSet(entityManager.find(Product.class, "Elana 480"));
        supplierElana.addProductToProductSet(entityManager.find(Product.class, "Elana ice skates"));

        Category categoryIceSkating = entityTransaction2.N
        User user = session.byNaturalId(User.class)
                .using("email","huchenhai@qq.com")
                .load()

        Category skiing = session.get(Category.class, 7);
        skiing.addProductToProductSet(session.get(Product.class, "Fisher 360"));
        skiing.addProductToProductSet(session.get(Product.class, "Fisher 480"));
        Category snowboarding = session.get(Category.class, 8);
        snowboarding.addProductToProductSet(session.get(Product.class, "Fisher Snowboard"));

        tx.commit();
        */








        /*


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


        try (Session session = HibernateUtils.getSessionFactory().openSession())
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
        EntityManager em = JPAFactory.getEntityManagerFactory().createEntityManager();
        List<Category> categories = new ArrayList<>();

        Category cat1 = new Category("Picie");
        cat1.addProduct(new Product("Kola", 21));
        cat1.addProduct(new Product("Pepsi", 37));
        cat1.addProduct(new Product("Fanta", 666));

        Category cat2 = new Category("Jedzenie");
        cat2.addProduct(new Product("Czipsy", 10));
        cat2.addProduct(new Product("Lizaki", 20));
        cat2.addProduct(new Product("Ryby", 30));

        Category cat3 = new Category("Elektronika") ;
        cat3.addProduct(new Product("MP3", 3));
        cat3.addProduct(new Product("MP4", 4));
        cat3.addProduct(new Product("MP5", 5));

        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);

        EntityTransaction etx = em.getTransaction();
        etx.begin();

        for(Category cat : categories) {
            for(Product product : cat.getProducts()) {
                product.setCategory(cat);
                em.persist(product);
            }
            em.persist(cat);
        }

        etx.commit();
        em.close();


         */
    }
}