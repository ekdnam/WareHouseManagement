package dmh.iiser;

import java.sql.*;

public class Database
{
    Connection con;
    String url = "jdbc:mysql://localhost:3306/SDL";
    String username = "root";
    String password = "abcd1234";
    Statement st;

    public Database()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Executes the database query
     *
     * @param query string we want to execute
     * @return ResultSet containing the results
     */
    public ResultSet exQuery(String query)
    {
        ResultSet res = null;
        try
        {
            st = con.createStatement();
            System.out.println(query);
            res = st.executeQuery(query);

            return res;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    public int exQueryUpdate(String query)
    {
        int res = 0;
        try
        {
            st = con.createStatement();
            System.out.println(query);
            res = st.executeUpdate(query);
            return res;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    public void close()
    {
        try
        {
            st.close();
            con.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    ResultSet allUsers()
    {
        return exQuery("select * from User order by id");
    }

    ResultSet adminItems()
    {
        return exQuery("select * from AdminItem");
    }

    ResultSet shopItem(int id)
    {
        return exQuery(String.format("select * from ShopItem where shop_id = %d", id));
    }

    ResultSet shopBills(int id)
    {
        return exQuery(String.format("select * from Bills where shop_id = %d", id));
    }

    ResultSet billItems(int bill_no)
    {
        return exQuery(String.format("select * from Bill where bill_no = %d", bill_no));
    }

    ResultSet getItems(int id)
    {
        return exQuery(String.format("select * from ShopItem where id = %d", id));
    }

    ResultSet getItem(int item_id, int shop_id)
    {
        return exQuery(String.format("select * from ShopItem where id = %d and shop_id = %d", item_id, shop_id));
    }

    ResultSet getRequests()
    {
        return exQuery("select * from Requests order by req_no");
    }

    int addUser(String username, String pass)
    {
        return exQueryUpdate(String.format("insert into User(username, password) values('%s', '%s')", username, pass));
    }

    int addAdminStock(Item it)
    {
        return exQueryUpdate(String.format(
                "insert into AdminItem(id, name, costp, sellp, qty, type) values(%d, '%s',%d, %d, %d, '%s')",
                it.getId(), it.getName(), it.getCostp(), it.getSellp(), it.getQty(), ""));
    }

    int changeAdminItem(Item it)
    {
        String query = String.format("update AdminItem set costp = %d, sellp = %d, qty = %d where id = %d", it.getCostp(),
                it.getSellp(), it.getQty(), it.getId());
        return exQueryUpdate(query);
    }

    void addBill(Bill b, int shop_id)
    {
        String ins = String.format("insert into Bills values(%d, %d, '%s', %d)", b.getBillNo(), shop_id, b.getDate(), b.getTotal());
        exQueryUpdate(ins);
        for (Item it : b.getItems())
        {
            String query = String.format("insert into Bill values(%d, %d, %d, %d)", b.getBillNo(), it.getId(), it.getSellp(),
                    it.getQty());
            exQueryUpdate(query);
        }
    }

    int updateShopItem(int shop_id, int item_id, int qty)
    {
        String query = String.format("update ShopItem set qty = qty + (%d) where shop_id = %d and id = %d", qty,
                shop_id, item_id);
        return exQueryUpdate(query);
    }

    int eraseReq()
    {
        ResultSet r = exQuery("select min(req_no) from Requests");
        try
        {
            if (r.next())
            {
                String query = String.format("delete from Requests where req_no = %s", r.getString(1));
                return exQueryUpdate(query);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    int updateAdminItem(int id, int qty)
    {
        String query = String.format("update AdminItem set qty = qty+(%d) where id = %d", qty, id);
        return exQueryUpdate(query);
    }

    int addItem(int shop_id, Item it)
    {
        try
        {
            String find = String.format("select id from ShopItem where shop_id = %d and id = %d", shop_id, it.getId());
            ResultSet r = exQuery(find);
            if (r.next())
            {
                updateShopItem(shop_id, it.getId(), it.getQty());
            } else
            {
                String query = String.format("insert into ShopItem values(%d, %d, '%s', %d, %d, %d, '%s')", shop_id,
                        it.getId(), it.getName(), it.getCostp(), it.getSellp(), it.getQty(), "");
                return exQueryUpdate(query);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    ResultSet getAdminItem(String name)
    {
        return exQuery(String.format("select * from AdminItem where name = '%s'", name));
    }

    int addRequest(Request r)
    {
        String query = String.format(
                "insert into Requests(shop_id, item_name, qty, shopname) values(%d, '%s', %d, '%s')", r.getShopId(), r.getItemName(),
                r.getQty(), "shopName");
        return exQueryUpdate(query);
    }
}
