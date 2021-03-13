import java.util.ArrayList;
public class BasicUser 
{
    //Parent class of Admin. Pretty much is the driver code for the accounts
    Account myAccount = new Account("", "", "", "", 0);
    public BasicUser(Account act)
    {
        myAccount = act;
    }
    public void changeMyPassword(String password)
    {
       //Pretty obvious
        myAccount.changePassword(password);
    }
    public int getCurrentAverage()
    {
        //Pretty obvious
        return myAccount.getAverage();
    }
}
