package dmh.iiser;

public class Admin extends User
{
    private AllItems stock;

    public Admin(String uname, String pass)
    {
        super(uname, pass);
        stock = new AllItems();
    }

    //Class Methods
    //TODO : implement the below methods (UI implementation here - leave it for the end)
    //Use the helper functions in other classes NO AMBIGUITY / REDUNDANCY
    public void menu()
    {
        //The initial frame after logging in
    }

    public void addStockF()
    {
        //Handling frame GUI for adding stock to the warehouse
    }

    public void displayStockF()
    {
        // Handle frame GUI for displaying stock in warehouse
    }

    public void updateStockF()
    {
        // Handling frame gui when updating/changing stock details
    }

    public void serveRequests()
    {
        // Handle frame GUI when serving requests from shops
    }

    public void serveRequest(Request r)
    {
        // Serve single request here -handling cases when item not in stock or not enough quantity
    }

}
