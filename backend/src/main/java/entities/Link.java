package entities;

public class Link {
    private String link;
    private String rel;

    public String getLink() {
        return link;
    }
    public String getRel() {
        return rel;
    }

    public Link(String link, String rel) {
        this.link = link;
        this.rel = rel;
    }

    public Link() {}
}
