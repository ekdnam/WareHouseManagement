package dmh.iiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopUp extends JFrame implements MouseListener
{
    JLabel error;
    int myWidth, myHeight;

    public PopUp(String s, int x, int y, int width, int height)
    {
        super();
        setLayout(null);
        myHeight = 100;
        myWidth = 250;
        setBounds(x + (width - myWidth) / 2, y + (height - myHeight) / 2, myWidth, myHeight);

        JLabel closeLabel = new JLabel("CLOSE");
        add(closeLabel);
        error = new JLabel(s);
        add(error);
        error.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        error.setForeground(new Color(50, 50, 50));
        error.setBounds(0, 0, myWidth, myHeight);
        error.setHorizontalAlignment(JLabel.CENTER);
        error.setBackground(new Color(200, 200, 200));
        error.setOpaque(true);
        closeLabel.setBounds((myWidth - 44) / 2, myHeight - 20, 44, 15);
        closeLabel.setHorizontalAlignment(JLabel.CENTER);
        closeLabel.setBackground(getBackground());
        closeLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        closeLabel.setForeground(new Color(50, 50, 50));
        closeLabel.setOpaque(false);
        addMouseListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
    }

    public PopUp(String s, int x, int y)
    {
        this(s, x, y, 600, 400);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent m)
    {
        int x = m.getX();
        int y = m.getY();
        if (x > (myWidth - 40) / 2 && x < (myWidth - 40) / 2 + 40 && y > myHeight - 20 && y < myHeight - 5)
            dispose();
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }


    public static void main(String[] args)
    {
        new PopUp("this is error", 200, 150).setVisible(true);
    }

}
