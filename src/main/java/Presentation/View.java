package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.View1;
import View.View2;
import View.View3;
public class View extends JFrame {
    private JLabel orderManagementButton;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;

    public View(){
        this.getContentPane().setBackground(new Color(255,192,203));
        this.setBounds(100,100,766,492);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        orderManagementButton= new JLabel("ORDERS MANAGEMENT");
        orderManagementButton.setFont(new Font("Tahoma",Font.PLAIN,25));
        orderManagementButton.setHorizontalAlignment(SwingConstants.CENTER);
        orderManagementButton.setBounds(183,24,362,73);
        this.getContentPane().add(orderManagementButton);

        clientButton= new JButton("CLIENT OPERATIONS");
        clientButton.setFont(new Font("Tahoma",Font.PLAIN,18));
        clientButton.setBounds(183,140,335,49);
        this.getContentPane().add(clientButton);
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View1 view1= new View1 ();
                view1.setVisible(true);
            }
        });
        productButton= new JButton("PRODUCT OPERATIONS");
        productButton.setFont(new Font("Tahoma",Font.PLAIN,18));
        productButton.setBounds(183,246,340,49);
        this.getContentPane().add( productButton);
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View2 view2 = new View2();
                        view2.setVisible(true);
            }
        });

        orderButton= new JButton("ORDER OPERATIONS");
        orderButton.setFont(new Font("Tahoma",Font.PLAIN,18));
        orderButton.setBounds(183,345,346,49);
        this.getContentPane().add( orderButton);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View3 view3 = new View3();
                view3.setVisible(true);
            }
        });

        this.setVisible(true);

    }

    public void addOrderListener(ActionListener action){
        orderButton.addActionListener(action);
    }
    public void addProductListener(ActionListener action){
        productButton.addActionListener(action);
    }
    public void addClientListener(ActionListener action){
        clientButton.addActionListener(action);
    }
}
