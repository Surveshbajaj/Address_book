package com.bridgelabz.addressbook.Service;

import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ContactService {
    private List<Contact> contacts = new ArrayList<>();

    // Get All Contacts
    public List<Contact> getAllContacts() {
        log.info("Fetching all contacts...");
        return contacts;
    }

    // Get Contact by ID
    public Contact getContactById(Long id) {
        log.info("Fetching contact with ID: {}", id);
        return contacts.stream().filter(contact -> contact.getId().equals(id)).findFirst().orElse(null);
    }

    // Add New Contact
    public String addContact(ContactDTO contactDTO) {
        Contact newContact = new Contact((long) (contacts.size() + 1), contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        contacts.add(newContact);
        log.info("Added new contact: {}", newContact);
        return "Contact added successfully";
    }

    // Update Contact
    public String updateContact(Long id, ContactDTO contactDTO) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                log.info("Updating contact with ID: {}", id);
                contact.setName(contactDTO.getName());
                contact.setEmail(contactDTO.getEmail());
                contact.setPhone(contactDTO.getPhone());
                log.info("Contact updated: {}", contact);
                return "Contact updated successfully!";
            }
        }
        log.error("Contact with ID {} not found!", id);
        return "Contact not found!";
    }

    // Delete Contact
    public String deleteContact(Long id) {
        boolean removed = contacts.removeIf(contact -> contact.getId().equals(id));
        if (removed) {
            log.info("Deleted contact with ID: {}", id);
            return "Contact deleted successfully!";
        } else {
            log.error("Failed to delete. Contact with ID {} not found!", id);
            return "Contact not found!";
        }
    }
}
