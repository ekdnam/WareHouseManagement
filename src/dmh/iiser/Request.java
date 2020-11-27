package dmh.iiser;

//dmh.iiser.Request made by shop to the warehouse in form of dmh.iiser.Request object
public class Request
{
    private int shop_id; // shop id of the shop requesting dmh.iiser.Item with name item_name
    private String item_name;
    private int qty;

    // Constructors
    public Request(int shop_id, String item_name, int qty)
    {
        this.shop_id = shop_id;
        this.item_name = item_name;
        this.qty = qty;
    }

    // Class methods
    public String[] getDataString()
    {
        String[] data = new String[3];

        data[0] = Integer.toString(this.shop_id);
        data[1] = this.item_name;
        data[2] = Integer.toString(this.qty);

        return data;
    }

    // Getters
    public int getShopId()
    {
        return shop_id;
    }

    public String getItemName()
    {
        return item_name;
    }

    public int getQty()
    {
        return qty;
    }

}
