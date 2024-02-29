package View;

import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Product;
import Presentation.TableView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class View2 extends JFrame {
    private JLabel productLabel;
    private JLabel numeLabel;
    private JLabel pretLabel;
    private JLabel cantitateLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField numeField;
    private JTextField cantitateField;
    private JTextField pretField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewAllClientButton;

    private ProductBLL productBLL= new ProductBLL();

    /**
     * constructor care creaza interfata grafica
     */

    public View2() {
        this.getContentPane().setBackground(new Color(215, 201, 181));
        this.setBounds(100, 100, 766, 492);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        productLabel = new JLabel("PRODUCT OPERATIONS");
        productLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        productLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productLabel.setBounds(220, 24, 298, 39);
        this.getContentPane().add(productLabel);

        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idField.setBounds(113, 97, 120, 33);
        this.getContentPane().add(idField);
        idField.setColumns(10);

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idLabel.setBounds(72, 98, 31, 31);
        this.getContentPane().add(idLabel);

        numeLabel = new JLabel("NUME");
        numeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        numeLabel.setBounds(31, 177, 80, 31);
        this.getContentPane().add(numeLabel);

        numeField = new JTextField();
        numeField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        numeField.setColumns(10);
        numeField.setBounds(113, 176, 120, 33);
        this.getContentPane().add(numeField);

        cantitateField = new JTextField();
        cantitateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cantitateField.setColumns(10);
        cantitateField.setBounds(113, 247, 120, 33);
        this.getContentPane().add(cantitateField);

        cantitateLabel = new JLabel("CANTITATE");
        cantitateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cantitateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cantitateLabel.setBounds(10, 248, 80, 31);
        this.getContentPane().add(cantitateLabel);

        pretLabel = new JLabel("PRET");
        pretLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pretLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pretLabel.setBounds(31, 326, 80, 31);
        this.getContentPane().add(pretLabel);

        pretField = new JTextField();
        pretField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pretField.setColumns(10);
        pretField.setBounds(113, 325, 120, 33);
        this.getContentPane().add(pretField);


        addButton = new JButton("ADD");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addButton.setBounds(307, 89, 120, 49);
        this.getContentPane().add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = getIdField();
                    String nume= getNumeField();
                    int pret= getPretField();
                    int cantitate=getCantitateField();

                    Product p = new Product (id,nume,pret,cantitate);
                    productBLL.insertProduct(p);
                    //
                } catch (Exception ex) {
                    showErrorMessage("Wrong!");
                }
            }
        });

        deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(445, 89, 120, 49);
        this.getContentPane().add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = getIdField();

                    productBLL.deleteProduct(id);

                } catch (Exception ex) {
                    showErrorMessage("Wrong");
                }
            }
        });

        viewAllClientButton = new JButton("VIEW ALL PRODUCTS");
        viewAllClientButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        viewAllClientButton.setBounds(65, 379, 193, 49);
        this.getContentPane().add(viewAllClientButton);
        viewAllClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<Product> prList = productBLL.findAllProducts();
                    TableView<Product> productTable = new TableView<>();
                    productTable.createTable(prList);
                } catch (Exception ex) {
                    showErrorMessage("Wrong");
                }
            }
        });


        updateButton = new JButton("UPDATE");
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        updateButton.setBounds(587, 89, 120, 49);
        this.getContentPane().add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            int id = 0;
            String nume = "";
            int cantitate = 0;
            int pret = 0;
            String id1 = "";
            String age = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                id = getIdField();
                nume = getNumeField();
                cantitate = getCantitateField();
                pret = getPretField();
                try {
                    List<String> fields = new ArrayList<>();
                    List<Product> productList = productBLL.findAllProducts();

                    Product product=new Product();

                    for (Product p : productList) {
                        if (p.getId() == id) {
                            product.setId(id);
                            fields.add("id");
                            product.setNume(nume);
                            fields.add("nume");
                            product.setPret(pret);
                            fields.add("pret");
                            product.setCantitate(cantitate);
                            System.out.println("Cantitate "+ product.getCantitate());
                            fields.add("cantitate");
                            product.setPret(pret);
                            fields.add("pret");

                            try {
                                productBLL.updateProduct(product, id, fields);
                                System.out.println("Produsul s-a actualizat cu succes!");
                            } catch (Exception exception) {
                                exception.printStackTrace();
                                System.out.println("Nu s-a putut actualiza!");
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

       this.setVisible(false);
    }

    /**
     * @return
     */
    public int getIdField() {
        return Integer.parseInt(idField.getText());
    }

    /**
     * @param idField
     */
    public void setIdField(int idField) {
        this.idField.setText(String.valueOf(idField));
    }

    /**
     * @return
     */
    public int getProductLabel() {
        return Integer.parseInt(productLabel.getText());
    }

    /**
     * @param productLabel
     */
    public void setProductLabel(int productLabel) {
        this.productLabel.setText(String.valueOf(productLabel));
    }

    /**
     * @return
     */
    public String getNumeField() {
        return numeField.getText();
    }

    /**
     * @param numeField
     */
    public void setNumeField(String numeField) {
        this.numeField.setText(numeField);
    }

    /**
     * @return
     */
    public int getCantitateField() {
        return Integer.parseInt(cantitateField.getText());
    }

    /**
     * @param cantitateField
     */
    public void setCantitateField(int cantitateField) {
        this.cantitateField.setText(String.valueOf(cantitateField));
    }

    /**
     * @return
     */
    public int getPretField() {
        return Integer.parseInt(pretField.getText());
    }

    /**
     * @param pretField
     */
    public void setPretField(int pretField) {
        this.pretField.setText(String.valueOf(pretField));
    }

    /**
     * @param action
     */
    public void addAddListener(ActionListener action) {
        addButton.addActionListener(action);
    }

    /**
     *
     * @param action
     */
    public void addUpdateListener(ActionListener action) {
        updateButton.addActionListener(action);
    }

    /**
     * @param action
     */
    public void addViewAllListener(ActionListener action) {
        viewAllClientButton.addActionListener(action);
    }

    /**
     * @param action
     */
    public void addDeleteListener(ActionListener action) {
        deleteButton.addActionListener(action);
    }
    public void showErrorMessage(String s) {
        JOptionPane.showMessageDialog(this,"ERROR MESSAGE");
    }

}
