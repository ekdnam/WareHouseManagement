package dmh.iiser;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class MyTable extends JTable
{

    private static final long serialVersionUID = 1L;

    public MyTable(String[][] data, String[] cols)
    {
        super(data, cols);
//        DefaultTableModel model = new DefaultTableModel(data, cols)
//        {
//            @Override
//            public boolean isCellEditable(int row, int column)
//            {
//                return false;
//            }
//        };
        setModel(new DefaultTableModel(data, cols)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

        });
        JTableHeader header = getTableHeader();
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        header.setBackground(new Color(50, 50, 50));
        header.setForeground(new Color(200, 200, 200));
        header.setBorder(BorderFactory.createEmptyBorder());
        setBorder(BorderFactory.createEmptyBorder());
        setRowHeight(15);
        setBackground(new Color(238, 238, 238));
    }

    public void align(int k)
    {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBorder(BorderFactory.createEmptyBorder());
        centerRenderer.setBackground(new Color(200, 200, 200));
        for (int x = 0; x < k; x++)
        {
            getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
    }
}
