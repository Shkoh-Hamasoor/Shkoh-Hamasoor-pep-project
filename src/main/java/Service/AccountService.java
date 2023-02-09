package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accountDAO;

    /*
     * no args constructor
     */
    
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /*
     * constructor
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    /*
     * new users
     */
    public Account addnewAccount(Account account){
//The registration will be successful if and only if the username is not blank, 
//the password is at least 4 characters long, 
//and an Account with that username does not already exist. 
        if(account.username ==""){ //dont put a space (counts as a nonblank)
            System.out.println("username can't be blank ");
            return null;
        }
        else if(account.password.length() <=4){
            System.out.println("password needs to be 4 characters long ");
            return null;
        }
        else if(account.username.equals(account.username)){
            System.out.println("username is already created ");
            
            
      }
      return accountDAO.addNewAccount(account);
        
    }
    /*
     * login users
     */
    public Account getUserLogins(Account account){
        return accountDAO.getUserLogins(account);
    }
}
