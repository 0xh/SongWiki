package api;

import controllers.AccountController;
import entities.Account;
import entities.JWTGenerationRequest;
import utils.JWTUtil;
import utils.PasswordHasher;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
public class AuthEndpoint {

    @Inject
    private AccountController accountController;

    @Inject
    private JWTUtil jwtUtil;

    @Inject
    private PasswordHasher passwordHasher;

    @POST
    public Response getJWT(JWTGenerationRequest generationRequest) {
        try {
            // Authenticate the user using the credentials provided
            Account account = accountController.find(generationRequest.getUsername());

            String hashedPassword = passwordHasher.hash(generationRequest.getPassword());
            if (!hashedPassword.equals(account.getPassword()))
                return Response.status(UNAUTHORIZED).build();

            // Issue a token for the user
            String token = jwtUtil.generateJWT(account);

            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }
}
