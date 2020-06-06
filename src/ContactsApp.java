import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import net.proteanit.sql.DbUtils;


public class ContactsApp implements IView{
    private JPanel Main;
    private JLabel lFName;
    private JLabel lLName;
    private JLabel lEmail;
    private JLabel lPhone;
    private JLabel lCarrier;
    private JLabel lId;
    private JTextField tFName;
    private JTextField tLName;
    private JTextField tEmail;
    private JTextField tPhone;
    private JComboBox cCarrier;
    private JButton btnAdd;
    private JButton btnRead;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTable tbContacts;
    private JTextField tID;
    Contact c = new Contact();
    ContactStorageController csc = new ContactStorageController(this, c);
    DBConn dbc = DBConn.getDBConn();
    Connection con = dbc.getConnection();


    public ContactsApp() {
        getPersonTableData();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csc.addContact(tFName, tLName, tEmail, tPhone, cCarrier);
            }
        });
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csc.getAllContacts();
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csc.updateContact(tID, tFName, tLName, tEmail, tPhone, cCarrier);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csc.deleteContact(tID, tFName, tLName, tEmail, tPhone);
            }
        });
        tbContacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                csc.getContact(tbContacts, tID, tFName, tLName, tEmail, tPhone, cCarrier);
            }
        });
    }

    @Override
    public void display(Contact contact) {
        JFrame appView = new JFrame("Contact App");
        appView.setPreferredSize(new Dimension(750,750));
        appView.setContentPane(new ContactsApp().Main);
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appView.pack();
        appView.setVisible(true);
    }

    public void getPersonTableData() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contacts");
            tbContacts.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }
}
