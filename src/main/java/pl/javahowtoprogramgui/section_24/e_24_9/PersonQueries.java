package pl.javahowtoprogramgui.section_24.e_24_9;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonQueries {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/result";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private Connection connection;
    private PreparedStatement selectAllPeople;
    private PreparedStatement selectPeopleByLastName;
    private PreparedStatement insertNewPerson;

//    public PersonQueries(String url, String username, String password, String query) throws SQLException {
    public PersonQueries() throws SQLException {

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            selectAllPeople = connection.prepareStatement("SELECT * FROM Addresses ORDER BY LastName, FirstName");

            selectPeopleByLastName = connection.prepareStatement("SELECT * FROM Addresses WHERE LastName LIKE ? ORDER BY LastName, FirstName");

            insertNewPerson = connection.prepareStatement("INSERT INTO Addresses (FirstName, LastName, Email, PhoneNumber) VALUES(?,?,?,?)");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public List<Person> getAllPeople() {
        try (ResultSet resultSet = selectAllPeople.executeQuery()) {
            List<Person> results = new ArrayList<>();

            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("AddressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                ));
            }

            return results;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> getPeopleByLastName(String lastName) {
        try {
            selectPeopleByLastName.setString(1, lastName); // Ustawia nazwisko
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

        try (ResultSet resultSet = selectPeopleByLastName.executeQuery()) {
            List<Person> results = new ArrayList<Person>();

            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")));
            }

            return results;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public int addPerson(String firstName, String lastName, String email, String phoneNumber){
        try {
            insertNewPerson.setString(1, firstName);
            insertNewPerson.setString(2, lastName);
            insertNewPerson.setString(3, email);
            insertNewPerson.setString(4, phoneNumber);

            return insertNewPerson.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void close(){
        try{
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
