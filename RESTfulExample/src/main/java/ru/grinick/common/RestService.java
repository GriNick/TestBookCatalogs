package ru.grinick.common;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/catalog")
@Produces("application/json")
public class RestService {
	private CatalogFactory catalogFactory = CatalogFactory.getInstance();

	@OPTIONS
	public Response options_headers() {
		List<Book> list = catalogFactory.getCatalog().getBooks(0);
	    return Response
	            .status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, cache-control")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
	            .build();
	}

	@GET
	public Response getFullCatalog() {
		List<Book> list = catalogFactory.getCatalog().getBooks(0);
	    return Response
	            .status(200)
	            .entity(list)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, cache-control")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
	            .build();
	}

	@OPTIONS
	@Path("/book")
	public Response options_headers1() {
		List<Book> list = catalogFactory.getCatalog().getBooks(0);
	    return Response
	            .status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, cache-control")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
	            .build();
	}

	@POST
	@Path("/book")
	@Consumes("application/json")
	public Response createBook(Book book) {
		UUID uuid;
		RestResponse response = new RestResponse();
		System.out.println("in createBook, trying to add the  book - " + book);
		if (book.getId() == null) {
			uuid = UUID.randomUUID();
			book.setId(uuid);
		} else uuid = book.getId();
		int added = catalogFactory.getCatalog().addBook(book);
		if (added == 1) {
			response.setSuccess(1);
			response.setMessage("New book has been successfully added");
			response.setUuid(uuid.toString());
		} else  {
			response.setSuccess(0);
			response.setMessage("Cannot add new book");
		}
		return Response.status(201).entity(response)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, cache-control")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
		.build();
	}
	@PUT
	@Path("/book")
	@Consumes("application/json")
	public Response updateBook(Book book) {
		RestResponse response = new RestResponse();
		int result = catalogFactory.getCatalog().updateBook(book);
		if (result == 1) {
			response.setSuccess(1);
			response.setMessage("The book has been successfully updated");
		} else {
			response.setSuccess(0);
			response.setMessage("Cannot update the book");
		}
		return Response.status(200).entity(response)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, cache-control")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
		.build();
	}

	@DELETE
	@Path("/book/{id}")
	public Response deleteBook(@PathParam("id") String uId) {
		UUID id;
		RestResponse response = new RestResponse();

		try {
			id = UUID.fromString(uId);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(0);
			response.setMessage("Cannot delete the book");
			return Response.status(404).entity(response).build();
		}
		int result = catalogFactory.getCatalog().deleteBook(id);
		if (result == 1) {
			response.setSuccess(1);
			response.setMessage("The book has been successfully deleted");
		} else {
			response.setSuccess(0);
			response.setMessage("Cannot delete the book");
		}
		return Response.status(200).entity(response)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, cache-control")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
		.build();
	}


}
