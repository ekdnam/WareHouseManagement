package dmh.iiser;

import javax.swing.*;

class BillItem
{
    JTextField itName;
    JTextField itQty;
    JSeparator name_sep, qty_sep;

    public BillItem(int x, int y)
    {
        itName = new JTextField();
        itName.setBorder(BorderFactory.createEmptyBorder());
        itName.setBounds(x, y, 150, 20);
        itName.setOpaque(false);
        name_sep = new JSeparator();
        name_sep.setBounds(x, y + 20, 150, 3);
        itQty = new JTextField();
        itQty.setBorder(BorderFactory.createEmptyBorder());
        itQty.setBounds(x + 155, y, 50, 20);
        itQty.setOpaque(false);
        qty_sep = new JSeparator();

        qty_sep.setBounds(x + 155, y + 20, 50, 3);
        itName.setVisible(true);
        itQty.setVisible(true);
        name_sep.setVisible(true);
        qty_sep.setVisible(true);
    }

    void setVisible(boolean k)
    {
        itName.setVisible(k);
        itQty.setVisible(k);
        name_sep.setVisible(k);
        qty_sep.setVisible(k);
    }
}