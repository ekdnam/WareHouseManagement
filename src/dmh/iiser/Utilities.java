package dmh.iiser;

//This class contains global static variables
public class Utilities
{
    static int BILL_CTR = 1;
    static int ITEM_CTR = 1;
    static int SHOP_CTR = 1;
    static int PORT = 5555;
    static String IP = "127.0.0.1";

    static void updateCtrs()
    {
        Client c = new Client(IP, PORT);
        c.socket_write("00");
        c.socket_write(BILL_CTR);
        c.socket_write(ITEM_CTR);
        BILL_CTR = c.socket_read();
        ITEM_CTR = c.socket_read();
        c.socket_read();
        c.close();
    }
}
