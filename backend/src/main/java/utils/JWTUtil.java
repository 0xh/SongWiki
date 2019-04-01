package utils;

import entities.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Date;

public class JWTUtil {

    @Context
    private UriInfo uriInfo;

    public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateJWT(Account account) {

        return Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20 * 60000))
                .claim("role", account.getRole())
                .signWith(SECRET_KEY)
                .compact();
    }

}
