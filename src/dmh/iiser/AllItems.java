package dmh.iiser;

import java.io.Serializable;
import java.util.ArrayList;

// This class contains list of all items belonging to any shop or warehouse
public class AllItems implements Serializable
{
    private ArrayList<Item> items;
    private int item_count;

    // Constructors
    public AllItems()
    {
        items = new ArrayList<>();
        item_count = 0;
    }

    public AllItems(AllItems allItems)
    {
        this.items = new ArrayList<>(allItems.items);
        this.item_count = allItems.item_count;
    }

    // Class methods / helper functions
    public void addItem(Item it)
    {
        // add item it to the list of all items belonging to any shop or warehouse
        items.add(it);
        item_count++;
    }

    public Item getItem(int item_id)
    {
        // takes item_id and return dmh.iiser.Item object with item_id = item_id else null
        int idx = getItemIdx(item_id);
        if (idx != -1)
            return items.get(idx);
        return null;
    }

    public Item getItem(String item_name)
    {
        // takes item_name and return dmh.iiser.Item object with item name = item_name else null
        int idx = getItemIdx(item_name);
        if (idx != -1)
            return items.get(idx);
        return null;
    }

    private int getItemIdx(int item_id)
    {
        // takes item_id as input and returns index at which the item with
        // item_id = item_id present in items arraylist
        for (int i = 0; i < item_count; i++)
        {
            if (items.get(i).getId() == item_id)
            {
                return i;
            }
        }
        return -1;
    }

    private int getItemIdx(String item_name)
    {
        // takes item_name as input and returns index at which the item with
        // item name = item_name present in items arraylist
        for (int i = 0; i < item_count; i++)
        {
            if (items.get(i).getName().equals(item_name))
            {
                return i;
            }
        }
        return -1;
    }

    private String[][] getDataString()
    {
        //Returns data in string format to be used in tables
        String[][] data = new String[item_count][5];
        int i = 0;
        for (Item item : items)
        {
            data[i] = item.getDataString();
            i++;
        }
        return data;
    }

    public boolean isPresent(String name)
    {
        //Returns true if item with name is present
        return getItemIdx(name) != -1;
    }

    public boolean isPresent(int id)
    {
        // Returns true id item with item_id is present
        return getItemIdx(id) != -1;
    }

    // TODO: define all these functions below, USE THE ABOVE HELPER FUNCTIONS
    public void changeCostp(int id, int n_cost)
    {
        // Change cost price to n_cost of item with item_id = id
    }

    public void changeCostp(String name, int n_cost)
    {
        // Change cost price to n_cost of item with name = name
    }

    public void changeSellp(int id, int n_sell)
    {
        // Change sell price to n_sell of item with item_id = id
    }

    public void changeSellp(String name, int n_sell)
    {
        // Change sell price to n_sell of item with name = name
    }

    public void updateQty(int id, int delta)
    {
        // Update qty in dmh.iiser.Item by delta object with item_id = id
    }

    public void updateQty(String name, int delta)
    {
        // Update qty in dmh.iiser.Item by delta object with name = name
    }

}
