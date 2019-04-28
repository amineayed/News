package Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_preferences", schema = "news", catalog = "")
@IdClass(UserPreferencesEntityPK.class)
public class UserPreferencesEntity {
    private int iduser;
    private String title;

    @Id
    @Column(name = "iduser")
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Id
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
        UserPreferencesEntity that = (UserPreferencesEntity) o;
        return iduser == that.iduser &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, title);
    }
}
