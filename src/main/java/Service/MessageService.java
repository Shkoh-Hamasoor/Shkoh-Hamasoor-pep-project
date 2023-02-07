package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;
    
    /*
     * no args constructor 
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /*
     * Constructor for MessageService
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }


    /*
     * retrieve all books
     */
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }


    /*
     * add messages 
     */
    public Message addMessage(Message message){
        int message_id = message.getMessage_id();
        if(messageDAO.getMessageByID(message_id)==null){
            return messageDAO.CreateMessage(message);

        }
        else{
            return null;
        }
    }
    
    /*
     * retrieve message by id
     */
    public Message getMessageByID(int message_id){
        return messageDAO.getMessageByID(message_id);
    }
    
    /*
     * PATCH ID and message_text
     */

    public Message updatMessageID(int message_id, String message_text){
        if(!message_text.isBlank()){
            return messageDAO.updateMessage(message_id, message_text);
        }   
        else{
            return null;
        }
    }
    }

