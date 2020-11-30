package dmh.iiser;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    public ServerSocket server = null;
    public Socket socket = null;

    public Server(int port)
    {
        try
        {
            server = new ServerSocket(port);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void createServer()
    {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        boolean i = true;
        while (i)
        {
            try
            {
                Socket s = server.accept();
                Runnable t1 = new ClientHandler(s);
                executor.execute(t1);
                // Thread t = new ClientHandler(s);
                // t.start();
            } catch (Exception e)
            {
                e.printStackTrace();
                i = false;
                break;
            }
            if (!i)
                break;
        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }
    }
}
