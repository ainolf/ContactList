package services;

import data.Contact;

import java.util.Collection;

public interface DataStorage {
    Contact createContact(Contact contact);
    Contact retrieveContact(String nif);
    Contact updateContact(Contact contact);
    Contact deleteContact(String nif);
    Collection<Contact> getContacts();
}
