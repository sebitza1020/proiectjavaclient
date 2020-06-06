import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactStorageControllerTest {
    Contact c = new Contact();
    ContactsApp cV = new ContactsApp();
    List<Contact> db = new ArrayList<>();
    ContactStorageController csc = new ContactStorageController(cV, c);

    @Test
    void getCarrierEnum() {
        CarrierEnum ce = CarrierEnum.Orange;
        CarrierEnum actual = CarrierEnum.valueOf("Telekom");
        assertEquals(ce, actual);
    }

    @Test
    void deleteContact() {
        int i;
        for(i=0;i<db.size();i++) {
            if(i == 1) {
                db.remove(i);
                assertNull(db.get(i));
            }
        }
    }

    @Test
    void updateContact() {
        c.setFirstName("Popescul");
        c.setLastName("Ionel");
        c.setEmail("ionicadubluve@yahoo.com");
        c.setPhoneNumber("0761946387");
        c.setCarrier(CarrierEnum.Telekom);
        db.add(c);
        for(int i=0;i<db.size();i++) {
            if(c.getLastName().equals("Ionel")) {
                c.setLastName("Ioan");
            }
        }
        for(Contact contact : db) {
            assertEquals("Ioan", contact.getLastName());
        }
    }

    @Test
    void addContact() {
        c.setFirstName("Muntean");
        c.setLastName("Sebastian");
        c.setEmail("munteansebastian@gmail.com");
        c.setPhoneNumber("0749091217");
        c.setCarrier(CarrierEnum.DigiMobil);
        db.add(c);
        for (Contact contact : db) {
            assertEquals("sebastian.muntean@ulbsibiu.ro", contact.getEmail());
        }
    }

    @Test
    void updateView() {
        String expected = cV.toString();
        assertEquals(expected, csc.toString());
    }
}