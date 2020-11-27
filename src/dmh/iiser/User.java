package dmh.iiser;

public class User
{
    private String username, password;

    //Constructors
    public User(String uname, String pass)
    {
        this.username = uname;
        this.password = pass;
    }

    public User(User u)
    {
        this.username = u.username;
        this.password = u.password;
    }

    public User()
    {
        this("", "");
    }

    //Class methods
    public boolean verify(String uname)
    {
        return this.username.equals(uname);
    }

    public boolean verify(String uname, String pass)
    {
        return this.username.equals(uname) && this.password.equals(pass);
    }
}
