package DataAccess;

import Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")

    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * " );
        sb.append(" FROM `sys`.` " );
        sb.append(type.getSimpleName());
        sb.append("` WHERE " + field + " =?");
        return sb.toString();

    }

    /**
     *
     * @return
     */
    private String createSelectQuery1() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * " );
        sb.append(" FROM " );
        sb.append(type.getSimpleName());
        return sb.toString();

    }

    /**
     *
     * @return
     */
    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery1();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * metoda de gasire a clientului , a produselor sau a comenzilor cu un anumit id
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * metoda de creare a unei liste de obiecte
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param t
     * @return
     */
    private String createInsertQuery(T t) {
    /* INSER INTO table_name(column1, column2, column 3..)
       VALUES (value1, value2, value3,.....);
    */

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO `sys`.`");
            sb.append(type.getSimpleName() + "` (");
            for (Field f : t.getClass().getDeclaredFields()) {
                sb.append(f.getName());
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES(");
            sb.append("?,".repeat((int) Math.max(0., t.getClass().getDeclaredFields().length - 1)));
            sb.append("?);");
            return sb.toString();


    }
    /**
     * metoda care preia field-urile necesare pentru realizarea operatiei de inserare
     * @param t
     */
    public void insert(T t) {
        //TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int parameter = 1;
            for (Field fields : type.getDeclaredFields()) {
                fields.setAccessible(true);
                statement.setObject(parameter, fields.get(t));
                parameter++;
            }
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert" + e.getMessage());

        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * metoda care implementeaza interogarea delete
     * @param id
     * @return
     */
    private String deleteQuery(int id) {
        //DELETE FROM table_name WHERE condition;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append("FROM `sys`.`");
        sb.append(type.getSimpleName() + "`");
        sb.append("WHERE id=?;");
        return sb.toString();

    }
    /**
     * metoda care preia field-urile necesare pentru realizarea operatie de stergere si apeleaza metoda care construieste interogare
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * metoda care implementeaza interogarea update
     * @param field
     * @return
     */
    private String updateQuery(String field, List<String> fields1) {
       /* UPDATE table_name
        SET column1 = value1, column2 = value2, ...
        WHERE condition;*/
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + type.getSimpleName().toLowerCase());
        sb.append("\n");
        sb.append("SET ");
        for (int i = 0; i < fields1.size(); i++) {
            if (i < fields1.size() - 1) {
                sb.append(fields1.get(i) + " =?, ");
            } else
                sb.append(fields1.get(i) + " =?");
        }
        sb.append("\n");
        sb.append("WHERE " + field + " =?");
        System.out.println(sb);
        return sb.toString();
    }


    /**
     * metoda care preia field-urile necesare pentru realizarea operatiei de update
     * @param t
     * @param id
     * @param field1
     */

    public void update(T t, int id, List<String> field1) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery("id", field1);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int p = 1;
            for (Field fields : type.getDeclaredFields()) {
                for (int i = 0; i < field1.size(); i++)
                    if (fields.getName().equals(field1.get(i))) {
                        fields.setAccessible(true);
                        statement.setObject(p,fields.get(t));
                        System.out.println(fields.get(t));
                        System.out.println(field1);
                        p++;
                    }
            }
            statement.setInt(p, id);
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * metoda care afiseaza tot tabelul
     * @return
     */

    public String viewAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("`sys`.`" + type.getSimpleName() + "`;");
        return sb.toString();
    }
}
