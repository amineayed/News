package entities;
// Generated 17 juin 2019 23:35:10 by Hibernate Tools 4.3.1



/**
 * Article generated by hbm2java
 */
public class Article  implements java.io.Serializable {


     private Integer idArticle;
     private Category category;
     private String title;
     private String link;
     private String description;
     private String image;
     private String date;
     private String source;

    public Article() {
    }

	
    public Article(String title, String link, String description, String image, String date, String source) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
        this.date = date;
        this.source = source;
    }
    public Article(Category category, String title, String link, String description, String image, String date, String source) {
       this.category = category;
       this.title = title;
       this.link = link;
       this.description = description;
       this.image = image;
       this.date = date;
       this.source = source;
    }

    
    public Integer getIdArticle() {
        return this.idArticle;
    }
    
    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
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


