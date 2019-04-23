package webapp.api.data;

import entities.Playlist;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

public class PlaylistDataGenerator extends DataGenerator {

    @Override
    @Transactional
    public void generateTestData(@Observes @Initialized(ApplicationScoped.class) final Object event) {
        Playlist playlist = new Playlist();
        playlist.setName("test");
        playlist.setDescription("Test description");

        em.persist(playlist);
    }
}
