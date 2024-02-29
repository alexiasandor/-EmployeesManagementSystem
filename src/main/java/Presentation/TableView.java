package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.util.Vector;

public class TableView<T> extends JFrame {
        private JPanel panel;
        private JTable table = new JTable();


        public TableView() {

            panel = new JPanel();
            this.setTitle("TABLE");

            /**
             * se creeaza scrollPane pentru tabel
             */
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setVisible(true);
            panel.add(scrollPane);

            this.add(panel);
            this.setSize(700, 400);
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setVisible(true);

        }


        public void setTable(JTable t) {
            this.table = t;
        }

    /**
     * aceasta functie imi creaza tabelul, exclusiv pentru comanda, pentru client, pentru produs
     * in functie de lista pe care o pimeste ca parametru, adauga succesiv coloanele si liniile
     * @param listaFinala
     */
    public void createTable(List<T> listaFinala){
            if(!listaFinala.isEmpty()){
                DefaultTableModel model = new DefaultTableModel();
                for(Field field :listaFinala.get(0).getClass().getDeclaredFields()){
                    model.addColumn(field.getName());
                }

                for(T t : listaFinala){
                    Vector v =new Vector<>();
                    for(Field field : t.getClass().getDeclaredFields()){
                        field.setAccessible(true);
                        Object o ;
                        try{
                            o=field.get(t);
                            v.add(o);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    model.addRow(v);
                }
                table.setModel(model);
            }
        }
     }

