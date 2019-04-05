package utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;

import javax.enterprise.inject.Produces;

public class GoogleAuthenticatorFactory {
    /**
     * Produces a GoogleAuthenticator object that can be used with CDI
     * @return CDI GoogleAuthenticator object
     */
    @Produces
    public GoogleAuthenticator createGoogleAuthenticator(){
        return new GoogleAuthenticator();
    }
}
