package dmh.iiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author HP
 */
public class MyFrame extends JFrame implements MouseListener
{

    /**
     * Creates new form MyFrame
     */
    public MyFrame()
    {
        this(200, 150);
    }

    int win_x, win_y, win_width, win_height;
    int in_x = -1, f_x = -1;
    int in_y = -1, f_y = -1;

    private JPanel left_panel;
    private JLabel close_label;
    private JTextArea project_dets;

    MyFrame(int px, int py)
    {
        this(px, py, 600, 400);
    }

    public MyFrame(int x1, int y1, int win_width1, int ht1)
    {
        win_x = x1;
        win_y = y1;
        win_width = win_width1;
        win_height = ht1;
        // 400, 250, 600, 400
        setBounds(win_x, win_y, win_width, win_height);
        initComponents();
        // delete from here

        // to here
        addMouseListener(this);
        setUndecorated(true);

    }

    public void add_to_left(Component c)
    {
        left_panel.add(c);
    }


    public void initialize()
    {
        getContentPane().add(left_panel);
    }

    private void initComponents()
    {
        left_panel = new javax.swing.JPanel();
        close_label = new javax.swing.JLabel();
        project_dets = new JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        left_panel.setBackground(new java.awt.Color(100, 100, 100));
        left_panel.setAlignmentX(0.0F);
        left_panel.setAlignmentY(0.0F);
        left_panel.setLayout(null);
        left_panel.setBounds(0, 0, 200, 400);
        left_panel.add(project_dets);
//        project_dets.setRows(5);
//        project_dets.setFont(new java.awt.Font("Courier New", 2, 12));
//        project_dets.setText("Warehouse Management System\nProject done by \n31355 - Mufaddal Diwan\n31352 - Aditya Mandke");
//        project_dets.setBounds(0, 340, 200, 60);
//        project_dets.setBorder(BorderFactory.createEmptyBorder());
//        project_dets.setOpaque(false);
//        project_dets.setForeground(new Color(0, 0, 0));
        close_label.setForeground(new java.awt.Color(100, 100, 100));
        close_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close_label.setText("X");
        close_label.setBounds(win_width - 15, 5, 10, 10);
        add(close_label);
    }

    @Override
    public void mouseClicked(MouseEvent m)
    {
        //
    }

    @Override
    public void mousePressed(MouseEvent m)
    {
        int x = m.getX();
        int y = m.getY();
        if (x < win_width - 20 && y < 20)
        {
            in_x = m.getX();
            in_y = m.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent m)
    {
        int x = m.getX();
        int y = m.getY();
        if (in_x != -1)
        {
            f_x = x - in_x;
            f_y = y - in_y;
            win_x = win_x + f_x;
            win_y = win_y + f_y;
            in_x = in_y = f_x = f_y = -1;
            this.setBounds(win_x, win_y, win_width, win_height);
        }
        if (x > win_width - 15 && x < win_width - 5 && y > 5 && y < 20)
            dispose();

    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        //
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MyFrame login_frame = new MyFrame();

//        JLabel login_label = new JLabel("LOGIN");
//        login_frame.add_to_left(login_label);
//        login_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
//        login_label.setBounds(15, 15, 100, 35);
//
//
//        MyTextField uname_textfield;
//        JPasswordField pass_textfield;
//
//        MyLabel uname_label = new MyLabel("USERNAME");
//        MyLabel pass_label = new MyLabel("PASSWORD");
//
//        uname_textfield = new MyTextField("");
//        pass_textfield = new JPasswordField("");
//        login_frame.add_to_right(uname_textfield);
//        login_frame.add_to_right(pass_textfield);
//        login_frame.add_to_right(uname_label);
//        login_frame.add_to_right(pass_label);
//        uname_label.setBounds(170, 180, 70, 20);
//        pass_label.setBounds(170, 230, 70, 20);
//        uname_textfield.setBounds(170, 200, 200, 30);
//        pass_textfield.setBounds(170, 250, 200, 30);
//        uname_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//        pass_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        login_frame.initialize();
        login_frame.setVisible(true);

    }

}
