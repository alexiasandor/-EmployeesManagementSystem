package BusinessLogic;

import BusinessLogic.validators.Validator;
import DataAccess.OrderDAO;
import Model.Client;
import Model.Comanda;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.NoSuchElementException;
public class OrderBLL {
    private OrderDAO orderDao;
    private List<Validator<Comanda>> validatorList;

    /**
     *
     */
    public OrderBLL() {
        orderDao = new OrderDAO();
        validatorList = new ArrayList<Validator<Comanda>>();
    }
    public Comanda findById(int id) throws NoSuchElementException {
        Comanda order = orderDao.findById(id);
        if (order == null) {
            throw new NoSuchElementException("The order with id = " + id + " was not found!");
        }
        return order;
    }
    /**
     *
     * @return
     */
    public List<Comanda> findAllComanda(){
        List<Comanda> comandaList= orderDao.findAll();
        if(comandaList == null){
            throw new NoSuchElementException("Lista de clienti goala!");
        }
        return comandaList;
    }

    /**
     *
     * @param comanda
     */
    public void insertComanda(Comanda comanda){
        for (Validator<Comanda> validator : validatorList) {
            validator.validate(comanda);
        }
        orderDao.insert(comanda);
    }



    /**
     *
     * @param id
     */
    public void deleteComanda(int id ){
        orderDao.delete(id);
    }
}
