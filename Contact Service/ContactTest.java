import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Author: William Vanderlinden
 * CS320 3-2 Milestone
 */
public class ContactTest {

    @Test
    public void testCreateValidContact() {
        Contact c = new Contact("HP001", "Harry", "Potter", "5551234567", "4 Privet Dr");
        assertEquals("HP001", c.getContactId());
        assertEquals("Harry", c.getFirstName());
        assertEquals("Potter", c.getLastName());
        assertEquals("5551234567", c.getPhone());
        assertEquals("4 Privet Dr", c.getAddress());
    }

    @Test
    public void testContactIdNotNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "Jon", "Snow", "5551234567", "Winterfell"));
    }

    @Test
    public void testContactIdMaxLength10() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("TOO_LONG_ID1", "Arya", "Stark", "5551234567", "Winterfell"));
    }

    @Test
    public void testFirstNameNotNullAndMaxLength10() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("GOT001", null, "Lannister", "5551234567", "Casterly Rock"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("GOT001", "Daeneryssss", "Targaryen", "5551234567", "Dragonstone"));
    }

    @Test
    public void testLastNameNotNullAndMaxLength10() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("HP002", "Hermione", null, "5551234567", "Hogwarts"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("HP002", "Hermione", "GrangerZZZZ", "5551234567", "Hogwarts"));
    }

    @Test
    public void testPhoneMustBeExactly10Digits() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("HP003", "Ron", "Weasley", null, "The Burrow"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("HP003", "Ron", "Weasley", "12345", "The Burrow"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("HP003", "Ron", "Weasley", "12345678901", "The Burrow"));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("HP003", "Ron", "Weasley", "12345ABCDE", "The Burrow"));
    }

    @Test
    public void testAddressNotNullAndMaxLength30() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("GOT002", "Bran", "Stark", "5551234567", null));

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("GOT002", "Bran", "Stark", "5551234567",
                        "1234567890123456789012345678901")); // 31 chars
    }

    @Test
    public void testUpdateFieldsValidations() {
        Contact c = new Contact("GOT003", "Tyrion", "Lannister", "1112223333", "Casterly Rock");

        c.setFirstName("Jaime");
        assertEquals("Jaime", c.getFirstName());

        c.setLastName("Stark");
        assertEquals("Stark", c.getLastName());

        c.setPhone("9998887777");
        assertEquals("9998887777", c.getPhone());

        c.setAddress("King's Landing");
        assertEquals("King's Landing", c.getAddress());

        // invalid updates
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("TargaryenXX")); // >10
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("999"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("1234567890123456789012345678901"));
    }
}
