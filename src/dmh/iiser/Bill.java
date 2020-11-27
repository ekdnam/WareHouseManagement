package dmh.iiser;

import java.util.ArrayList;

public class Bill
{
    private int bill_no;
    private String date;
    private ArrayList<Item> items;
    private int total;

    // Constructors
    // This constructor to be used only when actually making/generating a bill
    public Bill(String date)
    {
        this.date = date;
        this.items = new ArrayList<>();
        this.total = 0;
        this.bill_no = Utilities.BILL_CTR;
        Utilities.BILL_CTR++;
    }

    // Use these constructor for transferring of dmh.iiser.Bill objects here and there
    // so that the total count of dmh.iiser.Bill s do not increase
    public Bill(int bill_no, String date, ArrayList<Item> items, int total)
    {
        this.bill_no = bill_no;
        this.date = date;
        this.items = new ArrayList<>(items);
        this.total = total;
    }

    public Bill(Bill b)
    {
        this(b.getBillNo(), b.getDate(), b.getItems(), b.getTotal());
    }

    // Class methods
    public String[] getDataString()
    {
        String[] data = new String[3];
        data[0] = Integer.toString(bill_no);
        data[1] = date;
        data[2] = Integer.toString(total);
        return data;
    }

    public String[][] getItemDataString()
    {
        String[][] data = new String[items.size()][4];

        for (int i = 0; i < items.size(); i++)
        {
            String[] itData = items.get(i).getDataString();
            data[i][0] = Integer.toString(i + 1);
            data[i][1] = itData[1];
            data[i][2] = itData[3];
            data[i][3] = itData[4];
        }
        return data;
    }

    // Getters and Setters
    public int getBillNo()
    {
        return bill_no;
    }

    public void setBillNo(int bill_no)
    {
        this.bill_no = bill_no;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items = items;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

}
