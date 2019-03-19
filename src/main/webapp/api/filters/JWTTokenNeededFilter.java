package api.filters;

import entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import utils.JWTUtil;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if correct authorization header is provided
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        // Get the specified or default 'role' value on the JWTTokenNeeded annotation
        // of the calling method.
        // Source: https://stackoverflow.com/a/29201811
        Method resourceMethod = resourceInfo.getResourceMethod();
        JWTTokenNeeded classAnnotation = resourceMethod.getAnnotation(JWTTokenNeeded.class);

        try {
            // Validate the token
            Jws<Claims> claimsList = Jwts.parser()
                    .setSigningKey(JWTUtil.SECRET_KEY)
                    .parseClaimsJws(token);

            Claims claims = claimsList.getBody();
            String roleString = claims.get("role", String.class);
            Role role = Role.valueOf(roleString);

            if (role != classAnnotation.role())
                throw new JwtException(
                        "You do not have the role of "
                                + classAnnotation.role() +
                                "which is required for this call");
        } catch (JwtException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}