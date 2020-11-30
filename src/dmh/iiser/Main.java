package dmh.iiser;

//import dmh.iiser.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main
{
    //TODO: Database queries, reduce redundancy as verification is done in the program
    //TODO: Database code commented, handle later
    //TODO: These classes do not include details about UI, have to work on that after these are done

    private int type;
    private Admin admin;
    private Shop shop;
    private boolean loggedIn = false;
    MyButton login_btn;
    MyButton reg_btn;
    MyButton login_submit_btn, reg_submit_btn;
    MyFrame main_frame;
    MyFrame login_frame, signup_frame;

    MyTextField uname_textfield;
    JPasswordField pass_textfield;

    public void login()
    {
        login_frame = new MyFrame(main_frame.getX(), main_frame.getY());

        JLabel login_label = new JLabel("LOGIN");
        login_frame.add_to_left(login_label);
        login_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        login_label.setBounds(15, 15, 100, 35);


        MyLabel uname_label = new MyLabel("USERNAME");
        MyLabel pass_label = new MyLabel("PASSWORD");

        uname_textfield = new MyTextField("");
        pass_textfield = new JPasswordField("");
        login_submit_btn = new MyButton("SUBMIT");

        login_frame.add_to_right(uname_textfield);
        login_frame.add_to_right(pass_textfield);
        login_frame.add_to_right(uname_label);
        login_frame.add_to_right(pass_label);
        login_frame.add_to_right(login_submit_btn);
        uname_label.setBounds(170, 180, 70, 20);
        pass_label.setBounds(170, 230, 70, 20);
        uname_textfield.setBounds(170, 200, 200, 30);
        pass_textfield.setBounds(170, 250, 200, 30);
        uname_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pass_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        login_submit_btn.setBounds(210, 300, 90, 30);
        login_submit_btn.addMouseListener(new LoginSubmit());
        login_frame.initialize();
        login_frame.setVisible(true);
        // When user selects to login, create frame and handle here
        // Use helper function in User class - USE IT
    }

    public void register()
    {
        signup_frame = new MyFrame(main_frame.getX(), main_frame.getY());

        JLabel login_label = new JLabel("LOGIN");
        signup_frame.add_to_left(login_label);
        login_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        login_label.setBounds(15, 15, 100, 35);


        MyLabel uname_label = new MyLabel("USERNAME");
        MyLabel pass_label = new MyLabel("PASSWORD");

        uname_textfield = new MyTextField("");
        pass_textfield = new JPasswordField("");
        reg_submit_btn = new MyButton("SUBMIT");

        signup_frame.add_to_right(uname_textfield);
        signup_frame.add_to_right(pass_textfield);
        signup_frame.add_to_right(uname_label);
        signup_frame.add_to_right(pass_label);
        signup_frame.add_to_right(reg_submit_btn);
        uname_label.setBounds(170, 180, 70, 20);
        pass_label.setBounds(170, 230, 70, 20);
        uname_textfield.setBounds(170, 200, 200, 30);
        pass_textfield.setBounds(170, 250, 200, 30);
        uname_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pass_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        reg_submit_btn.setBounds(210, 300, 90, 30);
        reg_submit_btn.addMouseListener(new RegSubmit());
        signup_frame.initialize();
        signup_frame.setVisible(true);
    }

    public void verification()
    {
        main_frame = new MyFrame();

        login_btn = new MyButton("Login");
        reg_btn = new MyButton("Register");
        main_frame.add_to_right(login_btn);
        main_frame.add_to_right(reg_btn);
        login_btn.setBounds(225, 205, 100, 40);
        reg_btn.setBounds(225, 255, 100, 40);
        login_btn.addMouseListener(new LoginAction());
        reg_btn.addMouseListener(new RegAction());

        main_frame.initialize();
        main_frame.setVisible(true);
    }

    public class LoginAction implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            login();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }

    public class RegAction implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            register();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }

    public class LoginSubmit implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            String name = uname_textfield.getText();
            String pass = pass_textfield.getText();
            Client c = new Client(Utilities.IP, Utilities.PORT);
            c.socket_write("login");
            c.socket_write(name);
            c.socket_write(pass);
            String valid = c.socket_read();

            if (valid.equals("f"))
            {
                c.socket_read();
                c.close();
                loggedIn = false;
                return;
            }
            if (valid.equals("ad"))
            {
                type = 0;
                admin.setReq(c.socket_read());
                admin.setStock(c.socket_read());
                c.socket_read();
                c.close();

                admin.menu(login_frame.getX(), login_frame.getY());
                loggedIn = true;
                return;
            }
            type = 1;

            int id = c.socket_read();
            shop = new Shop(name, pass, id);
            shop.setStock(c.socket_read());
            shop.setBills(c.socket_read());
            c.socket_read();
            c.close();
            shop.menu(login_frame.getX(), login_frame.getY());
            loggedIn = true;
            login_frame.dispose();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }

    public class RegSubmit implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            String name = uname_textfield.getText();
            String pass = pass_textfield.getText();
            Client c = new Client(Utilities.IP, Utilities.PORT);

            c.socket_write("reg");
            c.socket_write(name);
            c.socket_write(pass);
            String valid = c.socket_read();
            if (valid.equals("f"))
            {
                c.socket_read();
                c.close();
                loggedIn = false;
                return;
            }

            type = 1;

            int id = c.socket_read();

            shop = new Shop(name, pass, id);

            shop.setStock(c.socket_read());
            shop.setBills(c.socket_read());
            c.socket_read();
            c.close();

            shop.menu(signup_frame.getX(), signup_frame.getY());
            loggedIn = true;
            signup_frame.dispose();

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }

    public static void main(String[] args)
    {
        Main m = new Main();
        m.admin = new Admin("admin", "admin");
        m.verification();
    }

}
