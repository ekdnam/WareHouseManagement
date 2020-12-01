package dmh.iiser;

import javax.swing.*;
import java.awt.*;

class MyButton extends JButton
{

    private static final long serialVersionUID = 1L;
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    public static Color DEFAULT_BG_COLOR = new Color(85, 85, 85);
    public static Color DEFAULT_SELECTED_BG_COLOR = new Color(50, 50, 50);

    public MyButton()
    {
        this(null);
    }

    public MyButton(String text)
    {
        super(text);
        setFocusPainted(false);
        setForeground(new Color(220, 220, 220));
        setHoverBackgroundColor(new Color(50, 50, 50));
        setPressedBackgroundColor(new Color(0, 0, 0));
        setBackground(DEFAULT_BG_COLOR);
        setBorder(BorderFactory.createEmptyBorder());
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed())
        {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover())
        {
            g.setColor(hoverBackgroundColor);
        } else
        {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b)
    {
    }

    public Color getHoverBackgroundColor()
    {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor)
    {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor()
    {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor)
    {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}