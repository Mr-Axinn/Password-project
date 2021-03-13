import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class AccountCreator
{
    //Pretty much all driver code. Reads and writes to files. Deals with instantiating accounts 
    //and all other user objects. 
    public static void main()
   {
      //Determines basic actions of program
      while(true)
      {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try{
            //creates set of accounts and stores them in the arrayList
            initiateProgram(accounts);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to create a new account");
        System.out.println("Press 2 to login to an existing account");
        System.out.println("Press 3 to exit the program");
        //Scanner sc = new Scanner(System.in);
        String userInput = "";
        boolean goOn = false;
        while(!goOn)
        {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            
            if(userInput.length() == 1)
            {
                int ascii = (int) userInput.charAt(0);
                if(ascii > 48 && ascii < 52)
                {
                    goOn = true;
                }
                else 
                {
                    System.out.println("Please enter an integer between 1 and 3");
                }
            }
            else 
            {
                System.out.println("Please enter an integer between 1 and 3");
            }
        }
        switch (userInput)
        {
          case "1":
          //Attempts to create accounts and saves it to the file
          try{
              createAccount(accounts);
        }
        catch(Exception e)
        {
            System.out.println("An unexpected error has occured");
        }
          try{
              saveProgram(accounts);
          }
          catch (Exception e)
          {
              System.out.println(e);
          } 
          break;
          
          
          case "2":
          //Logs in the an account and creates a user object depending on whether you are admin
          boolean contin = false;
          boolean admin = false;
          String input = "1";
          Account act1 = new Account("", "","","", 0);
          Account act = new Account("", "", "", "", 0);
          while(!contin)
          {
              if(input.equals("1"))
              {
                  try{
                       act = logIn(accounts);
                    }
                catch(Exception e)
                {
                    System.out.println("An unexcpected error has occured"); 
                }
                  if(act != null)
                  {
                      act1= act;
                      contin = true;
                  }
                  else
                  {
                      System.out.println("Press 1 to try logging in again or 0 to exit");
                      Scanner sc2 = new Scanner(System.in);
                      input = sc2.nextLine();
                      if (input.equals("0"))
                      {
                          try{
                              closeProgram(accounts);
                           }
                           catch (Exception e)
                           {
                               System.out.println(e);
                            } 
                      }
                  }
              }
              else if (input.equals("0"))
              {
                    try{
                        closeProgram(accounts);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    } 
              }
              else
              {
                  System.out.println("Press 1 to try logging in again or 0 to exit");
                  Scanner sc1 = new Scanner(System.in);
                  input = sc1.nextLine();
              }
          }
          if(act1.getAdmin().equals("Admin"))
          {
              try{
              adminAccess(act1, accounts);
           }
            catch(Exception e)
           {
               System.out.println("An unexcpected error has occured"); 
            }
          }
          else
          {
               try{
             userAccess(act1, accounts);
            }
               catch(Exception e)
            {
               System.out.println("An unexcpected error has occured"); 
            }
          }
          break;
          
          case "3":
          //Closes program and saves all changes
          try{
              closeProgram(accounts);
          }
          catch (Exception e)
          {
              System.out.println(e);
          } 
       }
    }
   }
   public static void adminAccess(Account act, ArrayList<Account> accounts) 
   {
       //Driver for all admin acess functions 
       boolean cont = false;
       while(!cont)
       {
       Admin admin = new Admin(act, accounts);
       System.out.println("What would you like to do?");
       System.out.println("Press 1 to see your average");
       System.out.println("Press 2 to see someone else's average");
       System.out.println("Press 3 to change someone else's average");
       System.out.println("Press 4 to change your password");
       System.out.println("Press 5 to change someone else's password");
       System.out.println("Press 6 to log out");
       System.out.println("Press 7 to exit");
       //Scanner sc = new Scanner(System.in);
       String userInput = "";
       boolean goOn = false;
       while(!goOn)
       {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            
            if(userInput.length() == 1)
            {
                int ascii = (int) userInput.charAt(0);
                if(ascii > 48 && ascii < 56)
                {
                    goOn = true;
                }
                else 
                {
                    System.out.println("Please enter an integer between 1 and 7");
                }
            }
            else 
            {
                System.out.println("Please enter an integer between 1 and 7");
            }
        }
        switch (userInput)
        {
            case "1":
            //Gets average
            System.out.println("Your current average is " + admin.getCurrentAverage());
            break;
            
            case "2":
            //Gets other person's average
            System.out.println("Who's average would you like to see?");
            Scanner sc2 = new Scanner(System.in);
            String userName = sc2.nextLine();
            userName = userName.toLowerCase();
            int Average = admin.getAverage(userName);
            if(Average != 0)
            {
                System.out.println(userName + "'s current average is " + admin.getAverage(userName));
            }
            else
           {
                System.out.println("The username you have entered doesn't exist");
           }
            break;
            
            case "3":
            //Changes someone's average
            System.out.println("Who's average would you like to change?");
            Scanner sc10 = new Scanner(System.in);
            String UserName = sc10.nextLine();
            UserName = UserName.toLowerCase();
            int average = 0;
           
               try{
                     System.out.println("What is the new average?");
                     average = sc10.nextInt();
                     
               }
               catch(Exception e)
               {
                   System.out.println("Please enter an integer average");
                   Scanner sc11 = new Scanner(System.in);
                   average = sc11.nextInt();
               }
            
            
            boolean Continu = false;
            while(!Continu)
            {
                if(admin.setAverage(UserName, average))
                {
                    Continu = true;
                }
                else 
                {
                            System.out.println("The username you have entered doesn't exist");
                            System.out.println("Enter the name of a real user or press 1 if you would like to exit: ");
                            Scanner sc30 = new Scanner(System.in);
                            UserName = sc30.nextLine();
                            UserName = UserName.toLowerCase();
                            if(UserName.equals("1"))
                            {
                                try {
                                    closeProgram(accounts);
                                }
                                catch(Exception e)
                                {
                                    System.out.println(e);
                                }
                            
                            }
                            
                } 
            }
            try{
                saveProgram(accounts);
            }
            catch(Exception e){
                System.out.println(e);
            }
            break;
            
            case "4":
            //Attempts to create a new password for yourself
            System.out.println("What would you like your new password to be?");
            System.out.println("A password requries:");
            System.out.println("A capital letter");
            System.out.println("A special character");
            System.out.println("A minimum of 8 characters");
            Scanner sc3 = new Scanner(System.in);
            String password = sc3.nextLine();
            boolean goAhead = false;
            while(!goAhead)
            {
               if(createPassword(password))
               {
                   goAhead = true;
                   admin.changeMyPassword(password);
               }
               else
               {
                    System.out.println("Please enter another password which meet the requirements: ");
                    Scanner sc19 = new Scanner(System.in);
                    password = sc19.nextLine();
               }
            }
            try{
                saveProgram(accounts);
            }
            catch(Exception e){
                System.out.println(e);
            }
            break;
            
            case "5":
            //Changes someone else's password
            System.out.println("Who's password would you like to change?");
            Scanner sc4 = new Scanner(System.in);
            String username = sc4.nextLine();
            username = username.toLowerCase();
            System.out.println("What would you like your new password to be?");
            System.out.println("A password requries:");
            System.out.println("A capital letter");
            System.out.println("A special character");
            System.out.println("A minimum of 8 characters");
            String Password = sc4.nextLine();
            boolean GoAhead = false;
            while(!GoAhead)
            {
               //If password works
                if(createPassword(Password))
               {
                   GoAhead = true;
                   boolean Continue = false;
                   while(!Continue)
                   {
                       //If username exists
                       if(admin.changeOtherPersonsPassword(username, Password))
                       {
                           Continue = true;
                       }
                       else 
                       {
                            System.out.println("The username you have entered doesn't exist");
                            System.out.println("Enter the name of a real user or press 1 to exit: ");
                            Scanner sc4a = new Scanner(System.in);
                            username = sc4a.nextLine();
                            username = username.toLowerCase();
                            if(username.equals("1"))
                            {
                                try{
                                    closeProgram(accounts);
                                }
                                catch (Exception e)
                                {
                                    System.out.println(e);
                                } 
                            }
                       }
                   
                    }
               }
               else
               {
                    System.out.println("Please enter another password: ");
                    Scanner sc19 = new Scanner(System.in);
                    Password = sc19.nextLine();
                    
               }
            }
            try{
                saveProgram(accounts);
            }
            catch(Exception e){
                System.out.println(e);
            }
            break;
            case "6":
            //Logs out
            cont = true;
            break;
            
            case "7":
            //Closes program
            try{
                closeProgram(accounts);
            }
            catch (Exception e)
            {
                System.out.println(e);
            } 
        }
    }
    }
   public static void userAccess(Account act, ArrayList<Account> accounts) 
   {
       boolean cont = false;
       while(!cont)
       {
       BasicUser basicUser = new BasicUser(act);
       System.out.println("What would you like to do?");
       System.out.println("Press 1 to see your average");
       System.out.println("Press 2 to change your password");
       System.out.println("Press 3 to log out");
       System.out.println("Press 4 to exit");
       String userInput = "";
       boolean goOn = false;
       while(!goOn)
       {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            
            if(userInput.length() == 1)
            {
                int ascii = (int) userInput.charAt(0);
                if(ascii > 48 && ascii < 53)
                {
                    goOn = true;
                }
                else 
                {
                    System.out.println("Please enter an integer between 1 and 3");
                }
            }
            else 
            {
                System.out.println("Please enter an integer between 1 and 3");
            }
        }
        switch (userInput)
        {
            case "1":
            //Gets average
            System.out.println("Your current average is: " + basicUser.getCurrentAverage());
            break;
            
            case "2":
            //Changes password
            System.out.println("What would you like your new password to be?");
            System.out.println("A password requries:");
            System.out.println("A capital letter");
            System.out.println("A special character");
            System.out.println("A minimum of 8 characters");
            Scanner sc3 = new Scanner(System.in);
            String password = sc3.nextLine();
            boolean goAhead = false;
            while(!goAhead)
            {
               //if password exists
                if(createPassword(password))
               {
                   //Actually changes password
                   basicUser.changeMyPassword(password);
                   goAhead = true;
               }
               else
               {
                    System.out.println("Please enter another password: ");
                    Scanner sc3a = new Scanner(System.in);
                    password = sc3a.nextLine();
               }
            }
            try{
                saveProgram(accounts);
            }
            catch(Exception e){
                System.out.println(e);
            }
            break;
            
            case "3":
            //Logs out
            cont = true;
            break;
            
            case "4":
            //Closes and saves program
            try{
                closeProgram(accounts);
            }
            catch (Exception e)
            {
                System.out.println(e);
            } 
        }
    }
   }
   public static void createAccount(ArrayList<Account> accounts) throws Exception
   {
       //Creates an account and adds it to arrayList of accounts
       Boolean admin = false;
       Scanner sc = new Scanner(System.in);
       System.out.println("Press 1 if you are an admin or anything else if you are a regular user");
       String isAdmin = sc.nextLine();
       //check if you are admin
       if(isAdmin.equals("1"))
       {
           admin = true;
       }
       System.out.println("Please enter a username");
       String username = sc.nextLine();
       while(username.contains(" "))
       {
           System.out.println("Please enter a username without spaces");
           username = sc.nextLine();
       }
       
       username = username.toLowerCase();
       username = username.trim();
       boolean goAhead = false;
       while(!goAhead)
       {
           //if username is not already taken 
           if(checkUsername(username, accounts) == null)
            {
                goAhead = true;
            }
            else
            {
                System.out.println("This username was already taken");
                System.out.println("Please enter another: ");
                Scanner sc3 = new Scanner(System.in);
                username = sc3.nextLine();
                username = username.toLowerCase();
                username = username.trim();
            }
       }
       System.out.println("Please enter a password: ");
       System.out.println("A password requries:");
       System.out.println("A capital letter");
       System.out.println("A special character");
       System.out.println("A minimum of 8 characters");
       String password = sc.nextLine();
       goAhead = false;
       while(!goAhead)
       {
           //If the password matches standards 
           if(createPassword(password))
            {
                goAhead = true;
            }
            else
            {
                System.out.println("Please enter another password: ");
                password = sc.nextLine();
            }
       }
       //Creates salt and adds to password
       String salt = generateSalt();
       Hash hash156 = new Hash(password.trim() + salt);
       String hash = hash156.getHash();
       //Creates a random average
       int average = (int) (1 + Math.random() * 100);
       //Creates the account and adds it to arraylist
       if(admin)
       {
           accounts.add(new Account(username, hash, salt, "Admin", average));
       }
       else
       {
           accounts.add(new Account(username, hash, salt, "User", average));
        }
        System.out.println("You have succesfully created an account");
        try{
                saveProgram(accounts);
            }
            catch(Exception e){
                System.out.println(e);
            }
   }
   public static Account logIn(ArrayList<Account> accounts) throws Exception
   {
       //Log in system. Returns the logged in account
       Scanner sc = new Scanner(System.in);
       System.out.println("Please enter your username");
       String username = sc.nextLine();
       
       username = username.toLowerCase();
       System.out.println("Please enter your password");
       String password = sc.nextLine();
       //Checks if the account exists and return account
       Account act = checkUsername(username, accounts);
       
       if(act != null)
       {
           //Checks password
           if(checkPassword(password, act))
           {
               System.out.println("You have succesfully logged in as a " + act.getAdmin());
               return act;
           }
       }
       System.out.println("Either your username or password is incorrect");
       return null;
   }
   public static Account checkUsername(String username, ArrayList<Account> accounts)
   {
       //Pretty self explanatory. Runs through arraylist and checks if username is there
       if(username.contains(" "))
       {
           return null;
       }
       for(int i = 0; i < accounts.size(); i++)
       {
           if(username.equals(accounts.get(i).getUsername()))
           {
               return accounts.get(i);
           }
       }
       return null;
   }
   public static Boolean checkPassword(String password, Account act)
   {
       //Adds salt to password and tests if it equals the given hash
       String hashPassword = password + act.getSalt();
       Hash hash1234 = new Hash(hashPassword);
       if(hash1234.getHash().equals(act.getHash()))
       {
           return true;
       }
       return false;
   }
   public static Boolean createPassword(String password)
   {
       //Checks if a password matches conditions
       if(password.length() < 8)
       {
           System.out.println("Your password is too short");
           return false;
       }
       int counterCaps = 0;
       int counterNonLetters = 0;
       for(int i = 0; i < password.length(); i++)
       {
           char character = password.charAt(i);
           int ascii = (int) character;
           if(ascii < 33 || ascii == 34 || ascii == 92)
           {
               //I could have added more unacceptable characters but I think you get the gist
               System.out.println("You have unacceptable characters in your password");
               return false;
           }
           if(ascii > 64 && ascii < 91)
           {
               //Make sure there is a capital
               counterCaps++;
           }
           if(!Character.isLetter(character))
           {
               //Make sure there is a nonletter too
               counterNonLetters++;
           }
        }
       if(counterCaps == 0)
       {
           System.out.println("You have no capital letters");
           return false;
       }
       if(counterNonLetters == 0)
       {
           System.out.println("You have no special characters");
           return false;
       }
       return true;
   }
   public static String generateSalt()
   {
       //Pretty simple but effective salt. No one can ever guess this number output and it will likely never be the same
       return "" + Math.random() + System.currentTimeMillis();
   }
   public static void initiateProgram(ArrayList<Account> accounts) throws Exception
   {
       //Translates data from files into an arraylist of accounts
       FileReader reading = new FileReader("output.txt");
       BufferedReader br = new BufferedReader(reading);
       String currentLine = null;
       currentLine = br.readLine();
       while(currentLine != null)
       {
           String[] lines = currentLine.split(" ", 5);
           //Splits line into its components and adds them into an account
           accounts.add(new Account(lines[0].trim(), lines[1].trim(), lines[2].trim(), lines[3].trim(), Integer.parseInt(lines[4].trim())));
           currentLine = br.readLine();
       } 
   }
   public static void saveProgram(ArrayList<Account> accounts) throws Exception
   {
       //Write account information to file in specified order
       File newestFile = new File("output.txt");
       FileWriter writing = new FileWriter(newestFile);
       BufferedWriter bw = new BufferedWriter(writing);
       for(int i = 0; i < accounts.size(); i++)
       {
           bw.write(accounts.get(i).getUsername() + " ");
           bw.write(accounts.get(i).getHash() + " ");
           bw.write(accounts.get(i).getSalt() + " ");
           bw.write(accounts.get(i).getAdmin() + " ");
           bw.write(accounts.get(i).getAverage() + "");
           bw.newLine();
       }
       bw.close();
   }
   public static void closeProgram(ArrayList<Account> accounts) throws Exception
   {
       //Basically saves program and exits
       saveProgram(accounts);
       System.exit(0);
    }
}
