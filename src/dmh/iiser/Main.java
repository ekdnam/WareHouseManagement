package dmh.iiser;

//import dmh.iiser.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    JTextField uname_textfield;
    JPasswordField pass_textfield;

    public void login()
    {
        login_frame = new MyFrame(main_frame.getX(), main_frame.getY());
        int wd = login_frame.getWidth(), ht = login_frame.getHeight();

        JLabel login_label = new JLabel("LOGIN");
        JSeparator login_label_separator = new JSeparator();
        login_frame.add_to_left(login_label);
        login_frame.add_to_left(login_label_separator);
        login_label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        login_label.setBounds(15, 15, 170, 35);
        login_label.setForeground(new Color(220, 220, 220));
        login_label_separator.setBounds(15, 50, 170, 3);

        JLabel uname_label = new JLabel("USERNAME");
        JLabel pass_label = new JLabel("PASSWORD");
        JSeparator name_separator = new JSeparator();
        JSeparator pass_separator = new JSeparator();

        uname_textfield = new JTextField("");
        pass_textfield = new JPasswordField("");
        login_submit_btn = new MyButton("SUBMIT");

        login_frame.add(uname_label);
        login_frame.add(uname_textfield);
        login_frame.add(name_separator);
        login_frame.add(pass_label);
        login_frame.add(pass_textfield);
        login_frame.add(pass_separator);
        login_frame.add(login_submit_btn);

        uname_label.setBounds(wd / 3 + 100, ht / 3 - 20, 200, 20);
        uname_textfield.setBounds(wd / 3 + 100, ht / 3, 200, 25);
        name_separator.setBounds(wd / 3 + 100, ht / 3 + 25, 200, 3);
        pass_label.setBounds(wd / 3 + 100, ht / 3 + 30, 200, 20);
        pass_textfield.setBounds(wd / 3 + 100, ht / 3 + 50, 200, 25);
        pass_separator.setBounds(wd / 3 + 100, ht / 3 + 75, 200, 3);
        uname_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        uname_textfield.setOpaque(false);
        pass_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pass_textfield.setOpaque(false);

        login_submit_btn.setOpaque(false);
        login_submit_btn.setBackground(new Color(91, 91, 91));
        login_submit_btn.setBounds(wd / 3 + 100, 5 * ht / 8 - 15, 200, 30);
        login_submit_btn.addActionListener(new LoginSubmit());

        login_frame.initialize();
        login_frame.setVisible(true);
    }

    public void register()
    {
        signup_frame = new MyFrame(main_frame.getX(), main_frame.getY());
        int wd = signup_frame.getWidth(), ht = signup_frame.getHeight();

        JLabel signup_label = new JLabel("REGISTER");
        JSeparator signup_label_separator = new JSeparator();
        signup_frame.add_to_left(signup_label);
        signup_frame.add_to_left(signup_label_separator);
        signup_label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        signup_label.setBounds(15, 15, 100, 35);
        signup_label.setForeground(new Color(220, 220, 220));
        signup_label_separator.setBounds(15, 50, 170, 3);

        JLabel uname_label = new JLabel("USERNAME");
        JLabel pass_label = new JLabel("PASSWORD");
        JSeparator name_separator = new JSeparator();
        JSeparator pass_separator = new JSeparator();

        uname_textfield = new JTextField("");
        pass_textfield = new JPasswordField("");
        reg_submit_btn = new MyButton("SUBMIT");

        signup_frame.add(uname_label);
        signup_frame.add(uname_textfield);
        signup_frame.add(name_separator);
        signup_frame.add(pass_label);
        signup_frame.add(pass_textfield);
        signup_frame.add(pass_separator);
        signup_frame.add(reg_submit_btn);

        uname_label.setBounds(wd / 3 + 100, ht / 3 - 20, 200, 20);
        uname_textfield.setBounds(wd / 3 + 100, ht / 3, 200, 25);
        name_separator.setBounds(wd / 3 + 100, ht / 3 + 25, 200, 3);
        pass_label.setBounds(wd / 3 + 100, ht / 3 + 30, 200, 20);
        pass_textfield.setBounds(wd / 3 + 100, ht / 3 + 50, 200, 25);
        pass_separator.setBounds(wd / 3 + 100, ht / 3 + 75, 200, 3);
        uname_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        uname_textfield.setOpaque(false);
        pass_textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pass_textfield.setOpaque(false);

        reg_submit_btn.setOpaque(false);
        reg_submit_btn.setBackground(new Color(91, 91, 91));
        reg_submit_btn.setBounds(wd / 3 + 100, 5 * ht / 8 - 15, 200, 30);
        reg_submit_btn.addActionListener(new LoginSubmit());

        signup_frame.initialize();
        signup_frame.setVisible(true);
    }

    public void verification()
    {
        main_frame = new MyFrame();

        login_btn = new MyButton("Login");
        reg_btn = new MyButton("Register");
        main_frame.add(login_btn);
        main_frame.add(reg_btn);
        login_btn.setBounds(7 * main_frame.getWidth() / 12, main_frame.getHeight() / 3, 100, 30);
        reg_btn.setBounds(7 * main_frame.getWidth() / 12, main_frame.getHeight() / 3 + 40, 100, 30);
        login_btn.addActionListener(new LoginAction());
        reg_btn.addActionListener(new RegAction());

        main_frame.initialize();
        main_frame.setVisible(true);
    }

    public class LoginAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            login();
        }
    }

    public class RegAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            register();
            System.out.println("clicked reg");
        }
    }

    public class LoginSubmit implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
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
                // TODO: add pop up for invalid credentials

                c.socket_read();
                c.close();
                loggedIn = false;
                return;
            }
            if (valid.equals("ad"))
            {
                type = 0;
                admin.setReq(c.socket_read());
                AllItems it = c.socket_read();
                if (it != null)
                    admin.setStock(it);
                c.socket_read();
                c.close();
                login_frame.dispose();
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
            login_frame.dispose();
            shop.menu(login_frame.getX(), login_frame.getY());
            loggedIn = true;
        }

    }

    public class RegSubmit implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
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
                // TODO: add pop up for existing account
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

            signup_frame.dispose();
            shop.menu(signup_frame.getX(), signup_frame.getY());
            loggedIn = true;
        }

    }

    public static void main(String[] args)
    {
        Main m = new Main();
        m.admin = new Admin("admin", "admin");
        m.verification();
    }

}
