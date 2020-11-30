package dmh.iiser;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField
{
    public MyTextField(String s)
    {
        super(s);
        setForeground(new Color(100, 100, 100));
    }

    public MyTextField()
    {
        this("");
    }
}
