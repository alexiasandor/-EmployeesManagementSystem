package View;

import java.awt.Font;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Model.Client;
import BusinessLogic.ClientBLL;
import Presentation.TableView;

public class View1 extends JFrame {

    private JLabel clientLabel;
    private JLabel numeLabel;
    private JLabel idLabel;
    private JLabel adresaLabel;
    private JLabel emailLabel;
    private JLabel varstaLabel;
    private JTextField idField;
    private JTextField numeField;
    private JTextField adresaField;
    private JTextField varstaField;
    private JTextField emailField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewAllClientButton;
    private JScrollPane scroll;
    private ClientBLL clientBLL = new ClientBLL();

    public View1() {

        this.setBounds(100, 100, 766, 492);
        this.getContentPane().setBackground(new Color(215, 201, 181));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        clientLabel = new JLabel("CLIENT OPERATIONS");
        clientLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientLabel.setBounds(220, 24, 298, 39);
        this.getContentPane().add(clientLabel);

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
        numeLabel.setBounds(31, 159, 80, 31);
        this.getContentPane().add(numeLabel);

        numeField = new JTextField();
        numeField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        numeField.setColumns(10);
        numeField.setBounds(113, 158, 120, 33);
        this.getContentPane().add(numeField);

        adresaField = new JTextField();
        adresaField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        adresaField.setColumns(10);
        adresaField.setBounds(113, 216, 120, 33);
        this.getContentPane().add(adresaField);


        adresaLabel = new JLabel("ADRESA");
        adresaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adresaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        adresaLabel.setBounds(23, 217, 80, 31);
        this.getContentPane().add(adresaLabel);

        emailLabel = new JLabel("EMAIL");
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailLabel.setBounds(31, 272, 80, 31);
        this.getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailField.setColumns(10);
        emailField.setBounds(113, 271, 120, 33);
        this.getContentPane().add(emailField);

        varstaField = new JTextField();
        varstaField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        varstaField.setColumns(10);
        varstaField.setBounds(113, 332, 120, 33);
        this.getContentPane().add(varstaField);

        varstaLabel = new JLabel("VARSTA");
        varstaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        varstaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        varstaLabel.setBounds(31, 333, 80, 31);
        this.getContentPane().add(varstaLabel);

        addButton = new JButton("ADD");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addButton.setBounds(307, 89, 120, 49);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = getIdField1();
                    String nume = getNumeField1();
                    String adresa = getAdresaField1();
                    String email = getEmailField1();
                    int varsta = getVarstaField1();

                    Client c = new Client(id, nume, adresa, email, varsta);
                    clientBLL.insertClient(c);
                    //
                } catch (Exception ex) {
                    showErorrMessage("Wrong!");
                }
            }
        });
        this.getContentPane().add(addButton);
        deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(445, 89, 120, 49);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = getIdField1();

                    clientBLL.deleteClient(id);

                } catch (Exception ex) {
                    showErorrMessage("Wrong");
                }
            }
        });
        this.getContentPane().add(deleteButton);
        viewAllClientButton = new JButton("VIEW ALL");
        viewAllClientButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        viewAllClientButton.setBounds(65, 379, 193, 49);

        viewAllClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Client> clList = clientBLL.findAllClients();
                    TableView<Client> clientTable = new TableView<>();
                    clientTable.createTable(clList);
                }
                catch (Exception ex) {
                    showErorrMessage("Wrong");
                }
            }
        });
        this.getContentPane().add(viewAllClientButton);

        updateButton = new JButton("UPDATE");
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        updateButton.setBounds(587, 89, 120, 49);


        updateButton.addActionListener(new ActionListener() {
            int id = 0;
            String name = "";
            String address = "";
            String email = "";
            int age = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                id = getIdField1();
                System.out.println("ID " + id);
                name = getNumeField1();
                address = getAdresaField1();
                email = getEmailField1();
                age = getVarstaField1();
                try {
                    List<String> fields1 = new ArrayList<>();
                    List<Client> clientList = clientBLL.findAllClients();

                    Client client = new Client();

                    for (Client c : clientList) {
                        if (c.getId() == id) {
                            client.setId(id);
                            fields1.add("id");
                            client.setNume(name);
                            fields1.add("nume");
                            client.setAdresa(address);
                            fields1.add("adresa");
                            client.setEmail(email);
                            fields1.add("email");
                            client.setVarsta(age);
                            fields1.add("varsta");

                            try {
                                clientBLL.updateClient(client, id, fields1);
                                System.out.println("Clientul a fost actualizat cu succes!");
                            } catch (Exception exception) {
                                exception.printStackTrace();
                                System.out.println("Nu s-a putut adauga!");
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        this.getContentPane().add(updateButton);
        this.setVisible(false);
    }

private void showErorrMessage(String message){
        JOptionPane.showMessageDialog(this,message,"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);

}

    public int getIdField1() {
        return Integer.parseInt(idField.getText());
    }

    public void setIdField1(int idField) {
        this.idField.setText(String.valueOf(idField));
    }

    public String getNumeField1() {
        return numeField.getText();
    }

    public void setNumeField1(String numeField) {
        this.numeField.setText(String.valueOf(numeField));
    }

    public String getAdresaField1() {
        return adresaField.getText();
    }

    public void setAdresaField1(String adresaField) {
        this.adresaField.setText(String.valueOf(adresaField));
    }

    public int getVarstaField1() {
        return Integer.parseInt(varstaField.getText());
    }

    public void setVarstaField1(int varstaField) {
        this.varstaField.setText(String.valueOf(varstaField));
    }

    public String getEmailField1() {
        return emailField.getText();
    }

    public void setEmailField1(String emailField) {
        this.emailField.setText(emailField);
    }

}


