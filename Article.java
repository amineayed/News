public class Article
{
    private String title;
    private String description;
    private String source;
    private String link;
    private String image;
    private String date;

    public Article(String title, String description, String source, String link, String image, String date)
    {
        this.title = title;
        this.description = description;
        this.source = source;
        this.link = link;
        this.image = image;
        this.date = date;
    }

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

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String toString()
    {

        return "Title: " + this.getTitle() + "\n" +
                "Description: " + this.getDescription() + "\n" +
                "Source: " + this.getSource() +
                "Link: " + this.getLink() + "\n" +
                "Image: " + this.getImage() + "\n" +
                "Date: " + this.getDate() + "\n";
    }

}
