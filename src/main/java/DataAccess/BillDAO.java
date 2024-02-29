package DataAccess;

import Connection.ConnectionFactory;
import Model.Bill;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.Connection;

public class BillDAO {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    public Bill insert(Bill bill) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO `sys`.`bill`(`id`, `cantitate`) VALUES (?,?)";
        int result = -1;
        int p = 1;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for(Field field: bill.getClass().getDeclaredFields()){
                field.setAccessible(true);
                statement.setObject(p, field.get(bill));
                p++;
            }
            result = statement.executeUpdate();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return bill;
    }
}
