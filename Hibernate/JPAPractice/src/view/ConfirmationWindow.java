package view;

import persistanceModel.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ConfirmationWindow  extends JFrame implements ActionListener {

    JButton bConfirm;
    JLabel labeldeliveryInfo;
    JLabel label1, label2;
    List<Order> bucket;
    Customer customer;
    JLabel labelTotalCost;




    public ConfirmationWindow(List<Order> bucket, Customer customer)
    {
        this.customer = customer;
        this.bucket = bucket;
        setSize(300, 250);
        setTitle("Confirmation");
        setLayout(null);

        label1 = new JLabel("Please check if everything is right");
        label1.setBounds(25, 25, 200, 20);
        add(label1);

        label2 = new JLabel(customer.getCompanyName());
        label2.setBounds(25, 55, 200, 15);
        add(label2);

        labeldeliveryInfo = new JLabel(customer.getAddress().deliverInfo());
        labeldeliveryInfo.setBounds(25, 65, 100, 50);
        add(labeldeliveryInfo);

        float totalValue = 0;
        for(Order order : bucket)
        {
            totalValue+= order.getAmount()*order.getProduct().getPrice();
        }
        labelTotalCost = new JLabel("Total cost: " + totalValue);
        labelTotalCost.setBounds(25, 110, 100, 60);
        add(labelTotalCost);

        bConfirm = new JButton("ok");
        bConfirm.setBounds(160,100,50,50);
        bConfirm.addActionListener(this);
        add(bConfirm);


    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Object eventSource = e.getSource();
        if(eventSource == bConfirm)
        {
            EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Invoice invoice = new Invoice();

            List<Customer> customers = entityManager.createQuery("select c from Customers c" +
                    " where companyName =: nameX")
                    .setParameter("nameX",customer.getCompanyName())
                    .getResultList();
            invoice.setCustomer(customers.get(0));
            for(Order order : bucket)
            {
                List<Product> results = entityManager.createQuery("select p from Products p" +
                        " where p.productName =: nameX")
                        .setParameter("nameX",order.getProduct().getProductName())
                        .getResultList();


                invoice.addProduct(results.get(0));
                entityManager.persist(results.get(0));
            }

            entityManager.persist(invoice);
            entityManager.persist(customers.get(0));
            entityTransaction.commit();
            entityManager.close();
            this.dispose();


        }

    }
}