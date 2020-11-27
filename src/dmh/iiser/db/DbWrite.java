//package dmh.iiser.db;
//
//import java.sql.*;
//
//public class DbWrite extends DbConn {
//    PreparedStatement preparedStatement;
//    Statement statement;
//    String resultString;
//    public DbWrite(){
//        resultString = "NULL";
//    }
//
//    /**
//     * Insert user data into database. The userdata consists of their username and password.
//     * At first a username lookup is performed to check whether the username exists.
//     * If username does not exist, data is written to database. (<code>data table: user</code>)
//     *
//     * @param username Username of user
//     * @param password Password of user
//     *
//     * @return NULL
//     */
//    public void writeUser(String username, String password) {
//        int userExists = checkWhetherUsernameExists(username);
//        System.out.println(userExists);
//        if (userExists == 1) {
//            resultString = "Username already exists";
//            System.out.println(resultString);
//        } else if (userExists == -1) {
//            resultString = "Error while performing lookup in database";
//            System.out.println(resultString);
//        } else if (userExists == 0) {
//            try {
//                this.preparedStatement = this.con.prepareStatement("INSERT INTO User VALUES (null, ?, ?)");
//                this.preparedStatement.setString(1, username);
//                this.preparedStatement.setString(2, password);
//                int outV = this.preparedStatement.executeUpdate();
//                System.out.println(outV + " users inserted");
//            } catch (SQLException sqlException) {
//                System.out.println("Error while pushing to database");
//                sqlException.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Insert AdminItem Product into database.
//     *
//     * @param name Name of Admin item product
//     * @param costp Cost price
//     * @param sellp Sell price
//     * @param qty Quantity
//     * @param type Type of product
//     */
//    public void writeAdminItem(String name, int costp, int sellp, int qty, String type){
//        int exists = checkWhetherAdminItemExists(name);
//        if(exists == 1){
//            resultString = "Product exists in database";
//            System.out.println(resultString);
//        }
//        else if(exists == -1){
//            resultString = "Error while performing product lookup in database";
//            System.out.println(resultString);
//        }
//        else if(exists == 0){
//            try{
//
//                this.preparedStatement = con.prepareStatement("INSERT INTO AdminItem VALUES (null, ?, ?, ?, ?, ?)");
//                this.preparedStatement.setString(1, name);
//                this.preparedStatement.setInt(2, costp);
//                this.preparedStatement.setInt(3, sellp);
//                this.preparedStatement.setInt(4, qty);
//                this.preparedStatement.setString(5, type);
//
//                int outV = this.preparedStatement.executeUpdate();
//
//                resultString = "Admin dmh.iiser.Item inserted into database";
//
//                System.out.println(resultString);
//
//            }
//            catch(java.sql.SQLException sqlException){
//                resultString = "Error while writing into database";
//                System.out.println(resultString);
//                sqlException.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String args[]){
//        DbWrite dbWrite = new DbWrite();
////        dbWrite.writeUser("hello", "1234");
//        int res = dbWrite.checkWhetherAdminItemExists("hello");
//        System.out.println(res);
//
//        dbWrite.writeAdminItem("Samsung M31", 9000, 15000, 50, "Electronics");
//    }
//}
