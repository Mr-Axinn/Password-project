import java.util.ArrayList;
public class Admin extends BasicUser
{
    //Child of BasicUser. Handles actions related to an admin account
    Account myAccount = new Account("", "", "", "", 0);
    ArrayList<Account> accounts = new ArrayList<Account>();
    public Admin(Account act, ArrayList<Account> Accounts)
    {
        super(act);
        myAccount = act;
        accounts = Accounts;
    }
    public int getAverage(String userName)
    {
        //Matches usernames and gets average
        for(int i = 0; i< accounts.size(); i++)
        {
            if(accounts.get(i).getUsername().equals(userName))
            {
                return accounts.get(i).getAverage();
            }
        }
        return 0;
    }
    public boolean setAverage(String userName, int average)
    {
        //Matches usernames and sets average
        for(int i = 0; i< accounts.size(); i++)
        {
            if(accounts.get(i).getUsername().equals(userName))
            {
                accounts.get(i).setAverage(average);
                return true;
            }
        }
        return false;
    }
    public boolean changeOtherPersonsPassword(String userName, String password)
    {
        //Matches usernames and changes password
        for(int i = 0; i< accounts.size(); i++)
        {
            if(accounts.get(i).getUsername().equals(userName))
            {
                accounts.get(i).changePassword(password);
                return true;
            }
        }
        return false;
    }
}
