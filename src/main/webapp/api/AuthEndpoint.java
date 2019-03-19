package api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import controllers.AccountController;
import entities.Account;
import utils.JWTUtil;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
public class AuthEndpoint {

    @Inject
    private AccountController accountController;

    @Inject
    private JWTUtil jwtUtil;

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response getJWT(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            // Authenticate the user using the credentials provided
            Account account = accountController.find(username);

            String hashedPassword = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
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
