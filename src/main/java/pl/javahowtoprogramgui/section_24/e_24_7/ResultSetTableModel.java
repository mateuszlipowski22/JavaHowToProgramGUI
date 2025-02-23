package pl.javahowtoprogramgui.section_24.e_24_7;

import javax.swing.table.AbstractTableModel;
import java.sql.*;


public class ResultSetTableModel extends AbstractTableModel {

    private final Connection connection;
    private final Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;

    private int numberOfRows;

    private boolean connectedToDatabase;


    public ResultSetTableModel(String url, String username, String password, String query) throws SQLException {

        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        connectedToDatabase = true;

        setQuery(query);
    }

    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalArgumentException("Brak połączenia z bazą danych");
        }
        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Object.class;
    }

    public int getColumnCount() throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalArgumentException("Brak połączenia z bazą danych");
        }
        try {
            return metaData.getColumnCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalArgumentException("Brak połączenia z bazą danych");
        }
        try {
            return metaData.getColumnName(column+1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getRowCount() throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalArgumentException("Brak połączenia z bazą danych");
        }

        return numberOfRows;
    }

    public Object getValueAt(int row, int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalArgumentException("Brak połączenia z bazą danych");
        }

        try {
            resultSet.absolute(row+1);
            return resultSet.getObject(column+1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void setQuery(String query) throws SQLException, IllegalStateException{
        if (!connectedToDatabase) {
            throw new IllegalArgumentException("Brak połączenia z bazą danych");
        }

        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();

        resultSet.last();
        numberOfRows = resultSet.getRow();

        fireTableStructureChanged();
    }

    public void disconnectFromDatabase(){
        if(connectedToDatabase){
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                connectedToDatabase=false;
            }

        }
    }
}
