package data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Contact {
    String name;
    String surname;
    String nif;

    @JsonIgnore
    public static final Contact NOT_FOUND = new Contact("Not found", "", "");

    public Contact() {
        super();
    }

    public Contact(String name, String surname, String nif) {
        this.name = name;
        this.surname = surname;
        this.nif = nif;
    }

    public String getNIF() {
        return nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(surname, contact.surname)
                && nif.equals(contact.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, nif);
    }
}
