package webapp.api.data;

import entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

public class SongDataGenerator extends DataGenerator {

    @Override
    @Transactional
    public void generateTestData(@Observes @Initialized(ApplicationScoped.class) final Object event) {
        Song song = new Song();
        song.setName("test");
        song.setPublishedAt(System.currentTimeMillis());
        song.setResource("https://www.youtube.com/watch?v=TKmGU77INaM");

        em.persist(song);
    }

}
