package com.game.swingy.core.DataBase;

//STEP 1. Import required packages
import com.game.swingy.core.Unit.Hero.Hero;
import com.game.swingy.core.Unit.Unit;

import java.sql.*;
import java.util.ArrayList;

public class DbMySQL {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";
    private Connection conn;
    private Statement statement;

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    private String dbName = "Swingy";

    int heroId;

    public DbMySQL() {//2817654 ніна Олексан
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            createDataBase();
            createTables();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDataBase() {

        try {
            //Execute a query
            statement = conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            statement.executeUpdate(sql);

            //Open a connection
            conn.setCatalog(dbName);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public boolean hasHeroId(int id) {

        try {
            //Execute a query
            System.out.println("Creating database...");
            statement = conn.createStatement();

            String sql = "SELECT 1 FROM Hero WHERE id = " + id;
            ResultSet rs = statement.executeQuery(sql);

            int row = rs.getRow();
            return rs.getRow() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/

    private void createTables() {

        String hero;
        String villain;

        hero = "CREATE TABLE IF NOT EXISTS Hero" +
                "(id INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(30) NOT NULL, " +
                " heroClass VARCHAR(8) NOT NULL, " +
                " level TINYINT UNSIGNED NOT NULL, " +
                " attack SMALLINT  UNSIGNED NOT NULL, " +
                " defense SMALLINT UNSIGNED NOT NULL, " +
                " hitPoints SMALLINT UNSIGNED NOT NULL, " +
                " weapon SMALLINT UNSIGNED NOT NULL, " +
                " armor SMALLINT UNSIGNED NOT NULL, " +
                " helm SMALLINT UNSIGNED NOT NULL, " +
                " coordinatesX TINYINT UNSIGNED NOT NULL, " +
                " coordinatesY TINYINT UNSIGNED NOT NULL, " +
                " experience TINYINT UNSIGNED NOT NULL, " +
                " PRIMARY KEY (id))";

        villain = "CREATE TABLE IF NOT EXISTS Villain" +
                "(id INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(30) NOT NULL, " +
                " heroClass VARCHAR(8) NOT NULL, " +
                " level TINYINT UNSIGNED NOT NULL, " +
                " attack SMALLINT  UNSIGNED NOT NULL, " +
                " defense SMALLINT UNSIGNED NOT NULL, " +
                " hitPoints SMALLINT UNSIGNED NOT NULL, " +
                " weapon SMALLINT UNSIGNED NOT NULL, " +
                " armor SMALLINT UNSIGNED NOT NULL, " +
                " helm SMALLINT UNSIGNED NOT NULL, " +
                " coordinatesX TINYINT UNSIGNED NOT NULL, " +
                " coordinatesY TINYINT UNSIGNED NOT NULL, " +
                " heroId INT UNSIGNED NOT NULL, " +
                " PRIMARY KEY ( id ), " +
                " FOREIGN KEY (heroId) REFERENCES Hero (id) ON DELETE CASCADE)";

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Execute a query
            statement = conn.createStatement();

            statement.executeUpdate(hero);
            statement.executeUpdate(villain);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } /*finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try*/
    }
    public void fillUnitTable(Unit unit) {

        String query;
        if (unit instanceof Hero)
            query = "INSERT INTO Hero (name, heroClass, level, attack, " +
                    "defense, hitPoints, weapon, armor, helm, coordinatesX, " +
                    "coordinatesY, experience) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        else
            query = "INSERT INTO Villain (name, heroClass, level, attack, " +
                    "defense, hitPoints, weapon, armor, helm, coordinatesX, " +
                    "coordinatesY, heroId) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Execute a query
            statement = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, unit.getName());
            preparedStatement.setString(2, unit.getHeroClass());
            preparedStatement.setInt(3, unit.getLevel());
            preparedStatement.setInt(4, unit.getAttack());
            preparedStatement.setInt(5, unit.getDefense());
            preparedStatement.setInt(6, unit.getHitPoints());
            preparedStatement.setInt(7, unit.getArtefacts().getWeapon());
            preparedStatement.setInt(8, unit.getArtefacts().getArmor());
            preparedStatement.setInt(9, unit.getArtefacts().getHelm());
            preparedStatement.setInt(10, unit.getCoordinates().getX());
            preparedStatement.setInt(11, unit.getCoordinates().getY());
            if (unit instanceof Hero) {
                preparedStatement.setInt(12, ((Hero) unit).getExperience());
                preparedStatement.execute();
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next())
                        heroId = rs.getInt(1);
                }
            }
            else {
                preparedStatement.setInt(12, heroId);
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } /*finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try*/
    }

    public String [][] getData() {


        ArrayList<String []> rowValues = new ArrayList<String []>();
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);


            //Execute a query
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Hero");
            while (rs.next()) {
                String[] row = {
                    rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("heroClass"),
                        rs.getString("level"),
                        rs.getString("attack"),
                        rs.getString("defense"),
                        rs.getString("hitPoints"),
                        rs.getString("weapon"),
                        rs.getString("armor"),
                        rs.getString("helm"),
                        rs.getString("experience")

                };
                rowValues.add(row);
            }
            rs.close();
//TODO rs.close якось треба почитати пронього
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        String [][]row = new String[rowValues.size()][11];
        row = rowValues.toArray(row);

        return row;
    }

    public String[][] getSelectedHero(int heroId) {

        ArrayList<String []> rowValues = new ArrayList<String []>();
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);


            //Execute a query
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Hero WHERE id = " + heroId);
            while (rs.next()) {
                String []row = {
                        rs.getString("name"),
                        rs.getString("heroClass"),
                        rs.getString("level"),
                        rs.getString("attack"),
                        rs.getString("defense"),
                        rs.getString("hitPoints"),
                        rs.getString("weapon"),
                        rs.getString("armor"),
                        rs.getString("helm"),
                        rs.getString("coordinatesX"),
                        rs.getString("coordinatesY"),
                        rs.getString("experience")
                };
                rowValues.add(row);

            }
            rs.close();
//TODO rs.close якось треба почитати пронього
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        String [][]row = new String[rowValues.size()][11];
        row = rowValues.toArray(row);

        return row;
    }

    public String[][] getSelectedVillain(int heroId) {

        ArrayList<String []> rowValues = new ArrayList<String []>();
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);


            //Execute a query
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Villain WHERE heroId = " + heroId);
            while (rs.next()) {
                String []row = {
                        rs.getString("name"),
                        rs.getString("heroClass"),
                        rs.getString("level"),
                        rs.getString("attack"),
                        rs.getString("defense"),
                        rs.getString("hitPoints"),
                        rs.getString("weapon"),
                        rs.getString("armor"),
                        rs.getString("helm"),
                        rs.getString("coordinatesX"),
                        rs.getString("coordinatesY"),
                };
                rowValues.add(row);
            }
            rs.close();
//TODO rs.close якось треба почитати пронього
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        String [][]row = new String[rowValues.size()][11];
        row = rowValues.toArray(row);

        return row;
    }

    public void deleteRow(int heroId) {

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);


            //Execute a query
            statement = conn.createStatement();
            statement.execute("DELETE FROM Hero WHERE id = " + heroId);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public int getLastId() {
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //Execute a query
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Hero");
            int res = 0;
            while (rs.next())
                res = rs.getInt(1);
            return res;
            //TODO не првильно чомусь повертає результат при пустій таблиці
//TODO rs.close якось треба почитати пронього
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        throw new Error("Hero table does't empty");
    }

    public boolean isEmptyHeroTable() {

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //Execute a query
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Hero");
            return rs.next();
            //TODO не првильно чомусь повертає результат при пустій таблиці
//TODO rs.close якось треба почитати пронього
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        throw new Error("Hero table does't empty");
    }
}
