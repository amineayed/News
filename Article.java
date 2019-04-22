import java.net.URL;

public class Article
{
    private String title;
    private String description;
    private String source;
    private URL link;
    private URL image;
    private long date;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public URL getLink()
    {
        return link;
    }

    public void setLink(URL link)
    {
        this.link = link;
    }

    public URL getImage()
    {
        return image;
    }

    public void setImage(URL image)
    {
        this.image = image;
    }

    public long getDate()
    {
        return date;
    }

    public void setDate(long date)
    {
        this.date = date;
    }
}
