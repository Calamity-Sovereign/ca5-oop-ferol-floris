package projectPackage.DAOs;

///** OOP Feb 2024
// *
// * Data Access Object (DAO) for User table with MySQL-specific code
// * This 'concrete' class implements the 'UserDaoInterface'.
// *
// * The DAO will contain the SQL query code to interact with the database,
// * so, the code here is specific to a MySql database.
// * No SQL queries will be used in the Business logic layer of code, thus, it
// * will be independent of the database specifics. Changes to code related to
// * the database are all contained withing the DAO code base.
// *
// *
// * The Business Logic layer is only permitted to access the database by calling
// * methods provided in the Data Access Layer - i.e. by calling the DAO methods.
// * In this way, the Business Logic layer is seperated from the database specific code
// * in the DAO layer.
// */

import projectPackage.DTOs.Team;
import projectPackage.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import javax.swing.*;

public class MySqlTeamDao extends MySqlDao implements TeamDaoInterface {
    //    /**
//     * Will access and return a List of all users in User database table
//     * @return List of User objects
//     * @throws DaoException
//     */
    //////////////////////Feature 2////////////////////////////
    @Override
    public Team getTeamById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM team WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                return new Team(result.getInt("id"),
                        result.getString("Team_Code"),
                        result.getString("Team_Name"),
                        result.getString("Region"),
                        result.getString("Division"),
                        result.getInt("Founded_in"));

            }
        } catch (SQLException e) {
            throw new DaoException("getTeamById() " + e.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("getTeamById() " + e.getMessage());
            }
        }
        return null;
    }

    ///////
    public int register(String fname, String lname, String user_name, String password) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int code = 0;

        try {
            connection = this.getConnection();

//////////////////////Feature 4 //////////////////////////////
            String query = "INSERT INTO USER VALUES ( null, ?, ?, ?, ? )";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, user_name);
            preparedStatement.setString(4, password);

            //TODO should check that this is not already in database
            // by executing a select query.

            code = preparedStatement.executeUpdate();   // executeQuery();

        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return code;     // reference to User object, or null value
    }

    //////////////////End of Feature 4//////////////

    ///////////////////Feature 1///////////////////
    ////////Finding all Teams Users/////// Floris Ferol 14 May 2024

    @Override
    public List<Team> findAllTeams() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Team> usersList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited// from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM TEAM";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String teamCode = resultSet.getString("Team_Code");
                String teamName = resultSet.getString("Team_Name");
                String region = resultSet.getString("Region");
                String division = resultSet.getString("Division");
                int Founded_in = resultSet.getInt("Founded_in");
                Team u = new Team(id, teamCode, teamName, region, division, Founded_in);
                usersList.add(u);
            }

        } catch (SQLException e) {
            throw new DaoException("find All Team ResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }

    ///////////////////End of Feature 1 code///////////////////


    ////////////////////////Feature 9///////////////////////////////////
    @Override
    public List<Integer> listOfIdTeams() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> usersList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited// from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT id FROM team";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                usersList.add(id);
            }

        } catch (SQLException e) {
            throw new DaoException("find All ID ResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }
    /////////////////////////////Feature 9//////////////////////////////

    /**
     * Given a username and password, find the corresponding User
     *
     * @param user_name
     * @param password
     * @return User object if found, or null otherwise
     * @throws DaoException
     */
    @Override
    public Team findUserByUsernamePassword(String user_name, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Team user = null;
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String teamCode = resultSet.getString("Team_Code");
                String teamName = resultSet.getString("Team_Name");
                String region = resultSet.getString("Region");
                String division = resultSet.getString("Division");
                int Founded_in = resultSet.getInt(" Founded_in");
                Team u = new Team(id, teamCode, teamName, region, division, Founded_in);
//                TeamList.add(u);
            }
        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return user;     // reference to User object, or null value
    }

/////////////////////////////////Feature 6////////////////////////////////////
// /////// Floris Ferol
    @Override
    public List<Team> findTeamYearFilter(int startyear, int endyear) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Team> usersList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited// from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM TEAM";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String teamCode = resultSet.getString("Team_Code");
                String teamName = resultSet.getString("Team_Name");
                String region = resultSet.getString("Region");
                String division = resultSet.getString("Division");
                int Founded_in = resultSet.getInt("Founded_in");

                if (Founded_in >= startyear && Founded_in <= endyear) {
                    Team u = new Team(id, teamCode, teamName, region, division, Founded_in);
                    usersList.add(u);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("find All Team ResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }
////////////////////////End of Feature 6/////////////////////////////

    //////Feature 8///////////////////////////
    // Floris Ferol
    public String teamToJson(Team team) {
        String jsonString = "";

        //Team teamToSerialise = getTeamById(id);

        Gson gsonPaser = new Gson();
        jsonString = gsonPaser.toJson(team);


        return jsonString;
    }
//////////////////////////End of Feature 8////////////////////////////

//////////////////////////////////Feature 7////////////////////////////
    public String teamListToJson(List<Team> list) {
        String jsonListString = "";

        Gson gsonPaser = new Gson();
        for (Team team : list) {
            String jsonString = gsonPaser.toJson(team);
            jsonListString += jsonString;
        }


        return jsonListString;
    }


    /////Feature 9////////////////////////
    public Team displayTeamId(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = this.getConnection();
            String query = "DISPLAY * FROM team WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                return new Team(result.getInt("id"),
                        result.getString("Team_Code"),
                        result.getString("Team_Name"),
                        result.getString("Region"),
                        result.getString("Division"),
                        result.getInt("Founded_in"));

            }
        } catch (SQLException e) {
            throw new DaoException("displayTeamId() " + e.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("displayTeamId() " + e.getMessage());
            }
        }
        return null;
    }
}





