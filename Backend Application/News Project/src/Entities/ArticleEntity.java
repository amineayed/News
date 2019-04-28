package Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "article", schema = "news", catalog = "")
public class ArticleEntity {
    private int idArticle;
    private String link;
    private String description;
    private String image;
    private String source;
    private String date;
    private String title;

    @Id
    @Column(name = "id_Article")
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return idArticle == that.idArticle &&
                Objects.equals(link, that.link) &&
                Objects.equals(description, that.description) &&
                Objects.equals(image, that.image) &&
                Objects.equals(source, that.source) &&
                Objects.equals(date, that.date) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, link, description, image, source, date, title);
    }
}
