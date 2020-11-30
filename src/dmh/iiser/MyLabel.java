package dmh.iiser;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel
{
    public MyLabel(String s)
    {
        super(s);
        setForeground(new Color(100, 100, 100));
    }

    public MyLabel()
    {
        this("");
    }
}
