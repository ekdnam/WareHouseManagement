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
    JSeparator bill_no_separator;
    MyButton detailBtn;

    JLabel reqItNameLabel, reqItQtyLabel;
    JTextField reqItName, reqItQty;
    MyButton reqButton;
    JSeparator req_it_name_separator, req_it_qty_separator;

    // Constructors
    // Use this constructor only when signing up and success
    public Shop(String uname, String pass)
    {
        super(uname, pass);
        stock = new AllItems();
        bills = new ArrayList<>();
        id = Utilities.SHOP_CTR;
        Utilities.SHOP_CTR++;
    }

    public Shop(String uname, String pass, int id)
    {
        super(uname, pass);
        this.id = id;
        bills = new ArrayList<>();
        stock = new AllItems();
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
        System.out.println(getId());
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
        clearBtn.addActionListener(new ClearBillAction());
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
        addItemBtn.addActionListener(new AddBillItemAction());
        makeBillBtn = new MyButton("GenerateBill");
        makeBillBtn.setBounds(off_x + 105, off_y + 35, 145, 20);
        makeBillBtn.addActionListener(new GenerateBillAction());
        shopFrame.add(addItemBtn);
        shopFrame.add(makeBillBtn);
        shopFrame.add(clearBtn);
    }

    public void requestItemF(int wd)
    {
        allDetailsF(wd);
        int off_x = wd / 3 + 10;
        int off_y = 29 + Math.min(10, tablelen) * 19;

        reqItNameLabel = new JLabel("Item Name");
        off_y += 30;
        reqItNameLabel.setBounds(off_x, off_y, 150, 20);
        off_y += 20;
        reqItName = new JTextField();
        req_it_name_separator = new JSeparator();
        reqItName.setBounds(off_x, off_y, 150, 20);
        reqItName.setOpaque(false);
        reqItName.setBorder(BorderFactory.createEmptyBorder());
        req_it_name_separator.setBounds(off_x, off_y + 20, 150, 3);
        off_y += 25;
        reqItQtyLabel = new JLabel("Quantity");
        reqItQtyLabel.setBounds(off_x, off_y, 150, 20);
        off_y += 20;
        reqItQty = new JTextField();
        req_it_qty_separator = new JSeparator();
        reqItQty.setBounds(off_x, off_y, 150, 20);
        reqItQty.setOpaque(false);
        reqItQty.setBorder(BorderFactory.createEmptyBorder());
        req_it_qty_separator.setBounds(off_x, off_y + 20, 150, 3);
        off_y += 30;
        reqButton = new MyButton("Request");
        reqButton.setBounds(off_x + 50, off_y, 100, 20);
        reqButton.addActionListener(new RequestAction());
        // reqButton.set
        shopFrame.add(allDetailsTable);
        shopFrame.add(reqItNameLabel);
        shopFrame.add(reqItName);
        shopFrame.add(req_it_name_separator);
        shopFrame.add(reqItQtyLabel);
        shopFrame.add(reqItQty);
        shopFrame.add(req_it_qty_separator);
        shopFrame.add(reqButton);
    }

    public void setupDetailsF()
    {
        Client c = new Client(Utilities.IP, Utilities.PORT);
        c.socket_write("22");
        c.socket_write(getId());
        stock = c.socket_read();
        bills = c.socket_read();
        c.socket_read();
        c.close();
        detailsFrame = new MyFrame(shopFrame.getX(), shopFrame.getY());
        JLabel detailFrameLabel = new JLabel("Details");
        JSeparator detail_separator = new JSeparator();
        detailFrameLabel.setBounds(15, 15, 100, 35);
        detail_separator.setBounds(15, 50, 170, 3);
        detailFrameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        detailFrameLabel.setForeground(new Color(220, 220, 220));

        int y_off = 30;
        int p_y = 80;

        detailsBtns = new MyButton[2];
        detailsBtns[0] = new MyButton("Display Items");
        detailsBtns[1] = new MyButton("Display Bills");

        for (int i = 0; i < 2; i++)
        {
            Color textColor = new Color(200, 200, 200);
            detailsBtns[i].setBounds(0, p_y, 200, 30);
            detailsBtns[i].addActionListener(new DetailsMenuAction());
            // detailsBtns[i].set
            p_y += y_off;
            detailsFrame.add(detailsBtns[i]);
        }

        detailsFrame.add_to_left(detailFrameLabel);
        detailsFrame.add_to_left(detail_separator);
        int wd = detailsFrame.getWidth();
        allBillsF(wd);
        allDetailsF(wd);
        detailsFrame.add(allDetailsTable);
        setDetailsVisFalse();
        detailsFrame.initialize();
        detailsFrame.setVisible(true);
    }

    public void allDetailsF(int wd)
    {
        Client c = new Client(Utilities.IP, Utilities.PORT);
        c.socket_write("22");
        c.socket_write(getId());
        stock = c.socket_read();
        bills = c.socket_read();
        c.socket_read();
        c.close();

        String[] cols = {"Id", "Name", "Cost", "Sell", "Qty"};
        String[][] data = stock.getDataString();

        MyTable table = new MyTable(data, cols);

        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);

        table.align(5);
        tablelen = data.length;
        allDetailsTable = new JScrollPane(table);
        allDetailsTable.setBounds(wd / 3 + 10, 30, 2 * wd / 3 - 15, 19 + Math.min(10, data.length) * 19);
        allDetailsTable.setBorder(BorderFactory.createEmptyBorder());
        allDetailsTable.setOpaque(false);
    }

    void allBillsF(int wd)
    {
        Client c = new Client(Utilities.IP, Utilities.PORT);
        c.socket_write("22");
        c.socket_write(id);
        stock = c.socket_read();
        bills = c.socket_read();
        c.socket_read();
        c.close();

        String[] cols = {"Bill No", "Date", "Total"};
        String[][] data = new String[bills.size()][3];
        int i = 0;
        for (Bill b : bills)
        {
            data[i] = b.getDataString();
            i++;
        }
        MyTable table = new MyTable(data, cols);

        table.align(3);

        allBillsDetails = new JScrollPane(table);
        billlen = data.length;

        int off_x = wd / 3 + 10;
        int off_y = 29 + Math.min(8, billlen) * 19;
        allBillsDetails.setBounds(off_x, 20, 2 * wd / 3 - 15, off_y);
        allBillsDetails.setBorder(BorderFactory.createEmptyBorder());
        allBillsDetails.setOpaque(false);
        off_y += 30;
        billnoLabel = new JLabel("Bill No");
        billnoLabel.setBounds(off_x, off_y, 200, 20);
        billNoInp = new JTextField();
        bill_no_separator = new JSeparator();
        billNoInp.setOpaque(false);
        billNoInp.setBorder(BorderFactory.createEmptyBorder());
        billNoInp.setBounds(off_x + 80, off_y, 70, 20);
        bill_no_separator.setBounds(off_x + 80, off_y + 20, 70, 3);
        detailBtn = new MyButton("View");
        detailBtn.setBounds(off_x + 160, off_y, 70, 20);

        detailBtn.addActionListener(new BillDetailAction());

        detailsFrame.add(billnoLabel);
        detailsFrame.add(billNoInp);
        detailsFrame.add(bill_no_separator);
        detailsFrame.add(detailBtn);
        detailsFrame.add(allBillsDetails);
    }

    void billDispF(int billno)
    {
        boolean flag = true;
        Bill b = null;

        for (Bill bill : bills)
        {
            if (bill.getBillNo() == billno)
            {
                b = bill;
                flag = false;
                break;
            }
        }
        if (flag)
            return;
        int wd = detailsFrame.getWidth();
        int off_x = wd / 3 + 10;
        int off_y = 88 + Math.min(8, billlen) * 18;
        int tsize;
        String[] cols = {"No.", "Item Name", "Price", "Qty"};
        String[][] data = b.getItemDataString();
        tsize = 19 + Math.min(8, data.length) * 19;
        MyTable table = new MyTable(data, cols);
        table.align(4);
        singleBillDetails = new JScrollPane(table);
        singleBillDetails.setBounds(off_x, off_y, 2 * wd / 3 - 15, tsize);
        singleBillDetails.setBorder(BorderFactory.createEmptyBorder());
        singleBillDetails.setOpaque(false);
        detailsFrame.add(singleBillDetails);
    }

    void setAllVisFalse()
    {
        for (BillItem i : billitems)
        {
            i.setVisible(false);
        }
        billitems.clear();
        clearBtn.setVisible(false);
        makeBillBtn.setVisible(false);
        addItemBtn.setVisible(false);
        billingItNameLabel.setVisible(false);
        billingItQtyLabel.setVisible(false);
        nItem.setVisible(false);

        allDetailsTable.setVisible(false);

        reqItNameLabel.setVisible(false);
        reqItName.setVisible(false);
        req_it_name_separator.setVisible(false);
        reqItQtyLabel.setVisible(false);
        reqItQty.setVisible(false);
        req_it_qty_separator.setVisible(false);
        reqButton.setVisible(false);

        for (MyButton btn : btns)
        {
            btn.setBackground(MyButton.DEFAULT_BG_COLOR);
        }
    }

    void setDetailsVisFalse()
    {
        for (int i = 0; i < 2; i++)
            detailsBtns[i].setBackground(MyButton.DEFAULT_BG_COLOR);
        allDetailsTable.setVisible(false);
//
        billnoLabel.setVisible(false);
        billNoInp.setVisible(false);
        bill_no_separator.setVisible(false);
        detailBtn.setVisible(false);
        allBillsDetails.setVisible(false);
        if (singleBillDetails != null)
            singleBillDetails.setVisible(false);
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
                detailsFrame.repaint();
            } else if (e.getSource() == btns[2])
            {
                setAllVisFalse();
                requestItemF(shopFrame.getWidth());
                btns[2].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                shopFrame.repaint();
            }
        }
    }

    public class DetailsMenuAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == detailsBtns[0])
            {
                setDetailsVisFalse();
                detailsBtns[0].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                allDetailsTable.setVisible(true);
                detailsFrame.repaint();
            } else if (e.getSource() == detailsBtns[1])
            {
                setDetailsVisFalse();
                detailsBtns[1].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                allBillsF(detailsFrame.getWidth());
                detailsFrame.repaint();
            }
        }
    }

    public class BillDetailAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //TODO: bill in range
            try
            {
                int bno = Integer.parseInt(billNoInp.getText());
                if (singleBillDetails != null)
                    detailsFrame.remove(singleBillDetails);
                detailsFrame.repaint();
                billDispF(bno);
                detailsFrame.repaint();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    class ClearBillAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for (BillItem i : billitems)
            {
                shopFrame.remove(i.itName);
                shopFrame.remove(i.itQty);
                shopFrame.remove(i.name_sep);
                shopFrame.remove(i.qty_sep);
            }
            int off_x = shopFrame.getWidth() / 3 + 10;
            shopFrame.remove(nItem.itName);
            shopFrame.remove(nItem.itQty);
            shopFrame.remove(nItem.name_sep);
            shopFrame.remove(nItem.qty_sep);
            billitems.clear();
            int off_y = 50;
            nItem = new BillItem(off_x, off_y);
            shopFrame.add(nItem.itName);
            shopFrame.add(nItem.itQty);
            shopFrame.add(nItem.name_sep);
            shopFrame.add(nItem.qty_sep);
            addItemBtn.setBounds(off_x + 210, off_y, 100, 20);
            makeBillBtn.setBounds(off_x + 105, off_y + 35, 145, 20);
            shopFrame.repaint();
        }
    }

    class AddBillItemAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            boolean isvalid = true;
            String name = "";
            int qty = 0;
            try
            {
                name = nItem.itName.getText();
                qty = Integer.parseInt(nItem.itQty.getText());
            } catch (Exception ex)
            {
                ex.printStackTrace();
                isvalid = false;
            }
            if (stock.isQty(name, qty) == -1)
            {
                isvalid = false;
            }
//            isvalid = true;
            int off_x = shopFrame.getWidth() / 3 + 10;
            if (isvalid)
            {

                billitems.add(nItem);
                int off_y = 50 + billitems.size() * 25;
                nItem = new BillItem(off_x, off_y);
                shopFrame.add(nItem.itName);
                shopFrame.add(nItem.itQty);
                shopFrame.add(nItem.name_sep);
                shopFrame.add(nItem.qty_sep);
                shopFrame.repaint();
                addItemBtn.setBounds(off_x + 210, off_y, 100, 20);
                makeBillBtn.setBounds(off_x + 105, off_y + 35, 145, 20);
            }
        }
    }

    class GenerateBillAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // logic
            //TODO : item not in stock pop up
            int off_x = shopFrame.getWidth() / 3 + 10;
            shopFrame.remove(nItem.itName);
            shopFrame.remove(nItem.itQty);
            shopFrame.remove(nItem.name_sep);
            shopFrame.remove(nItem.qty_sep);
            if (billitems.size() != 0)
            {
                Bill b = new Bill(date);
                for (BillItem i : billitems)
                {
                    String name = i.itName.getText();
                    int qty = Integer.parseInt(i.itQty.getText());
                    Item it = new Item(stock.getItem(name));
                    stock.updateQty(name, -Math.min(qty, stock.isQty(name, qty)));
                    it.setQty(Math.min(qty, stock.isQty(name, qty)));
                    b.addItem(it);
                }
                if (b.getTotal() != 0)
                {
                    bills.add(b);
                    Client c = new Client(Utilities.IP, Utilities.PORT);
                    c.socket_write("21");
                    c.socket_write(id);
                    c.socket_write(stock);
                    c.socket_write(bills);
                    c.socket_write(b);
                    c.socket_read();
                    c.close();
                } else
                {
                    Utilities.BILL_CTR--;
                }
            }
            for (BillItem i : billitems)
            {
                shopFrame.remove(i.itName);
                shopFrame.remove(i.itQty);
                shopFrame.remove(i.name_sep);
                shopFrame.remove(i.qty_sep);
            }
            billitems.clear();
            int off_y = 50;
            nItem = new BillItem(off_x, off_y);
            shopFrame.add(nItem.itName);
            shopFrame.add(nItem.itQty);
            shopFrame.add(nItem.name_sep);
            shopFrame.add(nItem.qty_sep);
            addItemBtn.setBounds(off_x + 210, off_y, 100, 20);
            makeBillBtn.setBounds(off_x + 105, off_y + 35, 145, 20);
            shopFrame.repaint();
        }
    }

    public class RequestAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String name = reqItName.getText();
                int qty = Integer.parseInt(reqItQty.getText());
                Request r = new Request(getId(), name, qty);

                Client c = new Client(Utilities.IP, Utilities.PORT);
                c.socket_write("23");
                c.socket_write(getId());
                c.socket_write(r);
                System.out.println(getId());
                c.close();
                reqItName.setText("");
                reqItQty.setText("");
            } catch (Exception ex)
            {
                ex.printStackTrace();
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
