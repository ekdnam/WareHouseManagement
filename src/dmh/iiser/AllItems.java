package dmh.iiser;

import java.io.Serializable;
import java.util.ArrayList;

// This class contains list of all items belonging to any shop or warehouse
public class AllItems implements Serializable
{
    private static final long serialVersionUID = 1L;
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
        this.items = new ArrayList<>();
        if (allItems.items != null)
            this.items = allItems.items;
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

    public String[][] getDataString()
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

    public int isQty(String name, int qty)
    {

        if (isPresent(name))
        {
            return Math.min(getItem(name).getQty(), qty);
        }
        return -1;
    }

    public Item changeCostp(int id, int n_cost)
    {
        // Change cost price to n_cost of item with item_id = id
        int idx = getItemIdx(id);

        Item it = items.get(idx);
        if (it != null)
            it.setCostp(n_cost);

        return it;
    }

    public Item changeCostp(String name, int n_cost)
    {
        int idx = getItemIdx(name);

        Item it = items.get(idx);
        if (it != null)
            it.setCostp(n_cost);

        return it;
    }

    public Item changeSellp(int id, int n_sell)
    {
        int idx = getItemIdx(id);

        Item it = items.get(idx);
        if (it != null)
            it.setSellp(n_sell);

        return it;
    }

    public Item changeSellp(String name, int n_sell)
    {
        int idx = getItemIdx(name);

        Item it = items.get(idx);
        if (it != null)
            it.setSellp(n_sell);

        return it;
    }

    public Item updateQty(int id, int delta)
    {
        int idx = getItemIdx(id);

        Item it = items.get(idx);
        if (it != null)
            it.setQty(it.getQty() + delta);

        return it;
    }

    public Item updateQty(String name, int delta)
    {
        int idx = getItemIdx(name);

        Item it = items.get(idx);
        if (it != null)
            it.setQty(it.getQty() + delta);

        return it;
    }

    public Item changeQty(int id, int n_qty)
    {
        int idx = getItemIdx(id);

        Item it = items.get(idx);
        if (it != null)
            it.setQty(n_qty);

        return it;
    }

    public Item changeQty(String name, int n_qty)
    {
        int idx = getItemIdx(name);

        Item it = items.get(idx);
        if (it != null)
            it.setQty(n_qty);

        return it;
    }

}
