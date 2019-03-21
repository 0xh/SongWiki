package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.enterprise.inject.Produces;

public class GsonFactory {

    /**
     * Produces a Gson object that can be used with CDI
     * @return CDI Gson object
     */
    @Produces
    public Gson createGson(){
        return new GsonBuilder().create();
    }

}
