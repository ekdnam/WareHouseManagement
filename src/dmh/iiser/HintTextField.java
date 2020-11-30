package dmh.iiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class HintTextField extends JTextField implements FocusListener
{

    private final String hint;
    private boolean showingHint;

    public HintTextField(final String hint)
    {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        this.setForeground(new Color(70, 70, 70));
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        if (this.getText().isEmpty())
        {
            super.setText("");
            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if (this.getText().isEmpty())
        {
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public String getText()
    {
        return showingHint ? "" : super.getText();
    }
}