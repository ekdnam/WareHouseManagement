//package dmh.iiser.db;
//
//import java.sql.*;
//import java.util.*;
//
//public class DbConn {
//    Connection con;
//    String url;
//    String dbName;
//    PreparedStatement preparedStatement;
//    ResultSet resultSet;
//
//    /*
//    Constructor
//     */
////    DbConn(String dbName){
////        try{
////            this.dbName = dbName;
////            this.url = "jdbc:mysql://localhost:3306/";
////            this.url += this.dbName;
////            Class.forName("com.mysql.cj.jdbc.Driver");
////
////            this.con = DriverManager.getConnection(url, "root", "mandke");
////        }
////        catch(ClassNotFoundException cnfe){
////            System.out.println("JDBC driver not found. Add MySql Connector Jar to the project structure");
////            cnfe.printStackTrace();
////        }
////        catch(java.sql.SQLException sqlException){
////            System.out.println("Error while establishing connection to Database. Check username and password");
////            sqlException.printStackTrace();
////        }
////    }
//
//    public DbConn(){
//        try{
//            this.dbName = "WAREHOUSE";
//            this.url = "jdbc:mysql://localhost:3306/";
//            this.url += this.dbName;
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            this.con = DriverManager.getConnection(url, "root", "mandke");
//        }
//        catch(ClassNotFoundException cnfe){
//            System.out.println("JDBC driver not found. Add MySql Connector Jar to the project structure");
//            cnfe.printStackTrace();
//        }
//        catch(java.sql.SQLException sqlException){
//            System.out.println("Error while establishing connection to Database. Check username and password");
//            sqlException.printStackTrace();
//        }
//    }
//
//    /**
//     * Performs username lookup in database
//     *
//     * @param username the username to be checked
//     * @return exists <p>whether username exists in database</p>
//     *
//     * <p>exists = 1 if username exists in database</p>
//     *
//     * <p>exists = 0 if username does not exist in database</p>
//     *
//     * <p>exists = -1 if error while performing lookup</p>
//     */
//    public int checkWhetherUsernameExists(String username){
//        int exists = -1;
//        try{
//            this.preparedStatement = con.prepareStatement("SELECT EXISTS(SELECT * FROM User WHERE username = ?)");
//            this.preparedStatement.setString(1, username);
//            this.resultSet = this.preparedStatement.executeQuery();
//
//            while(this.resultSet.next()){
//                exists = this.resultSet.getInt(1);
//            }
//            return exists;
//        }
//        catch(SQLException sqlException){
//            System.out.println("Error while searching for username in database");
//            sqlException.printStackTrace();
//        }
//        return exists;
//    }
//
//    /**
//     * A simple function to check whether product exists in database.
//     *
//     *
//     * @param name name of the admin item
//     * @return whether product exists
//     *
//     * <p></p>
//     * <p>returns 1 if product exists</p>
//     * <p>returns 0 if product does not exist</p>
//     * <p>returns -1 if error while performing lookup</p>
//     */
//    public int checkWhetherAdminItemExists(String name){
//        int exists = -1;
//        try{
//            this.preparedStatement = con.prepareStatement("SELECT EXISTS(SELECT * FROM AdminItem WHERE name = ?)");
//            this.preparedStatement.setString(1, name);
//            // Execute query
//            this.resultSet = this.preparedStatement.executeQuery();
//            // Get result
//            while(this.resultSet.next()){
//                exists = this.resultSet.getInt(1);
//            }
//            return exists;
//        }
//        catch(SQLException sqlException){
//            System.out.println("Error while searching for username in database");
//            sqlException.printStackTrace();
//        }
//        return exists;
//    }
//}
