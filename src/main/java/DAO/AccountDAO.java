package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    /**
     * new users
     */
    public Account addNewAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //sql logic here
            String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            //Statement.RETURN_GENERATED_KEYS
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedstatement logic here
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2,account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int generated_account_id = (int) rs.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
