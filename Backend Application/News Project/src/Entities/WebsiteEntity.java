package Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "website", schema = "news", catalog = "")
public class WebsiteEntity {
    private int idWebSite;
    private String title;
    private String link;

    @Id
    @Column(name = "id_WebSite")
    public int getIdWebSite() {
        return idWebSite;
    }

    public void setIdWebSite(int idWebSite) {
        this.idWebSite = idWebSite;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebsiteEntity that = (WebsiteEntity) o;
        return idWebSite == that.idWebSite &&
                Objects.equals(title, that.title) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWebSite, title, link);
    }
}
