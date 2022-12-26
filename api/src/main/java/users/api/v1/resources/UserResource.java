package users.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.persistence.sessions.Login;
import users.lib.UserDetails;
import users.payload.LoginPayload;
import users.payload.RegisterPayload;
import users.services.beans.UsersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private Logger log = Logger.getLogger(UserResource.class.getName());

    @Inject
    private UsersBean usersBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get all users data.", summary = "Get all users")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of users data",
                    content = @Content(schema = @Schema(implementation = UserDetails.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    public Response getImageMetadata() {

        List<UserDetails> usersData = usersBean.getAllUsers();

        return Response.status(Response.Status.OK).entity(usersData).build();
    }

    @Operation(description = "Register a new user", summary = "Register user")
    @Path("register")
    @POST
    public Response registerUser(@RequestBody(
            description = "Object with segment data.",
            required = true, content = @Content(
            schema = @Schema(implementation = RegisterPayload.class))) RegisterPayload registerPayload) {

        try{
            usersBean.registerUser(registerPayload);
        }
        catch (Exception e){
            System.out.println(e);

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @Operation(description = "Login with user credentials", summary = "Login user")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "User data",
                    content = @Content(schema = @Schema(implementation = UserDetails.class, type = SchemaType.OBJECT))
            )})
    @Path("login")
    @POST
    public Response loginUser(@RequestBody(
            description = "Object with segment data.",
            required = true, content = @Content(
            schema = @Schema(implementation = LoginPayload.class))) LoginPayload loginPayload) {


        UserDetails userDetails = usersBean.loginUser(loginPayload);

        if(userDetails != null)
            return Response.status(Response.Status.OK).entity(userDetails).build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
