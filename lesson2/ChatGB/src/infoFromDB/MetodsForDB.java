package infoFromDB;

import java.sql.*;

public class MetodsForDB {


        // JDBC URL, username and password of MySQL server
        private static final String url = "jdbc:sqlite:db";

        // JDBC variables for opening and managing connection
        private static Connection con;
        private static Statement stmt;
        private static ResultSet rs;

        public static void main(String args[]) {

            try {
                // opening database connection to MySQL server
                con = DriverManager.getConnection(url);
                System.out.println("База данных подключена");

                stmt = con.createStatement();
                String query = "SELECT * FROM users";
                rs=stmt.executeQuery(query);
                System.out.println(rs);

                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String password = rs.getString(3);
                    String nick = rs.getString(4);
                    System.out.printf("id: %d, name: %s, password: %s, nick: %s %n", id, name, password, nick);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //close connection ,stmt and resultset here
                try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
                try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
            }
        }

    /*public static String changeNickDB(String newNick, String nick){
        String sql = String.format("UPDATE users SET nick = " + newNick + " WHERE nick =" +
                nick, newNick, nick);
        try {
            stmt.execute(sql);
            return newNick;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    }

