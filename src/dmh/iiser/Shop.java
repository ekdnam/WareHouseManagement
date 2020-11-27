package dmh.iiser;

import java.util.ArrayList;

public class Shop extends User
{
    private int id;
    AllItems stock;
    ArrayList<Bill> bills;

    // Constructors
    // Use this constructor only when signing up and success
    public Shop(String uname, String pass)
    {
        super(uname, pass);
        id = Utilities.SHOP_CTR;
        Utilities.SHOP_CTR++;
    }

    // Use this for other purposes like moving the Shop object here and there
    public Shop(Shop s)
    {
        super(s);
        this.id = s.id;
        this.stock = new AllItems(s.stock);
        this.bills = new ArrayList<>(s.bills);
    }

    // Class methods
    //TODO: implement the below methods (UI implementation here - leave it for the end)
    public void menu()
    {
        //The initial frame after signing up/ logging in
    }

    public void sellItemF()
    {
        // Generation of bills frame GUI work here
    }

    public void detailsF()
    {
        // New Frame opens with options of different details
    }

    public void displayStockF()
    {
        // Handle frame GUI for displaying stock in this shop
    }

    public void billsDisplayF()
    {
        // Handle all bills display here
        // Use helper functions from Bill class
        // Display a particular bill on user choice call billDispayF()
    }

    public void billDisplayF()
    {
        // Handle display of items in a single bill here
    }
}
