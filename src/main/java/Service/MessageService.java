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
     * retrieve all messages
     */
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }


    /*
     * add messages 
     */
//     The creation of the message will be successful if and only if the message_text is not blank, 
//     is under 255 characters, and posted_by refers to a real, existing user. If successful, 
    public Message addMessage(Message message){
        //int message_id = message.getMessage_id();
        if(message.message_text==""){
            System.out.println("message cant be blank");
            return null;
        }
        else if(message.message_text.length()>=255){
            System.out.println("message cant be over 255 characters");
            return null;
        }
        else if(message.posted_by<=1){
            System.out.println("must be posted by an exisiting user");
            return null;
            
        }
        return messageDAO.CreateMessage(message);
    }
    
    /*
     * retrieve message by id
     */
    public Message getMessageByID(int message_id){
        return messageDAO.getMessageByID(message_id);
    }
    
    /*
     * update message by id (PATCH ID)
     */
//     the update of a message should be successful if and only 
//      if the message id already exists 
//     and the new message_text is not blank and is not over 255 characters. 

    public Message updatMessageID(int message_id, Message message){
   
         if(message.message_text !="" && message.message_text.length() <=255){
            return messageDAO.updateMessage(message_id, message);
        }   
        else{
            return null;
        }
             // if(message.getMessage_text()==""){
        //     System.out.println("message cant be blank");
        //     return null;
        // }
        // else if(message.message_text.length()>=255){
        //     System.out.println("message is over 255 characters");
        //     return null;
        // }
    }
      
    
    /*
     * delete message by ID
     */
    public Message deleteMessageByID(int message_id){
        Message deleteMessage_ID = messageDAO.getMessageByID(message_id);
        messageDAO.deleteMessageByID(message_id);
       
        if(deleteMessage_ID!=null){
           //return messageDAO.deleteMessageByID(message_id);
           return deleteMessage_ID;
        }
        
        else{
            return null;
        }
    }

    /*
     * retrieve all messages from particular user
     */
    public List<Message> getAllMessagesFromUser(int posted_by){
        return messageDAO.getAllMessagesFromUser(posted_by);
    }

    }

