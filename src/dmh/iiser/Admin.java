package dmh.iiser;

import java.util.LinkedList;
import java.util.Queue;

public class Admin extends User
{
    private AllItems stock;
    private Queue<Request> req = new LinkedList<>();

    public Admin(String uname, String pass)
    {
        super(uname, pass);
        stock = new AllItems();
    }

    //Class Methods
    //TODO : implement the below methods (UI implementation here - leave it for the end)
    //Use the helper functions in other classes NO AMBIGUITY / REDUNDANCY
    public void menu(int x, int y)
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

    public void addItem(Item it)
    {
        stock.addItem(it);
    }

    // Getter and Setters
    public void setStock(AllItems stock)
    {
        this.stock = new AllItems(stock);
    }

    public void setReq(Queue<Request> req)
    {
        this.req = new LinkedList<>(req);
    }

    public AllItems getStock()
    {
        return this.stock;
    }

    public Queue<Request> getReq()
    {
        return req;
    }
}
