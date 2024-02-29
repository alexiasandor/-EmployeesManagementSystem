package Connection;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
public class ConnectionFactory {
    private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/sys";
    private static final String USER="root";
    private static final String PASS ="root";
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory(){
        try {
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * realizam conexiunea cu baza de date
     * @return
     */
    private Connection createConnection(){
        Connection connection=null;
        try{
            connection=DriverManager.getConnection(DBURL,USER,PASS);
            System.out.println("Database succesfully connected!");
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "The database can not connect");
            e.printStackTrace();
        }
        return connection;
    }
    //return
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    /**
     * metodata care inchide conexiunea cu baza de date
     * @param connection
     */
    public static void close(Connection connection){
        if(connection!=null){
            try{
                connection.close();
            }catch(SQLException e){
                LOGGER.log(Level.WARNING, "Error at connection!");
            }
        }
    }

    public static void close(Statement statement){
        if(statement !=null){
            try{
                statement.close();
            }catch(SQLException e){
                LOGGER.log(Level.WARNING, "Error at connection");
            }
        }
    }

    public static  void close(ResultSet resultSet){
        if(resultSet !=null){
            try{
                resultSet.close();
            }catch(SQLException e){
                LOGGER.log(Level.WARNING, "Error at connection");
            }
        }
    }
}
