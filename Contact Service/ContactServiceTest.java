import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Author: William Vanderlinden
 * CS320 3-2 Milestone
 */
public class ContactServiceTest {

    @Test
    public void testAddContactSuccess() {
        ContactService service = new ContactService();
        service.addContact("HP001", "Harry", "Potter", "5551234567", "4 Privet Dr");

        Contact c = service.getContact("HP001");
        assertNotNull(c);
        assertEquals("Harry", c.getFirstName());
        assertEquals("Potter", c.getLastName());
    }

    @Test
    public void testAddContactMustHaveUniqueId() {
        ContactService service = new ContactService();
        service.addContact("GOT001", "Jon", "Snow", "1112223333", "Winterfell");

        assertThrows(IllegalArgumentException.class, () ->
                service.addContact("GOT001", "Arya", "Stark", "2223334444", "Winterfell"));
    }

    @Test
    public void testDeleteContactSuccess() {
        ContactService service = new ContactService();
        service.addContact("HP002", "Hermione", "Granger", "3334445555", "Hogwarts");

        service.deleteContact("HP002");
        assertNull(service.getContact("HP002"));
    }

    @Test
    public void testDeleteContactMissingIdThrows() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("NOPE001"));
    }

    @Test
    public void testUpdateContactFieldsSuccess() {
        ContactService service = new ContactService();
        service.addContact("GOT002", "Tyrion", "Lannister", "4445556666", "Casterly Rock");

        service.updateFirstName("GOT002", "Jaime");
        service.updateLastName("GOT002", "Stark");
        service.updatePhone("GOT002", "7778889999");
        service.updateAddress("GOT002", "King's Landing");

        Contact c = service.getContact("GOT002");
        assertEquals("Jaime", c.getFirstName());
        assertEquals("Stark", c.getLastName());
        assertEquals("7778889999", c.getPhone());
        assertEquals("King's Landing", c.getAddress());
    }

    @Test
    public void testUpdateMissingIdThrows() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("MISSING", "Harry"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("MISSING", "Potter"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("MISSING", "5551234567"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("MISSING", "Hogwarts"));
    }

    @Test
    public void testUpdateRejectsInvalidData() {
        ContactService service = new ContactService();
        service.addContact("HP003", "Ron", "Weasley", "8887776666", "The Burrow");

        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("HP003", "Gilderoyyy")); // 11
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("HP003", null));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("HP003", "123"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("HP003", "1234567890123456789012345678901"));
    }
}
