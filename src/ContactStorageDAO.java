import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public interface ContactStorageDAO {
    public void getAllContacts();
    public void addContact(JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone, JComboBox<CarrierEnum> cCarrier);
    public void updateContact(JTextField tID, JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone, JComboBox<CarrierEnum> cCarrier);
    public void deleteContact(JTextField tID, JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone);
    public void getContact(JTable tbContacts, JTextField tID, JTextField tFName, JTextField tLName, JTextField tEmail, JTextField tPhone, JComboBox<CarrierEnum> cCarrier);
}
