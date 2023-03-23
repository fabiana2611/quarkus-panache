package climate.social.controller;

import climate.social.domain.users.User;
import climate.social.domain.users.UserRepository;
import climate.social.controller.dto.CreateUserRequest;
import io.quarkus.arc.log.LoggerName;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import io.quarkus.logging.Log;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    // Jboss
    private static final Logger logJboss = Logger.getLogger(UserResource.class);

    // Use the class as name as used for logJboss
    @Inject
    Logger logInjected;

    // Give the name
    @LoggerName("logName")
    Logger logInjectedWithName;
    @Inject
    private UserRepository repository;
    private Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {

        logJboss.info("This is a log from jboss");
        Log.info("This is a log from Panache");
        logInjected.info("This is a log injected");
        logInjectedWithName.info("This is a log injected with name");

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);

        if(!violations.isEmpty()) {
            Map<String, String> erros = new HashMap<>();
             violations.stream()
                    .map(cv -> erros.put(cv.getPropertyPath().toString(), cv.getMessage()));

            return Response.status(422).entity(erros).build();
        }

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        repository.persist(user);

        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser( @PathParam("id") Long id){
        repository.deleteById(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser( @PathParam("id") Long id, CreateUserRequest userData ){
        User user = repository.findById(id);

        if(user != null){
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            // It's not necessary to call update because it is Transactional
            // It means that any entity used inside this context and is changed it will be commited when finish the method
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
