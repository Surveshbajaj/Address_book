package com.bridgelabz.addressbook.Service;

import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private List<Contact> contacts = new ArrayList<>();

    // Get All Contacts
    public List<Contact> getAllContacts() {
        return contacts;
    }

    // Get Contact by ID
    public Contact getContactById(Long id) {
        return contacts.stream().filter(contact -> contact.getId().equals(id)).findFirst().orElse(null);
    }

    // Add New Contact
    public String addContact(ContactDTO contactDTO) {
        Contact newContact = new Contact((long) (contacts.size() + 1), contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        contacts.add(newContact);
        return "Contact added successfully!";
    }

    // Update Contact
    public String updateContact(Long id, ContactDTO contactDTO) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setName(contactDTO.getName());
                contact.setEmail(contactDTO.getEmail());
                contact.setPhone(contactDTO.getPhone());
                return "Contact updated successfully!";
            }
        }
        return "Contact not found!";
    }

    // Delete Contact
    public String deleteContact(Long id) {
        boolean removed = contacts.removeIf(contact -> contact.getId().equals(id));
        return removed ? "Contact deleted successfully!" : "Contact not found!";
    }
}
