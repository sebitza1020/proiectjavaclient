import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ContactStorageController implements ContactStorageDAO{
    IView view;
    Contact contacts;

    private JTextField tFName;
    private JTextField tLName;
    private JTextField tEmail;
    private JTextField tPhone;
    private JComboBox cCarrier;
    private JTable tbContacts;
    private JTextField tID;
    DBConn dbc = DBConn.getDBConn();
    Connection con = dbc.getConnection();
    public ContactsApp cApp;

    public ContactStorageController(ContactsApp cApp, Contact contacts) {
        this.cApp = cApp;
        this.contacts = contacts;
    }

    public static CarrierEnum getCarrierEnum(int index) {
        switch (index) {
            case 0:
                return CarrierEnum.Orange;
            case 1:
                return CarrierEnum.Telekom;
            case 2:
                return CarrierEnum.DigiMobil;
        }
        return CarrierEnum.Orange;
    }

    @Override
    public void getAllContacts() {
        cApp.getPersonTableData();
    }

    @Override
    public void deleteContact(JTextField tID, JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone) {
        String id = tID.getText();
        try {
            String sqlDelete = "DELETE FROM contacts WHERE id='" + id + "'";
            PreparedStatement pstmt = con.prepareStatement(sqlDelete);
            pstmt.executeUpdate();
            tID.setText("");
            tFName.setText("");
            tLName.setText("");
            tEmail.setText("");
            tPhone.setText("");
            cApp.getPersonTableData();
        } catch (Exception e3) {
            System.out.println(e3);
        }
    }

    @Override
    public void updateContact(JTextField tID, JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone, JComboBox<CarrierEnum> cCarrier) {
        String id = tID.getText();
        contacts.setFirstName(tFName.getText());
        contacts.setLastName(tLName.getText());
        contacts.setEmail(tEmail.getText());
        contacts.setPhoneNumber(tPhone.getText());
        contacts.setCarrier(getCarrierEnum(cCarrier.getSelectedIndex()));
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE contacts SET firstName='"+contacts.getFirstName()+"', lastName='"+contacts.getLastName()+"', " +
                    "email='"+contacts.getEmail()+"', phoneNumber='"+contacts.getPhoneNumber()+"', carrier='"+contacts.getCarrier()+"' WHERE id='"+id+"'");
            tID.setText("");
            tFName.setText("");
            tLName.setText("");
            tEmail.setText("");
            tPhone.setText("");
            cApp.getPersonTableData();
        } catch (Exception e4) {
            System.out.println(e4);
        }
    }

    @Override
    public void addContact(JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone, JComboBox<CarrierEnum> cCarrier) {
        contacts.setFirstName(tFName.getText());
        contacts.setLastName(tLName.getText());
        contacts.setEmail(tEmail.getText());
        contacts.setPhoneNumber(tPhone.getText());
        contacts.setCarrier(getCarrierEnum(cCarrier.getSelectedIndex()));
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO contacts (firstName, lastName, email, phoneNumber, carrier, registrationDate)" +
                    "VALUES ('"+contacts.getFirstName()+"','"+contacts.getLastName()+"', '"+contacts.getEmail()+"', '"
                    +contacts.getPhoneNumber()+"', '"+contacts.getCarrier()+"', now())");
            tFName.setText("");
            tLName.setText("");
            tEmail.setText("");
            tPhone.setText("");
            cApp.getPersonTableData();
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    @Override
    public void getContact(JTable tbContacts, JTextField tID, JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone, JComboBox<CarrierEnum> cCarrier) {
        DefaultTableModel model = (DefaultTableModel) tbContacts.getModel();
        int selectedRowIndex = tbContacts.getSelectedRow();
        tID.setText(model.getValueAt(selectedRowIndex,0).toString());
        tFName.setText(model.getValueAt(selectedRowIndex,1).toString());
        tLName.setText(model.getValueAt(selectedRowIndex,2).toString());
        tEmail.setText(model.getValueAt(selectedRowIndex,3).toString());
        tPhone.setText(model.getValueAt(selectedRowIndex,4).toString());
        Object obj = model.getValueAt(selectedRowIndex,5).toString();
        cCarrier.setSelectedItem(obj);
    }

    public void updateView(Contact c) {
        this.view.display(c);
    }
}
