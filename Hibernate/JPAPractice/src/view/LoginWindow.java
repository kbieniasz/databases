package view;

import persistanceModel.Category;
import persistanceModel.Customer;
import persistanceModel.JPAFactory;
import persistanceModel.Order;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class LoginWindow  extends JFrame implements ActionListener {

    JButton bConfirm;
    JLabel labelLogin;
    JLabel labelPassword;
    JTextField textFieldLogin;
    JTextField textFieldPassword;
    List<Order> bucket;


    public LoginWindow(List<Order> bucket)
    {
        this.bucket = bucket;
        setSize(300, 250);
        setTitle("Autentication");
        setLayout(null);

        labelLogin = new JLabel("Login");
        labelLogin.setBounds(25, 25, 100, 30);
        add(labelLogin);

        labelPassword = new JLabel("Password");
        labelPassword.setBounds(25, 55, 100, 30);
        add(labelPassword);

        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(125, 25, 150, 30);
        add(textFieldLogin);

        textFieldPassword = new JTextField();
        textFieldPassword.setBounds(125, 55, 150, 30);
        add(textFieldPassword);


        bConfirm = new JButton("ok");
        bConfirm.setBounds(100,100,100,100);
        bConfirm.addActionListener(this);
        add(bConfirm);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        if(eventSource == bConfirm)
        {
            EntityManager entityManager = JPAFactory.getEntityManagerFactory().createEntityManager();
            String companyName  = textFieldLogin.getText();
            List<Customer> customers = entityManager.createQuery("select c from Customers c" +
                    " where c.companyName =: nameX")
                    .setParameter("nameX",companyName)
                    .getResultList();
            if(customers.size() == 0)
            {

                System.out.println("No such customer");
                return;
                //TODO
            }
            Customer customer = customers.get(0);
            entityManager.close();

            ConfirmationWindow confirmationWindow = new ConfirmationWindow(bucket, customer);
            confirmationWindow.setVisible(true);
            this.dispose();

        }

    }
}

