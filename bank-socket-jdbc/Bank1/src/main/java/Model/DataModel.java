package Model;

import java.sql.*;

public  class DataModel {
    private static Calculations calculations=new Calculations();
    private static Connection con;
    private static Statement statement;
    static {
        try {
             con= DriverManager.getConnection("jdbc:mysql://localhost/banksimple","root","");
             statement=con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static synchronized boolean cart2cart(int clientId,int distID,long amount){
        boolean clientRes=dbWithdraw(clientId,amount);
        if(clientRes){
            boolean destRes=dbDeposit(distID,amount);
            if(destRes){
                return true;
            }
        }
        return false;
    }
    public static synchronized boolean dbWithdraw(int clientId, long amount){
        try {
            ResultSet resultSet=statement.executeQuery(String.format("select balance from accounts where clientId = '%d' ;",clientId));
            if(resultSet.next()){
                long balance=resultSet.getInt(1);
                long calcResult;
                calcResult=calculations.withdraw(balance,amount);
                return balanceDB(calcResult,clientId);

            }else return false;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static synchronized boolean dbDeposit(int clientId, long amount){
        try {
            ResultSet resultSet=statement.executeQuery(String.format("select balance from accounts where clientId = '%d' ;",clientId));
            if(resultSet.next()){
                long balance=resultSet.getInt(1);
                long calcResult;
                calcResult=calculations.deposite(balance,amount);
                return balanceDB(calcResult,clientId);
            }else return false;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static Boolean balanceDB(Long newValue,int clientId) throws SQLException {
        int successTransaction;
        if(  newValue!= -1l){
            successTransaction=statement.executeUpdate(String.format("update accounts set balance=%d where clientId='%d';",newValue,clientId));
            if (successTransaction>0){
                return true;
            }else return false;
        }else{
            return false;
        }
    }

}

