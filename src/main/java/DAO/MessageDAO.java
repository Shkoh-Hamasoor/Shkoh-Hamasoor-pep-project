package DAO;
import Model.Message;
import Util.ConnectionUtil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    /*
     * retrieve all messages
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List <Message> messages = new ArrayList<>();

        try{
            //sql logic here
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                                rs.getInt("posted_by"),
                                rs.getString("message_text"),
                                rs.getLong("time_posted_epoch"));
                messages.add(message);

            }
            
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return messages;
    }
    /*
     * retrieve message by id
     */
    public Message getMessageByID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //sql logic
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //preparedstatment logic here
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                                rs.getInt("posted_by"),
                                rs.getString("message_text"),
                                rs.getLong("time_posted_epoch"));
                return message;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /*
     * Create a new message
     * TODO write sql logic and preparedstatement logic
     */
    public Message CreateMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //sql logic here
            String sql = "TODO";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //prepared satement here
            
            preparedStatement.executeUpdate();
            return message;
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;

    }
    /*
     * delete message by id
     * TODO write sql logic and preparedstatement logic
     */
    public Message deleteMessageByID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //sql logic here
            
            String sql = "TODO";
            PreparedStatement preparedstatment = connection.prepareStatement(sql);

            //prepparedstatement here

            ResultSet rs = preparedstatment.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                                rs.getInt("posted_by"),
                                rs.getString("message_text"),
                                rs.getLong("time_posted_epoch"));
                return message;
            }

        }catch(SQLException e ){
            System.out.println(e.getMessage());

        }

        return null;

    }

    /*
     * update message text by id
     * TODO write sql logic and preparedstatement logic
     */
    public void updatMessage(int message_id, String message_text){
        Connection connection = ConnectionUtil.getConnection();

        try{
            //sql logic here
            String sql = "TODO";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedstatement logic here

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    /*
     * retrieve all messages by a particular user'
     *  TODO write sql logic and preparedstatement logic 
     */

     public List<Message> getAllMessagesFromUser(String message_text, int posted_by ){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        
        try{
            //sql logic here
            String sql = "TODO";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //write preparedstatement logic here 
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                                rs.getInt("posted_by"),
                                rs.getString("message_text"),
                                rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
     }
}
