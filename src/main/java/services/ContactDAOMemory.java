package services;

import data.Contact;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;

@ApplicationScoped
public class ContactDAOMemory implements ContactDAO {
    @Inject
    DataStorage ds;

    @Override
    public Contact create(Contact contact) {
        return ds.createContact(contact);
    }

    @Override
    public Contact retrieve(String nif) {
        return ds.retrieveContact(nif);
    }

    @Override
    public Collection<Contact> retrieveAll() {
        return ds.getContacts();
    }

    @Override
    public Contact update(Contact contact) {
        return ds.updateContact(contact);
    }

    @Override
    public Contact delete(String nif) {
        return ds.deleteContact(nif);
    }
}
