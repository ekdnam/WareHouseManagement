package dmh.iiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Shop extends User
{
    private int id;
    private AllItems stock;
    private ArrayList<Bill> bills;

    MyFrame shopFrame;
    MyButton[] btns;

    ArrayList<BillItem> billitems = new ArrayList<>();
    MyButton clearBtn, makeBillBtn, addItemBtn;
    JLabel billingItNameLabel, billingItQtyLabel;
    BillItem nItem;
    String date;
    JScrollPane allDetailsTable;

    int tablelen, billlen;
    MyFrame detailsFrame;
    MyButton[] detailsBtns;
    JScrollPane allBillsDetails, singleBillDetails = null;
    JLabel billnoLabel;
    JTextField billNoInp;
    MyButton detailBtn;

    JLabel reqItNameLabel, reqItQtyLabel;
    JTextField reqItName, reqItQty;
    MyButton reqButton;


    // Constructors
    // Use this constructor only when signing up and success
    public Shop(String uname, String pass)
    {
        super(uname, pass);
        stock = new AllItems();
        id = Utilities.SHOP_CTR;
        Utilities.SHOP_CTR++;
    }

    public Shop(String uname, String pass, int id)
    {
        super(uname, pass);
        this.id = id;
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
    public void menu(int x, int y)
    {
        Utilities.updateCtrs();
        shopFrame = new MyFrame(x, y);
        JLabel shoplabel = new JLabel(getName());
        JSeparator label_separator = new JSeparator();
        shoplabel.setBounds(15, 15, 100, 35);
        label_separator.setBounds(15, 50, 170, 35);
        shoplabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        shoplabel.setForeground(new Color(220, 220, 220));
        int y_off = 30;
        int p_y = 80;

        btns = new MyButton[3];
        btns[0] = new MyButton("Sell Item");
        btns[1] = new MyButton("See Details");
        btns[2] = new MyButton("Request Stock");

        for (int i = 0; i < 3; i++)
        {
            Color textColor = new Color(200, 200, 200);
            btns[i].setBounds(0, p_y, 200, 30);
            btns[i].setBorder(null);
            btns[i].setOpaque(false);
            btns[i].setForeground(textColor);
            btns[i].addActionListener(new ShopMenuAction());
            // btns[i].set
            p_y += y_off;
            shopFrame.add(btns[i]);
        }

        shopFrame.add_to_left(shoplabel);
        shopFrame.add_to_left(label_separator);
        shopFrame.initialize();
        shopFrame.setVisible(true);
        setupFrame();
    }

    void setupFrame()
    {
        // menu see details of all
        int wd = shopFrame.getWidth();
        sellItemF(wd);

        requestItemF(wd);
        setAllVisFalse();
    }

    public void sellItemF(int wd)
    {
        int ht = shopFrame.getHeight();
        int off_x = wd / 3 + 10;
        int off_y = 25;
        clearBtn = new MyButton("Clear");
        clearBtn.setBounds(wd - 100, ht - 60, 90, 20);
//TODO:        clearBtn.addActionListener(new ClearBillAction());
        addItemBtn = new MyButton("Add Item");
        billingItNameLabel = new JLabel("Item Name");
        billingItQtyLabel = new JLabel("Qty");
        billingItNameLabel.setBounds(off_x, off_y, 150, 20);
        billingItQtyLabel.setBounds(off_x + 155, off_y, 100, 20);
        shopFrame.add(billingItNameLabel);
        shopFrame.add(billingItQtyLabel);

        for (BillItem it : billitems)
        {
            shopFrame.add(it.itName);
            shopFrame.add(it.itQty);
            shopFrame.add(it.name_sep);
            shopFrame.add(it.qty_sep);
            off_y = it.itName.getY();
        }
        off_y += 25;
        nItem = new BillItem(off_x, off_y);
        shopFrame.add(nItem.itName);
        shopFrame.add(nItem.itQty);
        shopFrame.add(nItem.qty_sep);
        shopFrame.add(nItem.name_sep);

        addItemBtn.setBounds(off_x + 210, off_y, 100, 20);
//TODO:        addItemBtn.addActionListener(new AddBillItemAction());
        makeBillBtn = new MyButton("GenerateBill");
        makeBillBtn.setBounds(off_x + 105, off_y + 35, 145, 20);
//TODO:        makeBillBtn.addActionListener(new GenerateBillAction());
        shopFrame.add(addItemBtn);
        shopFrame.add(makeBillBtn);
        shopFrame.add(clearBtn);
    }

    public void requestItemF(int wd)
    {

    }


    public void setupDetailsF()
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

    void setAllVisFalse()
    {
        billitems.clear();
        clearBtn.setVisible(false);
        makeBillBtn.setVisible(false);
        addItemBtn.setVisible(false);
        billingItNameLabel.setVisible(false);
        billingItQtyLabel.setVisible(false);
        nItem.setVisible(false);
//
//        allDetailsTable.setVisible(false);
//
//        reqItNameLabel.setVisible(false);
//        reqItName.setVisible(false);
//        reqItQtyLabel.setVisible(false);
//        reqItQty.setVisible(false);
//        reqButton.setVisible(false);

        for (int i = 0; i < btns.length; i++)
        {
            btns[i].setBackground(MyButton.DEFAULT_BG_COLOR);
        }
    }

    public class ShopMenuAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btns[0])
            {
                setAllVisFalse();
                sellItemF(shopFrame.getWidth());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateobj = new Date();
                date = df.format(dateobj);
                btns[0].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                shopFrame.repaint();
            } else if (e.getSource() == btns[1])
            {
                setAllVisFalse();
                setupDetailsF();
                btns[1].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
//                detailsFrame.repaint();
            } else if (e.getSource() == btns[2])
            {
                setAllVisFalse();
                requestItemF(shopFrame.getWidth());
                btns[2].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                shopFrame.repaint();
            }
        }
    }


    public void addItem(Item it)
    {
        if (it == null)
        {
            System.out.println("null");
            return;
        }

        if (stock == null) return;
        if (stock.isPresent(it.getId()))
        {
            System.out.println("check additem");

            stock.changeCostp(it.getId(), it.getCostp());
            stock.changeSellp(it.getId(), it.getSellp());
            stock.updateQty(it.getId(), it.getQty());
            System.out.println("check additem");

            return;
        }
        System.out.println("check additem");
        stock.addItem(it);
    }

    public void addBill(Bill b)
    {
        bills.add(b);
    }

    //Getters and setters

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public AllItems getStock()
    {
        return stock;
    }

    public void setStock(AllItems stock)
    {
        this.stock = stock;
    }

    public ArrayList<Bill> getBills()
    {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills)
    {
        this.bills = bills;
    }
}
