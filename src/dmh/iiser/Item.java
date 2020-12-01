package dmh.iiser;

import java.io.Serializable;

// This is class for one particular item, be it in shop or warehouse
public class Item implements Serializable
{
    private int id;
    private String name;
    private int costp, sellp, qty;

    // Constructors

    // This constructor to be called ONLY when adding new Item to warehouse
    public Item(String name, int costp, int sellp, int qty)
    {
        this.name = name;
        this.costp = costp;
        this.sellp = sellp;
        this.qty = qty;
        this.id = Utilities.ITEM_CTR;
        Utilities.ITEM_CTR++;
    }

    // Use these constructor for transferring of Item objects here and there
    // so that the total count of Item s do not increase
    public Item(int id, String name, int costp, int sellp, int qty)
    {
        this.name = name;
        this.costp = costp;
        this.sellp = sellp;
        this.qty = qty;
        this.id = id;
    }

    public Item(Item it)
    {
        this(it.getId(), it.getName(), it.getCostp(), it.getSellp(), it.getQty());
    }

    // Class methods
    public String[] getDataString()
    {
        // return string format of the data
        String[] data_string = new String[5];
        data_string[0] = Integer.toString(this.id);
        data_string[1] = this.name;
        data_string[2] = Integer.toString(this.costp);
        data_string[3] = Integer.toString(this.sellp);
        data_string[4] = Integer.toString(this.qty);
        return data_string;
    }

    public void updateQty(int delta)
    {
        setQty(getQty() + delta);
    }

    // Getters and Setters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCostp()
    {
        return costp;
    }

    public void setCostp(int costp)
    {
        this.costp = costp;
    }

    public int getSellp()
    {
        return sellp;
    }

    public void setSellp(int sellp)
    {
        this.sellp = sellp;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

}
