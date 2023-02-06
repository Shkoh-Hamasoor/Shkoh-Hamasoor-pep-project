package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;
    

    public MessageService(){
        messageDAO = new MessageDAO();
    }


    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
}
