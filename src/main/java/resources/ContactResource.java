package resources;

import data.Contact;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.ContactDAO;

import java.net.URI;
import java.net.URISyntaxException;


@Path("/contacts")
public class ContactResource {
    // @Inject
    // InMemoryDataStorage ds;
    @Inject
    ContactDAO ds;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData() {
        return Response.ok(ds.retrieveAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/retrieve/{nif}")
    public Response getContact(@PathParam("nif") final String nif) {
        if (ds.retrieve(nif) == Contact.NOT_FOUND)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(ds.retrieve(nif)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createContact(Contact contact) throws URISyntaxException {
        Contact response = ds.create(contact);
        if (response == Contact.NOT_FOUND)
            return Response.status(Response.Status.CONFLICT).build();
        URI uri = new URI("/contacts/" + contact.getNIF());
        return Response.created(uri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateContact(final Contact contact) throws URISyntaxException {
        Contact result = ds.update(contact);
        if (result == Contact.NOT_FOUND)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/delete/{nif}")
    public Response deleteContact(@PathParam("nif") final String nif) {
        Contact result = ds.delete(nif);
        if (result == Contact.NOT_FOUND)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}
