package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
//import com.my.stuff.second.Second;

import persistanceModel.*;

public class StartWindow extends JFrame implements ActionListener {
    private List<Order> productsInBasket = new ArrayList<>();


    private JButton bShowAllProducts;
    private JButton bFilterCompany;
    private JButton bFilterCategory;
    private JButton bAddToBasket;
    private JButton bFinalize;

    private JSpinner spinnerAmount;

    private List<Product> productList; //= new ArrayList<>();
    private List<Category> categoryList;// = new ArrayList<>();
    private List<Supplier> supplierList;// = new ArrayList<>();
    private JComboBox<Category> categoryJComboBox;
    private JComboBox<Supplier> supplierJComboBox;
    private JList<Product> productJList;
    private JList<Order> orderJList;
    JScrollPane scrollpane;
    JScrollPane scrollPaneBucket;

    private JLabel jLabelProductDescription;
    private JLabel jLabelProductDescriptionInfo;
    private JLabel jLabelCurrentBucketState;



    public StartWindow()
    {
        //addExampleData2();
        downloadData();
        initializeComponents();


    }

    private void downloadData()
    {
        EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction1 = entityManager.getTransaction();
        entityTransaction1.begin();
        categoryList = entityManager.createQuery("select c from Categories c").getResultList();
        supplierList = entityManager.createQuery("select s from Suppliers  s").getResultList();
        productList = entityManager.createQuery("select p from Products  p").getResultList();
        entityTransaction1.commit();
        entityManager.close();
    }

    private void initializeComponents(){
        setSize (800,400);
        setTitle("Sport Shop");
        setLayout(null);

        bShowAllProducts = new JButton("Show all products");
        bShowAllProducts.setBounds(25, 75, 250, 20);
        add(bShowAllProducts);
        bShowAllProducts.addActionListener(this);

        bFilterCompany = new JButton("Search products by company");
        bFilterCompany.setBounds(275, 75, 250, 20);
        add(bFilterCompany);
        bFilterCompany.addActionListener(this);

        bFilterCategory = new JButton("Search products by category");
        bFilterCategory.setBounds(525, 75, 250, 20);
        add(bFilterCategory);
        bFilterCategory.addActionListener(this);



        categoryJComboBox = new JComboBox<Category>();
        for(Category category : categoryList) {
            categoryJComboBox.addItem(category);
        }
        categoryJComboBox.setBounds(525, 100, 250, 40);
        this.add(categoryJComboBox);

        supplierJComboBox = new JComboBox<Supplier>();
        for(Supplier supplier : supplierList) {
            supplierJComboBox.addItem(supplier);
        }

        supplierJComboBox.setBounds(275, 100, 250, 40);
        this.add(supplierJComboBox);

        DefaultListModel<Product> listModel = new DefaultListModel<>();
        for(Product product : productList)
        {
            listModel.addElement(product);
        }

        productJList = new JList<Product>(listModel);
        addProductListSelectionListener();
        scrollpane = new JScrollPane(productJList);
        scrollpane.setBounds(25, 100, 250, 190);
        this.add(scrollpane);

        jLabelProductDescription = new JLabel();

        jLabelProductDescription.setBounds(275, 160, 250, 80);
        //jLabelProductDescription.setForeground(Color.lightGray);
        this.add(jLabelProductDescription);

        bAddToBasket = new JButton("Add to basket");
        bAddToBasket.setBounds(275, 250, 250, 20);
        add(bAddToBasket);
        bAddToBasket.addActionListener(this);

        spinnerAmount = new JSpinner();
        spinnerAmount.setBounds(275, 270, 250,20);
        add(spinnerAmount);


        orderJList = new JList<Order>();
        scrollPaneBucket = new JScrollPane(orderJList);
        scrollPaneBucket.setBounds(525, 160, 250, 80);
        this.add(scrollPaneBucket);


        bFinalize = new JButton("Finalize");
        bFinalize.setBounds(525, 250, 250, 40);
        add(bFinalize);
        bFinalize.addActionListener(this);

        jLabelProductDescriptionInfo = new JLabel("Product information");
        jLabelProductDescriptionInfo.setBounds(275, 140, 250, 20);
        add(jLabelProductDescriptionInfo);

        jLabelCurrentBucketState = new JLabel("Your bucket");
        jLabelCurrentBucketState.setBounds(525, 140, 250, 20);
        add(jLabelCurrentBucketState);


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {


        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

        StartWindow startWindow = new StartWindow();
        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startWindow.setVisible(true);



    }


    public void addExampleData2()
    {
        EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction1 = entityManager.getTransaction();
        entityTransaction1.begin();
        Supplier supplier1 = new Supplier("Nike", "Sportowa", "17", "Rzeszów", "36-084", "123456777");
        Customer customer1 = new Customer("Jan Kowalski", "Sportowa", "20", "Rzeszów", "36-084",0.5);
        Customer customer2 = new Customer("Piotr Nowak", "Sportowa", "18", "Rzeszów", "36-084",0.4);

        Category category1 = new Category("Football");
        Product product1 = new Product("Nike 360 control",5, 199);
        Product product2 = new Product("Nike Mercurial",5, 299);
        Product product3 = new Product("Nike Tiempo",5, 399);

        supplier1.addProductToProductSet(product1);
        supplier1.addProductToProductSet(product3);
        supplier1.addProductToProductSet(product2);

        category1.addProductToProductSet(product1);
        category1.addProductToProductSet(product2);
        category1.addProductToProductSet(product3);

        entityManager.persist(supplier1);

        entityManager.persist(category1);


        entityManager.persist(customer1);
        entityManager.persist(customer2);

        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);

        entityTransaction1.commit();
        entityManager.close();
    }



    public void addExampleData()
    {
        EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction1 = entityManager.getTransaction();
        entityTransaction1.begin();
        Supplier supplier1 = new Supplier("Puma", "Sportowa", "12", "Rzeszów", "36-084", "123456789");
        Supplier supplier2 = new Supplier("Asisc", "Sportowa", "13", "Rzeszów", "36-084","123456788");
        Customer customer1 = new Customer("Runner Shop", "Sportowa", "14", "Rzeszów", "36-084",0.5);
        Customer customer2 = new Customer("Sport Shop", "Sportowa", "14", "Rzeszów", "36-084",0.4);

        Category category1 = new Category("Skiing");
        Category category2 = new Category("Running");
        Category category3 = new Category("Football");

        Product product1 = new Product("Puma 360",5, 199);
        Product product2 = new Product("Asisc pro",5, 299);
        Product product3 = new Product("Puma extreme",5, 399);

        supplier1.addProductToProductSet(product1);
        supplier1.addProductToProductSet(product3);
        supplier2.addProductToProductSet(product2);

        category2.addProductToProductSet(product1);
        category2.addProductToProductSet(product2);
        category2.addProductToProductSet(product3);

        entityManager.persist(supplier1);

        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(category3);

        entityManager.persist(supplier2);
        entityManager.persist(customer1);
        entityManager.persist(customer2);

        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);

        entityTransaction1.commit();
        entityManager.close();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        if(eventSource == bFilterCategory)
        {
            if(categoryJComboBox.getSelectedItem() != null) // bez sensu
            {
                EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
                String categoryName  =categoryJComboBox.getSelectedItem().toString();
                List<Category> categories = entityManager.createQuery("select c from Categories c" +
                        " where c.name =: nameX")
                        .setParameter("nameX",categoryName)
                        .getResultList();


                List<Product> results = entityManager.createQuery("select p from Products p" +
                        " where p.category =: categoryX")
                        .setParameter("categoryX",categories.get(0))
                        .getResultList();
                DefaultListModel<Product> listModel = new DefaultListModel<>();
                for(Product product : results)
                {
                    listModel.addElement(product);
                }

                productJList = new JList<Product>(listModel);
                addProductListSelectionListener();
                scrollpane = new JScrollPane(productJList);
                scrollpane.setBounds(25, 100, 250, 190);
                this.add(scrollpane);
                scrollpane.updateUI();
                entityManager.close();
                return;
            }
        }

        if(eventSource == bFilterCompany)
        {
            if(supplierJComboBox.getSelectedItem() != null)
            {
                EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
                String companyName  =supplierJComboBox.getSelectedItem().toString();
                List<Supplier> suppliers = entityManager.createQuery("select s from Suppliers s" +
                        " where s.companyName =: nameX")
                        .setParameter("nameX",companyName)
                        .getResultList();


                List<Product> results = entityManager.createQuery("select p from Products p" +
                        " where p.supplier =: supplierX")
                        .setParameter("supplierX",suppliers.get(0))
                        .getResultList();
                DefaultListModel<Product> listModel = new DefaultListModel<>();
                for(Product product : results)
                {
                    listModel.addElement(product);
                }

                productJList = new JList<Product>(listModel);
                addProductListSelectionListener();
                scrollpane = new JScrollPane(productJList);
                scrollpane.setBounds(25, 100, 250, 190);
                this.add(scrollpane);
                scrollpane.updateUI();
                entityManager.close();
                return;
            }
        }

        if(eventSource == bShowAllProducts)
        {

                EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
                List<Product> results = entityManager.createQuery("select p from Products p")
                        .getResultList();
                DefaultListModel<Product> listModel = new DefaultListModel<>();
                for(Product product : results)
                {
                    listModel.addElement(product);
                }

                productJList = new JList<Product>(listModel);
                scrollpane = new JScrollPane(productJList);
                scrollpane.setBounds(25, 100, 250, 100);
                this.add(scrollpane);
                scrollpane.updateUI();
                entityManager.close();

                return;

        }

        if(eventSource == bAddToBasket)
        {

            EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
            String productName = productJList.getSelectedValue().toString();
            List<Product> results = entityManager.createQuery("select p from Products p" +
                    " where p.productName =: productX")
                    .setParameter("productX",productName)
                    .getResultList();
            Product product = results.get(0);
            int amount = (Integer) spinnerAmount.getValue();
            productsInBasket.add(new Order(product, amount));

            DefaultListModel<Order> listModel = new DefaultListModel<>();
            for(Order order : productsInBasket)
            {
                listModel.addElement(order);
            }
             orderJList= new JList<Order>(listModel);
            scrollPaneBucket = new JScrollPane(orderJList);
            scrollPaneBucket.setBounds(525, 160, 250, 80);
            this.add(scrollPaneBucket);
            scrollPaneBucket.updateUI();
            entityManager.close();
            return;

        }
        if(eventSource == bFinalize)

        {
            LoginWindow loginWindow = new LoginWindow(productsInBasket);
            loginWindow.setVisible(true);

        }



    }

    public void addProductListSelectionListener()
    {
        productJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
                String productName = productJList.getSelectedValue().toString();
                List<Product> results = entityManager.createQuery("select p from Products p" +
                        " where p.productName =: productX")
                        .setParameter("productX",productName)
                        .getResultList();
                Product product = results.get(0);
                jLabelProductDescription.setText(product.displayDescription());
                entityManager.close();
            }
        });
    }

}
