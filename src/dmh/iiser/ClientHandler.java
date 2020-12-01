package dmh.iiser;

import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * A server which implements multithreading, runs indefinitely
 */
class ClientHandler implements Runnable, Serializable
{
    Socket socket = null; // server socket that will be interacting with client
    ObjectInputStream instream = null;
    ObjectOutputStream outstream = null;
    static Admin admin; // warehouse
    static ArrayList<Shop> shops = new ArrayList<>();
    public static Queue<Request> shopreqs = new LinkedList<>();
    static Database db = new Database();

    public ClientHandler(Socket s)
    {
        try
        {
            this.socket = s;
            outstream = new ObjectOutputStream(socket.getOutputStream());
            instream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    void loginHandle()
    {
        String name = socket_read();
        String pass = socket_read();
        if (admin.verify(name, pass))
        {
            socket_write("ad");
            socket_write(admin.getReq());
            socket_write(admin.getStock());
            return;
        }
        if (admin.verify(name))
        {
            socket_write("f");
            return;
        }
        for (Shop u : shops)
        {

            if (u.verify(name, pass))
            {
                socket_write("sh");
                System.out.println(u.getName());
                socket_write(u.getId());
                socket_write(u.getStock());
                socket_write(u.getBills());
                return;
            }
        }
        socket_write("f");
    }

    void regHandle()
    {
        String name = socket_read();
        String pass = socket_read();
        if (admin.verify(name))
        {
            socket_write("f");
            return;
        }
        for (Shop u : shops)
        {
            if (u.verify(name))
            {
                socket_write("f");
                return;
            }
        }
        Shop u = new Shop(name, pass, shops.size());
        System.out.println("created");
        shops.add(u);
        db.addUser(u.getName(), u.getPassword());
        socket_write("sh");
        socket_write(u.getId());
        socket_write(u.getStock());
        socket_write(u.getBills());
        socket_write("done");
    }

    void adminPutStock()
    {
        Item it = socket_read();

        AllItems stock = new AllItems(admin.getStock());
        stock.addItem(it);
        admin.setStock(stock);
        db.addAdminStock(it);

    }

    void adminManipStock()
    {
        Item it = socket_read();
        db.changeAdminItem(it);

    }

    void adminRetStock()
    {
        admin.setStock(new AllItems());
        updateAdminItems();
        socket_write(admin.getStock());
    }

    void adminWriteReq()
    {
        shopreqs = new LinkedList<>();
        updateRequests();
        socket_write(shopreqs);
    }

    void adminReadReq()
    {
        int cnt = socket_read();
        for (int i = 0; i < cnt; i++)
        {
            shopreqs.remove();
            db.eraseReq();
        }
    }

    void adminServeReq()
    {
        int shop_id = socket_read();
        String name = socket_read();
        int qty = socket_read();
        if (qty == -1)
        {
            return;
        }
        Item it = socket_read();
        AllItems stock = admin.getStock();
        stock.updateQty(name, -qty);
        admin.setStock(stock);
        System.out.println("check 7 " + shop_id);
        System.out.println(shops.get(shop_id - 1).getId());
        shops.get(shop_id - 1).addItem(it);
        db.updateAdminItem(it.getId(), -it.getQty());
        db.addItem(shop_id, it);
        System.out.println("check done");
    }

    void shopMadeBills()
    {
        int id = socket_read();
        Shop sh = shops.get(id);
        sh.setStock(socket_read());
        sh.setBills(socket_read());
        Bill b = socket_read();
        db.addBill(b, id);
        for (Item it : b.getItems())
        {
            db.updateShopItem(id, it.getId(), -it.getQty());
        }
    }

    void shopRetDets()
    {
        int id = socket_read();
        Shop sh = shops.get(id);
        socket_write(sh.getStock());
        socket_write(sh.getBills());
    }

    void shopReq()
    {
        socket_read();
        Request r = socket_read();
        shopreqs.add(r);
        db.addRequest(r);
    }

    void updateShopStock()
    {
        int id = socket_read();
        if (shops.get(id).getStock() != null)
        {
            socket_write(true);
            socket_write(shops.get(id).getStock());
        } else
        {
            socket_write(false);
        }
    }

    void ctrSync()
    {
        int bill_ctr = socket_read();
        int it_ctr = socket_read();
        bill_ctr = Math.max(bill_ctr, Utilities.BILL_CTR);
        it_ctr = Math.max(it_ctr, Utilities.ITEM_CTR);
        Utilities.BILL_CTR = bill_ctr;
        Utilities.ITEM_CTR = it_ctr;
        socket_write(bill_ctr);
        socket_write(it_ctr);
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName());
        String type = socket_read();
        System.out.println(type);
        switch (type)
        {
            case "login":

                break;
            case "reg":
                break;
            case "11":

                break;
            case "12":
                break;

            case "13":
                break;
        }
        if (type.equals("login"))
        {
            loginHandle(); // done
        } else if (type.equals("reg"))
        {
            regHandle(); // done
        } else if (type.equals("11"))
        {
            adminPutStock(); // done
        } else if (type.equals("12"))
        {
            adminManipStock(); // done
        } else if (type.equals("13"))
        {
            adminRetStock(); // done
        } else if (type.equals("14"))
        {
            adminWriteReq(); // done
        } else if (type.equals("15"))
        {
            adminReadReq(); // done
        } else if (type.equals("16"))
        {
            System.out.println("check 6");
            adminServeReq();// done
        } else if (type.equals("21"))
        {
            shopMadeBills(); // done
        } else if (type.equals("22"))
        {
            shopRetDets(); // done
        } else if (type.equals("23"))
        {
            shopReq(); // done
        } else if (type.equals("25"))
        {
            updateShopStock();
        } else if (type.equals("00"))
        {
            ctrSync();
        }
        socket_write("done");
        close();
    }

    public <T> void socket_write(T obj)
    {
        try
        {
            outstream.writeObject(obj);
        } catch (IOException io)
        {
            io.printStackTrace();
        }
    }

    public <T> T socket_read()
    {
        T obj = null;
        try
        {
            obj = (T) instream.readObject();
            return obj;
        } catch (Exception io)
        {
            io.printStackTrace();
        }
        return obj;
    }

    public void close()
    {
        try
        {
            instream.close();
            outstream.close();
            socket.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static public void updateUsers()
    {
        ResultSet allUsers = db.allUsers();
        try
        {
            while (allUsers.next())
            {
                int id = Integer.parseInt(allUsers.getString(1));
                String username = allUsers.getString(2);
                String pass = allUsers.getString(3);
                System.out.println(username);
                Shop s = new Shop(username, pass);
                shops.add(s);
            }
            allUsers.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static public void updateAdminItems()
    {
        ResultSet aitem = db.adminItems();
        try
        {
            while (aitem.next())
            {
                int id = Integer.parseInt(aitem.getString(1));
                String name = aitem.getString(2);
                int costp = Integer.parseInt(aitem.getString(3));
                int sellp = Integer.parseInt(aitem.getString(4));
                int qty = Integer.parseInt(aitem.getString(5));
                String type = aitem.getString(6);
                Item it = new Item(id, name, costp, sellp, qty);
                admin.addItem(it);
            }
            aitem.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static public void updateShopItems(int i)
    {
        Shop shop = shops.get(i);
        ResultSet sitem = db.shopItem(shop.getId());
        try
        {
            while (sitem.next())
            {
                int id = Integer.parseInt(sitem.getString(2));
                String name = sitem.getString(3);
                int costp = Integer.parseInt(sitem.getString(4));
                int sellp = Integer.parseInt(sitem.getString(5));
                int qty = Integer.parseInt(sitem.getString(6));
                String type = sitem.getString(7);
                Item it = new Item(id, name, costp, sellp, qty);
                shop.addItem(it);
            }
            sitem.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static public void updateShopBills(int i)
    {
        Shop shop = shops.get(i);
        ResultSet sbills = db.shopBills(shop.getId());
        try
        {
            while (sbills.next())
            {
                int bill_no = Integer.parseInt(sbills.getString(1));
                String dt = sbills.getString(3);
                Bill b = new Bill(dt);
                b.setBillNo(bill_no);
                ResultSet billItems = db.billItems(bill_no);
                while (billItems.next())
                {
                    int item_id = Integer.parseInt(billItems.getString(2));
                    int qty = Integer.parseInt(billItems.getString(4));
                    ResultSet itres = db.getItems(item_id);
                    itres.next();
                    String name = itres.getString(3);
                    int costp = Integer.parseInt(itres.getString(4));
                    int sellp = Integer.parseInt(itres.getString(5));
                    Item it = new Item(item_id, name, costp, sellp, qty);
                    b.addItem(it);
                }
                billItems.close();
                shop.addBill(b);
            }
            sbills.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    static public void updateRequests()
    {
        ResultSet reqs = db.getRequests();
        try
        {
            while (reqs.next())
            {
                int shop_id = Integer.parseInt(reqs.getString(2));
                String item_name = reqs.getString(3);
                int qty = Integer.parseInt(reqs.getString(4));
                String shopname = reqs.getString(5);

                Request r = new Request(shop_id, item_name, qty);
                shopreqs.add(r);

            }
            reqs.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static public void initialize()
    {
        updateUsers();
        for (int j = 0; j < shops.size(); j++)
        {
            updateShopItems(j);
            updateShopBills(j);
        }
        updateAdminItems();
        updateRequests();
    }

    public static void main(String[] args)
    {
        admin = new Admin("admin", "admin");
        initialize();
        Server s = new Server(Utilities.PORT);
        s.createServer();
    }
}
