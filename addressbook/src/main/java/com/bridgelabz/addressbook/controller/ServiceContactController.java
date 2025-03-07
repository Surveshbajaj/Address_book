package com.bridgelabz.addressbook.controller;


import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.Service.ContactService;
import com.bridgelabz.addressbook.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook/service")
public class ServiceContactController {

    private final ContactService contactService;

    // Constructor Injection
    public ServiceContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // GET - Fetch All Contacts
    @GetMapping("/contacts/get/all")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    // GET - Fetch Contact by ID
    @GetMapping("/contacts/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        return (contact != null) ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    // POST - Add New Contact
    @PostMapping("/contacts/add")
    public ResponseEntity<String> addContact(@RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(contactService.addContact(contactDTO));
    }

    // PUT - Update Contact
    @PutMapping("/contacts/update/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(contactService.updateContact(id, contactDTO));
    }

    // DELETE - Remove Contact
    @DeleteMapping("/contacts/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.deleteContact(id));
    }
}
