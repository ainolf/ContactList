package services;

import data.Contact;

import java.util.Collection;

public interface ContactDAO {
    Contact create(Contact contact);
    Contact retrieve(String nif);
    Collection<Contact> retrieveAll();
    Contact update(Contact contact);
    Contact delete(String nif);
}
