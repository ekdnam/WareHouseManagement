package dmh.iiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.Queue;

public class Admin extends User
{
    private AllItems stock;
    private Queue<Request> req = new LinkedList<>();

    MyButton[] btns;
    MyFrame adminFrame;

    JLabel[] itLabel;
    JTextField[] itText;
    JSeparator[] itSep;
    MyButton addStockSubmit;

    JComboBox<String> options;
    JLabel opLabel, namelabel, vallabel;
    JTextField itname, val;
    JSeparator itname_separator, val_separator;
    MyButton updateStockSubmit;
    JScrollPane allDetailsTable;
    int tablelen;

    JScrollPane requestTable;
    JLabel servelabel;
    JTextField serveCnt;
    JSeparator cnt_separator;
    MyButton serveButton;


    public Admin(String uname, String pass)
    {
        super(uname, pass);
        stock = new AllItems();
    }

    //Class Methods
    //Use the helper functions in other classes NO AMBIGUITY / REDUNDANCY
    public void menu(int x, int y)
    {
        Utilities.updateCtrs();
        adminFrame = new MyFrame(x, y);
        JLabel adminlabel = new JLabel("ADMIN");
        JSeparator label_separator = new JSeparator();
        adminlabel.setBounds(15, 15, 100, 35);
        label_separator.setBounds(15, 50, 170, 3);
        adminlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        adminlabel.setForeground(new Color(220, 220, 220));
        int y_off = 30;
        int p_y = 80;
        btns = new MyButton[4];
        btns[0] = new MyButton("Add Stock");
        btns[1] = new MyButton("Update Stock");
        btns[2] = new MyButton("See Details");
        btns[3] = new MyButton("Serve Order");

        for (int i = 0; i < 4; i++)
        {
            Color textColor = new Color(200, 200, 200);
            btns[i].setBounds(0, p_y, 200, 30);
            btns[i].setBorder(null);
            btns[i].setOpaque(false);
            btns[i].setForeground(textColor);
            btns[i].addActionListener(new AdminMenuAction());
            // btns[i].set
            p_y += y_off;
            adminFrame.add_to_left(btns[i]);
        }

        adminFrame.add_to_left(adminlabel);
        adminFrame.add_to_left(label_separator);
        adminFrame.initialize();
        adminFrame.setVisible(true);
        setupFrame();
    }

    void setupFrame()
    {
        // menu see details
        int wd = adminFrame.getWidth();
        addStockF(wd);
        updateStockF(wd);
        displayStockF(wd);
        serveRequests(wd);

        setAllVisFalse();
    }

    public void addStockF(int wd)
    {
        Utilities.updateCtrs();
        itLabel = new JLabel[4];
        itLabel[0] = new JLabel("Item Name");
        itLabel[1] = new JLabel("Item Cost Price");
        itLabel[2] = new JLabel("Item Sell Price");
        itLabel[3] = new JLabel("Item Quantity");

        itText = new JTextField[4];
        itSep = new JSeparator[4];
        int off_y = 50;
        int pos_y = 60;
        int width = (2 * wd / 3 - 200) / 2 + wd / 3;

        for (int i = 0; i < 4; i++)
        {
            itLabel[i].setBounds(width, pos_y, 200, 20);
            itText[i] = new JTextField();
            itSep[i] = new JSeparator();
            itText[i].setBounds(width, pos_y + 19, 200, 22);
            itText[i].setOpaque(false);
            itText[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
            adminFrame.add(itLabel[i]);
            adminFrame.add(itText[i]);
            adminFrame.add(itSep[i]);
            itSep[i].setBounds(width, pos_y + 41, 200, 3);

            itText[i].setVisible(true);
            itLabel[i].setVisible(true);
            itSep[i].setVisible(true);
            pos_y += off_y;
        }
        addStockSubmit = new MyButton("Submit");
        addStockSubmit.setBounds(7 * wd / 12, pos_y + 10, 100, 30);
        addStockSubmit.setBounds(width, pos_y + 10, 200, 30);
        addStockSubmit.setOpaque(false);
        addStockSubmit.setBackground(new Color(91, 91, 91));
        adminFrame.add(addStockSubmit);
        addStockSubmit.addActionListener(new StockAddAction());
        addStockSubmit.setVisible(true);
    }

    public void displayStockF(int wd)
    {
        Client c = new Client(Utilities.IP, Utilities.PORT);
        c.socket_write("13");
        stock = c.socket_read();
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
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        allDetailsTable = new JScrollPane(table);
        tablelen = data.length;
        allDetailsTable.setBounds(wd / 3 + 10, 30, 2 * wd / 3 - 15, 19 + Math.min(10, data.length) * 19);
        allDetailsTable.setBorder(BorderFactory.createEmptyBorder());
        allDetailsTable.setOpaque(false);
        adminFrame.add(allDetailsTable);
    }

    public void updateStockF(int wd)
    {
        Utilities.updateCtrs();
        options = new JComboBox<>();
        options.addItem("Cost Price");
        options.addItem("Sell Price");
        options.addItem("Quantity");


        options.addActionListener(new ParameterSelectAction());
        int off_y = 60 + Math.min(10, tablelen) * 19;
        int off_x = wd / 3 + 10;

        opLabel = new JLabel("Change");
        opLabel.setBounds(off_x, off_y, 100, 20);
        off_y += 20;
        options.setBounds(off_x, off_y, 100, 20);
        off_y += 30;
        options.setBackground(new Color(50, 50, 50));
        options.setForeground(new Color(200, 200, 200));

        namelabel = new JLabel("Item Name");
        itname_separator = new JSeparator();
        namelabel.setBounds(off_x, off_y, 150, 20);
        off_y += 20;
        itname = new JTextField();
        itname.setBounds(off_x, off_y, 150, 20);
        itname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        itname.setOpaque(false);
        itname_separator.setBounds(off_x, off_y + 20, 150, 3);
        off_y += 30;

        vallabel = new JLabel("New Cost Price");
        val_separator = new JSeparator();
        vallabel.setBounds(off_x, off_y, 150, 20);
        off_y += 20;
        val = new JTextField();
        val.setBounds(off_x, off_y, 150, 20);
        val.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        val.setOpaque(false);
        val_separator.setBounds(off_x, off_y + 20, 150, 3);
        off_y += 30;

        updateStockSubmit = new MyButton("Submit");
        updateStockSubmit.setBounds(off_x + 30, off_y + 5, 100, 20);

        adminFrame.add(opLabel);
        adminFrame.add(options);
        adminFrame.add(namelabel);
        adminFrame.add(itname);
        adminFrame.add(itname_separator);
        adminFrame.add(vallabel);
        adminFrame.add(val);
        adminFrame.add(val_separator);
        adminFrame.add(updateStockSubmit);

        updateStockSubmit.addActionListener(new StockUpdateAction());
    }

    public void serveRequests(int wd)
    {
        Client c = new Client(Utilities.IP, Utilities.PORT);
        c.socket_write("14");
        Queue<Request> reqdisp = c.socket_read();
        c.socket_read();
        c.close();

        String[] cols = {"Shop Id", "Item Name", "Qty"};
        String[][] data = new String[reqdisp.size()][3];
        int k = reqdisp.size();


        for (int i = 0; i < k; i++)
        {
            Request r = reqdisp.remove();
            data[i] = r.getDataString();
            System.out.println(data[i].length);
            for (int j = 0; j < data[i].length; j++)
            {
                System.out.println(data[i][j]);
            }
        }

        MyTable table = new MyTable(data, cols);

        table.align(3);

        requestTable = new JScrollPane(table);
        tablelen = data.length;
        int off_x = wd / 3 + 10;
        requestTable.setBounds(off_x, 30, 2 * wd / 3 - 15, 19 + Math.min(10, tablelen) * 19);
        requestTable.setBorder(BorderFactory.createEmptyBorder());
        requestTable.setOpaque(false);

        int off_y = 50 + Math.min(10, tablelen) * 19 + 5;
        servelabel = new JLabel("Requests to Serve");
        servelabel.setBounds(off_x, off_y, 140, 20);
        serveCnt = new JTextField();
        serveCnt.setBounds(off_x + 140, off_y, 50, 20);
        serveCnt.setOpaque(false);
        serveCnt.setBorder(BorderFactory.createEmptyBorder());
        cnt_separator = new JSeparator();
        cnt_separator.setBounds(off_x + 140, off_y + 20, 50, 3);
        serveButton = new MyButton("Serve");
        serveButton.setBounds(off_x + 200, off_y, 80, 20);
        serveButton.addActionListener(new RequestServeAction());
        adminFrame.add(servelabel);
        adminFrame.add(requestTable);
        adminFrame.add(serveCnt);
        adminFrame.add(serveButton);
        adminFrame.add(cnt_separator);
    }

    public void serveRequest(Request r)
    {
        int qtya = stock.isQty(r.getItemName(), r.getQty());
        System.out.println("check 0");
        if (qtya != -1)
        {
            System.out.println("check 1");
            Item it = new Item(stock.getItem(r.getItemName()));
            it.setQty(qtya);

            Client c = new Client(Utilities.IP, Utilities.PORT);
            c.socket_write("16");
            c.socket_write(r.getShopId());
            System.out.println("check 4");
            c.socket_write(r.getItemName());
            c.socket_write(qtya);
            c.socket_write(it);
            System.out.println("check 5");
            c.socket_read();
            System.out.println("check 3");
            c.close();
            System.out.println("check 2");
            stock.updateQty(r.getItemName(), -qtya);
        } else
        {
            Client c = new Client(Utilities.IP, Utilities.PORT);
            c.socket_write("16");
            c.socket_write(r.getShopId());
            c.socket_write(r.getItemName());
            c.socket_write(-1);
            c.socket_read();
            c.close();
        }
    }

    public void setAllVisFalse()
    {
        // add Stock Components
        for (int i = 0; i < 4; i++)
        {
            itLabel[i].setVisible(false);
            itText[i].setVisible(false);
            itSep[i].setVisible(false);
        }
        addStockSubmit.setVisible(false);

        // update stock
        opLabel.setVisible(false);
        options.setVisible(false);
        namelabel.setVisible(false);
        itname.setVisible(false);
        itname_separator.setVisible(false);
        vallabel.setVisible(false);
        val.setVisible(false);
        val_separator.setVisible(false);
        updateStockSubmit.setVisible(false);

//        all details components
        allDetailsTable.setVisible(false);

//        // serve request components
        servelabel.setVisible(false);
        requestTable.setVisible(false);
        serveCnt.setVisible(false);
        serveButton.setVisible(false);
        cnt_separator.setVisible(false);
        //buttons color
        for (int i = 0; i < btns.length; i++)
        {
            btns[i].setBackground(MyButton.DEFAULT_BG_COLOR);
        }
    }


    public void addItem(Item it)
    {
        stock.addItem(it);
    }

    public class AdminMenuAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btns[0])
            {
                setAllVisFalse();
                for (int i = 0; i < 4; i++)
                {
                    itText[i].setVisible(true);
                    itLabel[i].setVisible(true);
                    itSep[i].setVisible(true);
                }
                addStockSubmit.setVisible(true);
                btns[0].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                adminFrame.repaint();
            } else if (e.getSource() == btns[1])
            {
                setAllVisFalse();
                displayStockF(adminFrame.getWidth());
                updateStockF(adminFrame.getWidth());
                btns[1].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                adminFrame.repaint();
            } else if (e.getSource() == btns[2])
            {
                setAllVisFalse();
                displayStockF(adminFrame.getWidth());

                btns[2].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                adminFrame.repaint();
            } else if (e.getSource() == btns[3])
            {
                setAllVisFalse();

                serveRequests(adminFrame.getWidth());
                adminFrame.initialize();
                btns[3].setBackground(MyButton.DEFAULT_SELECTED_BG_COLOR);
                adminFrame.repaint();
                adminFrame.setVisible(true);
            }
        }
    }

    public class StockAddAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String name = itText[0].getText();
                System.out.println(itText[3].getText() + ", " + name);
                int cost = Integer.parseInt(itText[1].getText());
                int sell = Integer.parseInt(itText[2].getText());
                int qty = Integer.parseInt(itText[3].getText());
                System.out.println(name + ", " + cost + ", " + qty);
                if (stock.isPresent(name))
                {
                    new PopUp("Item already in stock", adminFrame.getX(), adminFrame.getY()).setVisible(true);
                    return;
                }
                Utilities.updateCtrs();
                Item s = new Item(name, cost, sell, qty);

                stock.addItem(s);
                Client c = new Client(Utilities.IP, Utilities.PORT);
                c.socket_write("11");
                c.socket_write(s);
                c.socket_read();
                c.close();
                for (int i = 0; i < 4; i++)
                {
                    itText[i].setText("");
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public class StockUpdateAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Item it = null;

            new PopUp("Item not in stock", adminFrame.getX(), adminFrame.getY()).setVisible(true);
            if (options.getSelectedItem().equals("Cost Price"))
            {
                try
                {
                    String name = itname.getText();
                    int up = Integer.parseInt(val.getText());
                    it = stock.changeCostp(name, up);
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (options.getSelectedItem().equals("Sell Price"))
            {
                try
                {
                    String name = itname.getText();
                    int up = Integer.parseInt(val.getText());
                    it = stock.changeSellp(name, up);
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (options.getSelectedItem().equals("Quantity"))
            {
                try
                {
                    String name = itname.getText();
//                    it
                    int up = Integer.parseInt(val.getText());
                    it = stock.updateQty(name, up);
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (it != null)
            {
                Client c = new Client(Utilities.IP, Utilities.PORT);
                c.socket_write("12");
                c.socket_write(it);
                c.socket_read();
                c.close();
            }
            itname.setText("");
            val.setText("");
        }
    }

    public class RequestServeAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                Client c = new Client(Utilities.IP, Utilities.PORT);
                c.socket_write("14");

                req = c.socket_read();
                c.socket_read();
                c.close();

                int cnt = Integer.parseInt(serveCnt.getText());
                if (cnt > req.size())
                {
                    cnt = req.size();
                }
                for (int i = 0; i < cnt; i++)
                {
                    Request r = req.remove();
                    serveRequest(r);
                }
                c = new Client(Utilities.IP, Utilities.PORT);
                c.socket_write("15");
                c.socket_write(cnt);
                c.socket_read();
                c.close();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
            serveCnt.setText("");
//            setAllVisFalse();
//            serveRequests(adminFrame.getWidth());
        }
    }

    public class ParameterSelectAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (options.getSelectedItem().equals("Cost Price"))
            {
                vallabel.setText("New Cost Price");
            } else if (options.getSelectedItem().equals("Sell Price"))
            {
                vallabel.setText("New Sell Price");
            } else if (options.getSelectedItem().equals("Quantity"))
            {
                vallabel.setText("Update Quantity by");
            }
        }
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
