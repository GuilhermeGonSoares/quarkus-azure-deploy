package azure.deploy.controllers;

import azure.deploy.entities.Todo;
import azure.deploy.repositories.TodoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoController {
    @Inject
    TodoRepository todoRepository;

    @GET
    public Response getTodos() {
        return Response.ok(todoRepository.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getTodoById(@PathParam("id") Long id) {
        return Response.ok(todoRepository.findById(id)).build();
    }

    @POST
    public Response create(CreateTodoRequest todo) {
        return Response.ok(todoRepository.save(todo.name()))
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UpdateTodoRequest todo) {
        return Response.ok(todoRepository.update(id, todo.name())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        todoRepository.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}/complete")
    public Response complete(@PathParam("id") Long id) {
        return Response.ok(todoRepository.complete(id)).build();
    }
}
