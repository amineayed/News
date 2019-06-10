package entities;
public class Article
{
    private Integer idArticle;
    private Category category;
    private String title;
    private String description;
    private String source;
    private String link;
    private String image;
    private String date;

    public Article(Integer idArticle, Category category, String title, String description, String source, String link, String image, String date)
    {
        this.idArticle = idArticle;
        this.category = category;
        this.title = title;
        this.description = description;
        this.source = source;
        this.link = link;
        this.image = image;
        this.date = date;
    }

    public Integer getIdArticle()
    {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle)
    {
        this.idArticle = idArticle;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getTitle()
    {
        return title.replace("\"", "'");
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description.replace("\"", "'");
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

    public Article() {
    }

    @Override
    public String toString()
    {

        return "{\"ID\" :  " + this.getIdArticle() + "," +
               
                "\"Title\" : \"" + this.getTitle() + "\"," +
                "\"Link\" : \"" + this.getLink() + "\"," +
                "\"Description\" : \"" + this.getDescription() + "\"," +
                 "\"Image\" : \"" + this.getImage() + "\"," +
                "\"Date\" : \"" + this.getDate() + "\","+
                "\"Source\" : \"" + this.getSource() + "\"," +
                 "\"Category \" : { \"title\": \"" + this.getCategory().getTitle() + "\"}}" ;
    }

}