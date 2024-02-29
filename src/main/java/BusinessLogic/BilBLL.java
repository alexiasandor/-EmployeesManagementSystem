package BusinessLogic;

import DataAccess.BillDAO;
import Model.Bill;

import java.sql.SQLException;

public class BilBLL {
    private BillDAO billDAO;

    /**
     *
     */
    public BilBLL(){
        billDAO = new BillDAO();
    }

    /**
     *
     * @param b
     */
    public void insert (Bill b){
        try{
            billDAO.insert(b);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
