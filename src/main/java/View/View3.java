package View;

import BusinessLogic.BilBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.BillDAO;
import Model.Bill;
import Model.Client;
import Model.Comanda;
import Model.Product;
import Presentation.TableView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class View3 extends JFrame {

    private JLabel orderLabel;
    private JLabel idProdusLabel;
    private JLabel idClientLabel;
    private JLabel cantitateLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField idProdusField;
    private JTextField idClientField;
    private JTextField cantitateField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewAllClientButton;
    private JButton facturaButton;
    private JTable table;
    private JPanel panel;
    private JScrollPane scroll;
    private OrderBLL comandaBLL = new OrderBLL();

    /**
     * constructor care realizeaza interfata grafica
     */
    public View3(){
        this.getContentPane().setBackground(new Color(215, 201, 181));
        this.setBounds(100, 100, 766, 492);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        orderLabel = new JLabel("ORDER OPERATIONS");
        orderLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderLabel.setBounds(220, 24, 298, 39);
        this.getContentPane().add(orderLabel);

        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idField.setBounds(138, 97, 120, 33);
        this.getContentPane().add(idField);
        idField.setColumns(10);

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idLabel.setBounds(61, 98, 31, 31);
        this.getContentPane().add(idLabel);

        idClientLabel = new JLabel("ID CLIENT");
        idClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idClientLabel.setBounds(31, 177, 80, 31);
        this.getContentPane().add(idClientLabel);

        idClientField = new JTextField();
        idClientField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idClientField.setColumns(10);
        idClientField.setBounds(138, 176, 120, 33);
        this.getContentPane().add(idClientField);

        idProdusField = new JTextField();
        idProdusField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idProdusField.setColumns(10);
        idProdusField.setBounds(138, 247, 120, 33);
        this.getContentPane().add(idProdusField);

        idProdusLabel = new JLabel("ID PRODUS");
        idProdusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idProdusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idProdusLabel.setBounds(31, 248, 80, 31);
        this.getContentPane().add(idProdusLabel);

        cantitateLabel = new JLabel("CANTITATE");
        cantitateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cantitateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cantitateLabel.setBounds(31, 326, 80, 31);
        this.getContentPane().add(cantitateLabel);

        cantitateField = new JTextField();
        cantitateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cantitateField.setColumns(10);
        cantitateField.setBounds(138, 325, 120, 33);
        this.getContentPane().add(cantitateField);

        addButton = new JButton("ADD");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addButton.setBounds(289, 89, 120, 49);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = getIdField();
                    int idClient= getIdClientField();
                    int idProdus= getIdProdusField();
                    int cantitate=getCantitateField();

                    Comanda cm= new Comanda (id,idClient,idProdus,cantitate);
                    comandaBLL.insertComanda(cm);
                    Bill b= new Bill(id,cantitate);
                    BilBLL bB = new BilBLL();
                    bB.insert(b);

                } catch (Exception ex) {
                    showErorrMessage("Wrong!");
                }

            }
        });
        this.getContentPane().add(addButton);

        deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(419, 89, 120, 49);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = getIdField();

                    comandaBLL.deleteComanda(id);

                } catch (Exception ex) {
                    showErorrMessage("Wrong");
                }
            }
        });
        this.getContentPane().add(deleteButton);

        viewAllClientButton = new JButton("VIEW ALL ORDERS");
        viewAllClientButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        viewAllClientButton.setBounds(65, 379, 193, 49);
        viewAllClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        List<Comanda> comList = comandaBLL.findAllComanda();
                        TableView<Comanda> comandaTable = new TableView<>();
                        comandaTable.createTable(comList);
                    }
                    catch (Exception ex) {
                        showErorrMessage("Wrong");
                    }
                }

        });
        this.getContentPane().add(viewAllClientButton);

        this.setVisible(false);
    }

    /**
     *
     * @return
     */
    public int getIdField() {
        return Integer.parseInt(idField.getText());
    }

    /**
     *
     * @param idField
     */
    public void setIdField(int idField) {
        this.idField.setText(String.valueOf(idField));
    }

    /**
     *
     * @return
     */

    public int getIdProdusField() {
        return Integer.parseInt(idProdusField.getText());
    }

    /**
     *
     * @param idProdusField
     */

    public void setIdProdusField(int idProdusField) {
        this.idProdusField.setText(String.valueOf(idProdusField));
    }

    /**
     *
     * @return
     */

    public int getIdClientField() {
        return Integer.parseInt(idClientField.getText());
    }

    /**
     *
     * @param idClientField
     */

    public void setIdClientField(int idClientField) {
        this.idClientField.setText(String.valueOf(idClientField));
    }

    /**
     *
     * @return
     */

    public int getCantitateField() {
        return Integer.parseInt(cantitateField.getText());
    }

    /**
     *
     * @param cantitateField
     */
    public void setCantitateField(int cantitateField) {
        this.cantitateField.setText(String.valueOf(cantitateField));
    }



    private void showErorrMessage(String message){
        JOptionPane.showMessageDialog(this,message,"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);

    }


    public void refresh() {
        idField.setText(null);
        idClientField.setText(null);
        idProdusField.setText(null);
        cantitateField.setText(null);
    }
}