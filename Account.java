public class Account
{
    //The base unit of my program. Has all necessary information for an account
    String username = "";
    String salt = "";
    String hash = "";
    String admin = "";
    int average; 
    public Account(String Username, String Hash, String Salt, String Admin, int Average)
    {
        username = Username;
        salt = Salt;
        hash = Hash;
        admin = Admin;
        average = Average;
    }
    public String getUsername()
    {
        //Obvious
        return username;
    }
    public String getHash()
    {
        //Obvious
        return hash;
    }
    public String getSalt()
    {
       //Obvious
        return salt;
    }
    public String getAdmin()
    {
        //Obvious
        return admin;
    }
    public int getAverage()
    {
        return average;
    }
    public String changePassword(String newPassword)
    {
        //Adds salt to userInput and hashes it
        Hash hash123 = new Hash(newPassword + this.generateSalt());
        hash = hash123.getHash();
        return hash;
    }
    public void setAverage(int Average)
    {
        //Can only be accessed by an Admin
        average = Average;
    }
     public String generateSalt()
     {
       //Pretty simple but effective salt. No one can ever guess this number output and it will likely never be the same
       salt = "" + Math.random() + System.currentTimeMillis();
       return salt;
    }
    
}
