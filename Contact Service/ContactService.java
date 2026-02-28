import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: William Vanderlinden
 * CS320 3-2 Milestone
 */
public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    // Add a contact with a unique ID
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID must be unique");
        }
        contacts.put(id, contact);
    }

    // Convenience method
    public void addContact(String contactId, String firstName, String lastName, String phone, String address) {
        addContact(new Contact(contactId, firstName, lastName, phone, address));
    }

    // Delete by ID
    public void deleteContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        contacts.remove(contactId);
    }

    // Update fields by ID
    public void updateFirstName(String contactId, String firstName) {
        Contact c = getExisting(contactId);
        c.setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        Contact c = getExisting(contactId);
        c.setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        Contact c = getExisting(contactId);
        c.setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        Contact c = getExisting(contactId);
        c.setAddress(address);
    }

    // For testing/debugging (read-only view)
    public Map<String, Contact> getContactsView() {
        return Collections.unmodifiableMap(contacts);
    }

    public Contact getContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }
        return contacts.get(contactId);
    }

    private Contact getExisting(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        return c;
    }
}
