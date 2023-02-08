package Controller;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Message;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;

    public SocialMediaController(){
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler); //ignore

        app.get("/accounts/{account_id}/messages",this::getAllMessagesFromParticularUserHandler); // gets messages from a particular user
        app.patch("/messages/{message_id}", this::patchMessageByIDHandler);
        app.get("/messages/{message_id}", this::getMessageByIDHandler); //retrieve messages by id
        app.get("/messages", this::getAllMessagesHandler); //retrieve all messages
        app.post("/messages", this::postMessagesHandler); //create messages 
        app.delete("/messages/{message_id}", this::getDeleteMessageByIDHandler); //deletes message 
        
        return app;
    }
    

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    /*
     * Handler to retrieve all messages.
     */
    public void getAllMessagesHandler(Context context){
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);

    }

    /*
     * Handler to create new messages
     */

     public void postMessagesHandler(Context context) throws JsonProcessingException{

        ObjectMapper map = new ObjectMapper();
        Message message = map.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);

        if(addedMessage!=null){
            
            context.json(map.writeValueAsString(addedMessage));
            context.status(200);
        }

        else{
            context.status(400);
        }
    }

    /*
     * retrieve message by id
     */
    public void getMessageByIDHandler(Context context) throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        int message_id = Integer.parseInt(context.pathParam("mesage_id"));
        Message message = messageService.getMessageByID(message_id);

        if(message!=null){
            context.json(map.writeValueAsString(message));
            context.status(200);
        }

        else{
           context.status(400);
        }
    }
    /*
     * update message by id (PATCH)
     */
    public void patchMessageByIDHandler(Context context) throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message temp = map.readValue(context.body(), Message.class);
        Message message = messageService.updatMessageID(message_id, temp.getMessage_text());

        if(message!=null){
            context.json(map.writeValueAsString(message));
            context.status(200);
        }

        else{
            context.status(400);
        }
    }
    /*
     * delete message by its id
     */
    public void getDeleteMessageByIDHandler(Context context) throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.deleteMessageByID(message_id);

        if(message!=null){
            context.json(map.writeValueAsString(message));
            context.status(200);
        }
    }
    /*
     * retrieve message from particular user
     */
    public void getAllMessagesFromParticularUserHandler(Context context) throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        int posted_by = Integer.parseInt(context.pathParam("posted_by"));
        List<Message> messages = messageService.getAllMessagesFromUser(posted_by);

        if(messages!=null){
            context.json(map.writeValueAsString(messages));
            context.status(200);
        }

        else{
            context.status(400);
        }
    }
    }
    
     

