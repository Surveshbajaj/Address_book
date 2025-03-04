package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.model.Contact;
import com.bridgelabz.addressbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
public class AddressBookController{

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    AddressBookController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    // GET all contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactRepository.findAll());
    }

    // GET contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new contact
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    // PUT to update contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(updatedContact.getName());
                    contact.setAddress(updatedContact.getAddress());
                    return ResponseEntity.ok(contactRepository.save(contact));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
