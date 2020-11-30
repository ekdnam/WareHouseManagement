package dmh.iiser;

import java.net.*;
import java.io.*;

public class Client
{
    public Socket socket = null;
    public ObjectInputStream instream = null;
    public ObjectOutputStream outstream = null;

    public Client(String ip, int port)
    {
        try
        {
            socket = new Socket(ip, port);

            instream = new ObjectInputStream(socket.getInputStream());
            outstream = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception u)
        {
            u.printStackTrace();
        }
    }

    public <T> void socket_write(T obj)
    {
        try
        {
            outstream.writeObject(obj);
        } catch (Exception io)
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
        } catch (Exception io)
        {
            io.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Client c = new Client("127.0.0.1", 5000);
        c.socket_write("hello");
        c.close();
    }
}
