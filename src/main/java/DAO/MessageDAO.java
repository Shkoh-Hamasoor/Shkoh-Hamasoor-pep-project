package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    /*
     * retrieve all messages
     * TODO FINISHED
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
     * TODO FINISHED
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
     * TODO FINISHED
     */
    public Message CreateMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //sql logic here
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //prepared satement here
            // preparedStatement.setInt(1,message.getMessage_id());
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();  
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int generated_createMessage_id = (int) rs.getLong(1);
                return new Message (generated_createMessage_id,message.getPosted_by(),
                            message.getMessage_text(),message.getTime_posted_epoch());  
            }
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;

    }
    /*
     * delete message by id
     * TODO FINISHED
     */
    public Message deleteMessageByID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //sql logic here
            
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement preparedstatment = connection.prepareStatement(sql);

            //prepparedstatement here
            preparedstatment.setInt(1, message_id);

            preparedstatment.executeUpdate();

        }catch(SQLException e ){
            System.out.println(e.getMessage());

        }

        return null;

    }

    /*
     * update message text by id
     * TODO FINISHED
     */
    public Message updateMessage(int message_id, Message message){
        Connection connection = ConnectionUtil.getConnection();

        try{
            //sql logic here
            String sql = "UPDATE message SET message_text = ? WHERE message_id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //preparedstatement logic here
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();
            return getMessageByID(message_id);
            //ResultSet rs = preparedStatement.executeQuery();
            // while(rs.next()){
               
            //     Message message = new Message(rs.getInt("message_id"),
            //                     rs.getInt("posted_by"),
            //                     rs.getString("message_text"),
            //                     rs.getLong("time_posted_epoch"));
            //     return message;
            // }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /*
     * retrieve all messages by a particular user'
     *  TODO FINISHED
     */

     public List<Message> getAllMessagesFromUser( int posted_by ){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        
        try{
            //sql logic here
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedstatement logic here 
            preparedStatement.setInt(1, posted_by);

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
