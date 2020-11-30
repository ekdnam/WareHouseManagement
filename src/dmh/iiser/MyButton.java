package dmh.iiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author HP
 */
public class MyButton extends javax.swing.JPanel
{

    private JLabel text_area;
    private String text;

    public MyButton(String text)
    {
        this.text = text;
        initComponents();
    }

    public MyButton()
    {
        this("");
    }

    private void initComponents()
    {

        text_area = new javax.swing.JLabel();

        text_area.setFont(new java.awt.Font("Courier New", Font.PLAIN, 18));
        setBackground(new java.awt.Color(105, 105, 105));
        text_area.setForeground(new Color(238, 238, 238));
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                formMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                formMouseExited(evt);
            }
        });
        addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                setBackground(new Color(100, 100, 100));
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                setBackground(new Color(105, 105, 105));
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
        setLayout(new java.awt.BorderLayout());

        text_area.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_area.setText(text);
        add(text_area, java.awt.BorderLayout.CENTER);
    }

    private void formMouseEntered(java.awt.event.MouseEvent evt)
    {
        this.setBackground(new Color(110, 110, 110));
    }

    private void formMouseExited(java.awt.event.MouseEvent evt)
    {
        this.setBackground(new Color(105, 105, 105));
    }
}
