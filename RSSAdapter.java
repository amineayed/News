import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class RSSAdapter
{

    public static ArrayList<Article> retrieveAllNews() throws IOException, SAXException, ParserConfigurationException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(retrieveWorldNews());
        articles.addAll(retrieveTechNews());
        articles.addAll(retrieveScienceNews());
        articles.addAll(retrieveSportNews());
        articles.addAll(retrieveMiddleEastNews());
        articles.addAll(retrieveAfricaNews());
        articles.addAll(retrieveEuropeNews());
        articles.addAll(retrieveAmericasNews());
        articles.addAll(retrieveAsiaNews());
        articles.addAll(retrieveTopStoriesNews());
        articles.addAll(retrieveBusinessNews());
        articles.addAll(retrieveEntertainmentNews());

        purgeDuplicates(articles);

        return articles;
    }

    //Retrieve World News RSS feeds from all sources
    public static ArrayList<Article> retrieveWorldNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/rss.xml", "world"));
        articles.addAll(f24Adapter("https://www.france24.com/en/rss", "world"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_world.rss", "world"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveTechNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/technology/rss.xml", "technology"));
        articles.addAll(wiredAdapter("https://www.wired.com/feed/category/gear/latest/rss", "technology"));
        articles.addAll(f24Adapter("https://www.france24.com/en/business-tech/rss", "technology"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_technology.rss", "technology"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveScienceNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>(BBCAdapter("http://feeds.bbci.co.uk/news/science_and_environment/rss.xml", "science"));
        articles.addAll(wiredAdapter("https://www.wired.com/feed/category/science/latest/rss", "science"));


        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveSportNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(f24Adapter("https://www.france24.com/en/sport/rss", "sport"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_sport.rss", "sport"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_football.rss", "sport"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_golf.rss", "sport"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_motorsport.rss", "sport"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_tennis.rss", "sport"));
        articles.addAll(ESPNAdapter("http://www.espn.com/espn/rss/", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=mlb", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=nfl", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=cfb", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=nba", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=nhl", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=cbk", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=nascar", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=ufc", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=motor", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=golf", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=soccer", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=olympics", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=tennis", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=horse-racing", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=wnba", "sport"));
        articles.addAll(foxAdapter("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=wcbk", "sport"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveMiddleEastNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/middle_east/rss.xml", "middle_east"));
        articles.addAll(f24Adapter("https://www.france24.com/en/middle-east/rss", "middle_east"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_meast.rss", "middle_east"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveAfricaNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/africa/rss.xml", "africa"));
        articles.addAll(f24Adapter("https://www.france24.com/en/africa/rss", "africa"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_africa.rss", "africa"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveEuropeNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/europe/rss.xml", "europe"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/england/rss.xml", "europe"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/northern_ireland/rss.xml", "europe"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/scotland/rss.xml", "europe"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/england/rss.xml", "europe"));
        articles.addAll(f24Adapter("https://www.france24.com/en/europe/rss", "europe"));
        articles.addAll(f24Adapter("https://www.france24.com/en/france/rss", "europe"));
        articles.addAll(CNNAdapter("http://feeds.bbci.co.uk/news/wales/rss.xml", "europe"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveAmericasNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml", "americas"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/latin_america/rss.xml", "americas"));
        articles.addAll(f24Adapter("https://www.france24.com/en/americas/rss", "americas"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_us.rss", "americas"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_americas.rss", "americas"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveAsiaNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/asia/rss.xml", "asia"));
        articles.addAll(f24Adapter("https://www.france24.com/en/asia-pacific/rss", "asia"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_asia.rss", "asia"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveTopStoriesNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/rss.xml", "top_stories"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/video_and_audio/news_front_page/rss.xml?edition=uk", "top_stories"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition.rss", "top_stories"));
        articles.addAll(wiredAdapter("https://www.wired.com/feed/rss", "top_stories"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveBusinessNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/business/rss.xml", "business"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/video_and_audio/business/rss.xml", "business"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/money_news_international.rss", "business"));
        articles.addAll(wiredAdapter("https://www.wired.com/feed/category/business/latest/rss", "business"));

        purgeDuplicates(articles);

        return articles;
    }

    public static ArrayList<Article> retrieveEntertainmentNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml", "entertainment"));
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/video_and_audio/entertainment_and_arts/rss.xml", "entertainment"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_entertainment.rss", "entertainment"));

        purgeDuplicates(articles);

        return articles;
    }

    //Produce an ArrayList<Article> from a given BBC RSS feed URL
    public static ArrayList<Article> BBCAdapter(String rssLink, String category) throws IOException, ParserConfigurationException, SAXException
    {
        ArrayList<Article> articles = new ArrayList<>();

        //Build XML Document
        String xml = (readFromWeb(rssLink));
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));
        Document doc = builder.parse(src);

        //Extract resources into NodeLists and convert to ArrayLists
        NodeList titleNodeList = doc.getElementsByTagName("title");
        ArrayList<String> titleList = nodeToArray(titleNodeList);
        titleList.remove(0);
        titleList.remove(0);

        NodeList descNodeList = doc.getElementsByTagName("description");
        ArrayList<String> descList = nodeToArray(descNodeList);
        descList.remove(0);

        NodeList dateNodeList = doc.getElementsByTagName("pubDate");
        ArrayList<String> dateList = nodeToArray(dateNodeList);

        NodeList linkNodeList = doc.getElementsByTagName("link");
        ArrayList<String> linkList = nodeToArray(linkNodeList);
        linkList.remove(0);
        linkList.remove(0);

        ArrayList<String> imageList = getBBCImageURLs(xml);

        for(int i = 0; i < dateList.size(); i++)
        {
            articles.add(new Article(
                    titleList.get(i).hashCode(),
                    new Category(category),
                    titleList.get(i),
                    descList.get(i),
                    "BBC",
                    linkList.get(i),
                    imageList.get(i),
                    dateList.get(i)
            ));
        }

        return articles;
    }
    //Produce an ArrayList<Article> from a given f24 RSS feed URL
    public static ArrayList<Article> f24Adapter(String rssLink, String category) throws IOException, ParserConfigurationException, SAXException
    {
        ArrayList<Article> articles = new ArrayList<>();

        //Build XML Document
        String xml = (readFromWeb(rssLink));
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));
        Document doc = builder.parse(src);

        //Extract resources into NodeLists and convert to ArrayLists
        NodeList titleNodeList = doc.getElementsByTagName("title");
        ArrayList<String> titleList = nodeToArray(titleNodeList);

        NodeList descNodeList = doc.getElementsByTagName("description");
        ArrayList<String> descList = nodeToArray(descNodeList);

        NodeList dateNodeList = doc.getElementsByTagName("pubDate");
        ArrayList<String> dateList = nodeToArray(dateNodeList);

        NodeList linkNodeList = doc.getElementsByTagName("link");
        ArrayList<String> linkList = nodeToArray(linkNodeList);

        ArrayList<String> imageList = getF24ImageURLs(xml);


        for(int i = 0; i < dateList.size(); i++)
        {
            articles.add(new Article(
                    titleList.get(i).hashCode(),
                    new Category(category),
                    titleList.get(i),
                    descList.get(i),
                    "France 24",
                    linkList.get(i),
                    imageList.get(i),
                    dateList.get(i)
            ));
        }

        return articles;
    }
    //Produce an ArrayList<Article> from a given CNN RSS feed URL
    public static ArrayList<Article> CNNAdapter(String rssLink, String category) throws IOException, ParserConfigurationException, SAXException
    {
        ArrayList<Article> articles = new ArrayList<>();

        ArrayList<Integer> articleIds = new ArrayList<>();

        //Build XML Document
        String xml = (readFromWeb(rssLink));
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));
        Document doc = builder.parse(src);


        //Extract resources into NodeLists and convert to ArrayLists
        NodeList titleNodeList = doc.getElementsByTagName("title");
        ArrayList<String> titleList = nodeToArray(titleNodeList);
        titleList.remove(0);
        titleList.remove(0);

        NodeList descNodeList = doc.getElementsByTagName("description");
        ArrayList<String> descList = nodeToArray(descNodeList);
        descList.remove(0);

        NodeList dateNodeList = doc.getElementsByTagName("pubDate");
        ArrayList<String> dateList = nodeToArray(dateNodeList);
        dateList.remove(0);

        NodeList linkNodeList = doc.getElementsByTagName("link");
        ArrayList<String> linkList = nodeToArray(linkNodeList);
        linkList.remove(0);
        linkList.remove(0);


        String docText = docToString(doc);
        ArrayList<String> imageList = getCNNImageURLs(docText);

        ArrayList<String> itemList = getFullElementsByTag(docText, "item");

        NodeList itemNodeList = doc.getElementsByTagName("item");
        HashMap<Integer, String[]> articleMap = new HashMap<>();

        int descErrorIndex;

        int nextKey = 0;
        for(int i = 0; i < itemNodeList.getLength(); i++)
        {
            //Full, title, description, link, image, date
            articleMap.put(nextKey++, new String[]{itemList.get(i), "NO_TITLE", "NO_DESCRIPTION", "NO_LINK", "NO_IMAGE", "NO_DATE"});
            for (String s : titleList)
            {
                if (s.hashCode() == 0)
                    continue;
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[1] = s;
                    break;
                }
            }
            for (String s : descList)
            {
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[2] = s;
                    break;
                }
            }
            for (String s : linkList)
            {
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[3] = s;
                }
            }
            for (String s : imageList)
            {
                if (itemList.get(i).contains(s))
                {
                    articleMap.get(i)[4] = s;
                }
            }
            for (String s : dateList)
            {
                if (s.hashCode() == 0)
                    continue;
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[5] = s;
                }
            }
        }

        for(int i = 0; i < articleMap.size(); i++)
        {
            //Check for whitespace title
            if(articleMap.get(i)[1].hashCode() == 0)
                continue;
            //Check for duplicates
            if(articleIds.contains(articleMap.get(i)[1].hashCode()))
                continue;
            //Check for malformed description
            articleIds.add(articleMap.get(i)[1].hashCode());
            descErrorIndex = articleMap.get(i)[2].indexOf("<img src");
            if(descErrorIndex == 0)
                articleMap.get(i)[2] = "NO_DESCRIPTION";
            else if(descErrorIndex > 0)
                articleMap.get(i)[2] = articleMap.get(i)[2].substring(0, descErrorIndex);
            //Add article
            articles.add(new Article(
                    articleMap.get(i)[1].hashCode(),
                    new Category(category),
                    articleMap.get(i)[1],
                    articleMap.get(i)[2],
                    "CNN",
                    articleMap.get(i)[3],
                    articleMap.get(i)[4],
                    articleMap.get(i)[5]
            ));
        }

        return articles;
    }
    public static ArrayList<Article> wiredAdapter(String rssLink, String category) throws IOException, ParserConfigurationException, SAXException
    {
        ArrayList<Article> articles = new ArrayList<>();

        //Build XML Document
        String xml = (readFromWeb(rssLink));
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));
        Document doc = builder.parse(src);

        //Extract resources into NodeLists and convert to ArrayLists
        NodeList titleNodeList = doc.getElementsByTagName("title");
        ArrayList<String> titleList = nodeToArray(titleNodeList);
        titleList.remove(0);

        NodeList descNodeList = doc.getElementsByTagName("description");
        ArrayList<String> descList = nodeToArray(descNodeList);
        descList.remove(0);

        NodeList dateNodeList = doc.getElementsByTagName("pubDate");
        ArrayList<String> dateList = nodeToArray(dateNodeList);

        NodeList linkNodeList = doc.getElementsByTagName("link");
        ArrayList<String> linkList = nodeToArray(linkNodeList);
        linkList.remove(0);

        ArrayList<String> imageList = getBBCImageURLs(xml);

        for(int i = 0; i < dateList.size(); i++)
        {
            articles.add(new Article(
                    titleList.get(i).hashCode(),
                    new Category(category),
                    titleList.get(i),
                    descList.get(i),
                    "Wired",
                    linkList.get(i),
                    imageList.get(i),
                    dateList.get(i)
            ));
        }

        return articles;
    }
    public static ArrayList<Article> ESPNAdapter(String rssLink, String category) throws IOException, ParserConfigurationException, SAXException
    {
        ArrayList<Article> articles = new ArrayList<>();

        ArrayList<Integer> articleIds = new ArrayList<>();

        //Build XML Document
        String xml = (readFromWeb(rssLink));
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));
        Document doc = builder.parse(src);


        //Extract resources into NodeLists and convert to ArrayLists
        NodeList titleNodeList = doc.getElementsByTagName("title");
        ArrayList<String> titleList = nodeToArray(titleNodeList);
        titleList.remove(0);

        NodeList descNodeList = doc.getElementsByTagName("description");
        ArrayList<String> descList = nodeToArray(descNodeList);
        descList.remove(0);

        NodeList dateNodeList = doc.getElementsByTagName("pubDate");
        ArrayList<String> dateList = nodeToArray(dateNodeList);

        NodeList linkNodeList = doc.getElementsByTagName("link");
        ArrayList<String> linkList = nodeToArray(linkNodeList);
        linkList.remove(0);
        linkList.remove(0);

        NodeList imageNodeList = doc.getElementsByTagName("image");
        ArrayList<String> imageList = nodeToArray(imageNodeList);



        String docText = docToString(doc);

        ArrayList<String> itemList = getFullElementsByTag(docText, "item");

        NodeList itemNodeList = doc.getElementsByTagName("item");
        HashMap<Integer, String[]> articleMap = new HashMap<>();

        int descErrorIndex;

        int nextKey = 0;
        for(int i = 0; i < itemNodeList.getLength(); i++)
        {
            //Full, title, description, link, image, date
            articleMap.put(nextKey++, new String[]{itemList.get(i), "NO_TITLE", "NO_DESCRIPTION", "NO_LINK", "NO_IMAGE", "NO_DATE"});
            for (String s : titleList)
            {
                if (s.hashCode() == 0)
                    continue;
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[1] = s;
                    break;
                }
            }
            for (String s : descList)
            {
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[2] = s;
                    break;
                }
            }
            for (String s : linkList)
            {
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[3] = s;
                }
            }
            for (String s : imageList)
            {
                if (itemList.get(i).contains(s))
                {
                    articleMap.get(i)[4] = s;
                }
            }
            for (String s : dateList)
            {
                if (s.hashCode() == 0)
                    continue;
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[5] = s;
                }
            }
        }

        for(int i = 0; i < articleMap.size(); i++)
        {
            //Check for whitespace title
            if(articleMap.get(i)[1].hashCode() == 0)
                continue;
            //Check for duplicates
            if(articleIds.contains(articleMap.get(i)[1].hashCode()))
                continue;
            //Check for malformed description
            articleIds.add(articleMap.get(i)[1].hashCode());
            /*descErrorIndex = articleMap.get(i)[2].indexOf("<img src");
            if(descErrorIndex == 0)
                articleMap.get(i)[2] = "NO_DESCRIPTION";
            else if(descErrorIndex > 0)
                articleMap.get(i)[2] = articleMap.get(i)[2].substring(0, descErrorIndex);*/
            //Add article
            articles.add(new Article(
                    articleMap.get(i)[1].hashCode(),
                    new Category(category),
                    articleMap.get(i)[1],
                    articleMap.get(i)[2],
                    "ESPN",
                    articleMap.get(i)[3],
                    articleMap.get(i)[4],
                    articleMap.get(i)[5]
            ));
        }

        return articles;
    }
    public static ArrayList<Article> foxAdapter(String rssLink, String category) throws IOException, ParserConfigurationException, SAXException
    {
        ArrayList<Article> articles = new ArrayList<>();

        ArrayList<Integer> articleIds = new ArrayList<>();

        //Build XML Document
        String xml = (readFromWeb(rssLink));
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));
        Document doc = builder.parse(src);


        //Extract resources into NodeLists and convert to ArrayLists
        NodeList titleNodeList = doc.getElementsByTagName("title");
        ArrayList<String> titleList = nodeToArray(titleNodeList);
        titleList.remove(0);

        NodeList descNodeList = doc.getElementsByTagName("description");
        ArrayList<String> descList = nodeToArray(descNodeList);
        descList.remove(0);

        NodeList dateNodeList = doc.getElementsByTagName("pubDate");
        ArrayList<String> dateList = nodeToArray(dateNodeList);

        NodeList linkNodeList = doc.getElementsByTagName("link");
        ArrayList<String> linkList = nodeToArray(linkNodeList);
        linkList.remove(0);
        linkList.remove(0);


        String docText = docToString(doc);

        ArrayList<String> imageList = getBBCImageURLs(docText);

        ArrayList<String> itemList = getFullElementsByTag(docText, "item");

        NodeList itemNodeList = doc.getElementsByTagName("item");
        HashMap<Integer, String[]> articleMap = new HashMap<>();

        int descErrorIndex;

        int nextKey = 0;
        for(int i = 0; i < itemNodeList.getLength(); i++)
        {
            //Full, title, description, link, image, date
            articleMap.put(nextKey++, new String[]{itemList.get(i), "NO_TITLE", "NO_DESCRIPTION", "NO_LINK", "NO_IMAGE", "NO_DATE"});
            for (String s : titleList)
            {
                if (s.hashCode() == 0)
                    continue;
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[1] = s;
                    break;
                }
            }
            for (String s : descList)
            {
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[2] = s;
                    break;
                }
            }
            for (String s : linkList)
            {
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[3] = s;
                }
            }
            for (String s : imageList)
            {
                if (itemList.get(i).contains(s))
                {
                    articleMap.get(i)[4] = s;
                }
            }
            for (String s : dateList)
            {
                if (s.hashCode() == 0)
                    continue;
                if (itemNodeList.item(i).getTextContent().contains(s))
                {
                    articleMap.get(i)[5] = s;
                }
            }
        }

        for(int i = 0; i < articleMap.size(); i++)
        {
            //Check for whitespace title
            if(articleMap.get(i)[1].hashCode() == 0)
                continue;
            //Check for duplicates
            if(articleIds.contains(articleMap.get(i)[1].hashCode()))
                continue;
            //Check for malformed description
            articleIds.add(articleMap.get(i)[1].hashCode());
            /*descErrorIndex = articleMap.get(i)[2].indexOf("<img src");
            if(descErrorIndex == 0)
                articleMap.get(i)[2] = "NO_DESCRIPTION";
            else if(descErrorIndex > 0)
                articleMap.get(i)[2] = articleMap.get(i)[2].substring(0, descErrorIndex);*/
            //Add article
            articles.add(new Article(
                    articleMap.get(i)[1].hashCode(),
                    new Category(category),
                    articleMap.get(i)[1].trim(),
                    articleMap.get(i)[2].trim(),
                    "Fox Sports",
                    articleMap.get(i)[3].trim(),
                    articleMap.get(i)[4].trim(),
                    articleMap.get(i)[5].trim()
            ));
        }

        return articles;
    }

    //Used to extract the image URLs from BBC feeds, also works for Wired
    private static ArrayList<String> getBBCImageURLs(String doc)
    {
        StringBuilder imageUrl;
        char nextChar;
        int charCount = 0;
        ArrayList<String> imageUrls = new ArrayList<>();
        charCount = doc.indexOf("url=\"", charCount);
        while(charCount != -1)
        {
            imageUrl = new StringBuilder();
            charCount += 5;
            nextChar = doc.charAt(charCount);
            while(nextChar != '\"')
            {
                imageUrl.append(nextChar);
                nextChar = doc.charAt(++charCount);
            }
            imageUrls.add(imageUrl.toString());
            charCount = doc.indexOf("url=\"", charCount);

        }
        return imageUrls;
    }
    //Used to extract the image URLs from f24 feeds
    private static ArrayList<String> getF24ImageURLs(String doc)
    {
        StringBuilder imageUrl;
        char nextChar;
        int charCount = 0;
        ArrayList<String> imageUrls = new ArrayList<>();
        charCount = doc.indexOf("/\" url=\"", charCount);
        while(charCount != -1)
        {
            imageUrl = new StringBuilder();
            charCount += 8;
            nextChar = doc.charAt(charCount);
            while(nextChar != '\"')
            {
                imageUrl.append(nextChar);
                nextChar = doc.charAt(++charCount);
            }
            imageUrls.add(imageUrl.toString());
            charCount = doc.indexOf("/\" url=\"", charCount);

        }
        return imageUrls;
    }
    //Used to extract the image URLs from CNN feeds
    private static ArrayList<String> getCNNImageURLs(String doc)
    {
        doc = doc.replace(" ", "");
        doc = doc.replace("\n", "");
        StringBuilder imageUrl;
        char nextChar;
        int charCount = 0;
        ArrayList<String> imageUrls = new ArrayList<>();
        charCount = doc.indexOf("<media:group>", charCount);

        while(charCount != -1)
        {
            imageUrl = new StringBuilder();
            charCount += 59;
            nextChar = doc.charAt(charCount);
            while(nextChar != '\"')
            {
                imageUrl.append(nextChar);
                nextChar = doc.charAt(++charCount);
            }
            imageUrls.add(imageUrl.toString());
            charCount = doc.indexOf("<media:group>", charCount);

        }
        return imageUrls;
    }

    //Convert a NodeList into an ArrayList
    private static ArrayList<String> nodeToArray(NodeList nodeList)
    {
        ArrayList<String> outList = new ArrayList<>();
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            outList.add(nodeList.item(i).getTextContent());
        }
        return outList;
    }
    //Get all of the text from between two XML tags
    private static ArrayList<String> getFullElementsByTag(String doc, String tag)
    {
        ArrayList<String> elementList = new ArrayList<>();
        StringBuilder element;
        char nextChar;
        int charCount = 0;
        charCount = doc.indexOf("<" + tag + ">", charCount);
        while(charCount != -1)
        {
            element = new StringBuilder();
            charCount += 2 + tag.length();
            nextChar = doc.charAt(charCount);
            while(!doc.substring(charCount, charCount + 3 + tag.length()).contains("</" + tag + ">"))
            {
                element.append(nextChar);
                nextChar = doc.charAt(++charCount);
            }
            elementList.add(element.toString());
            charCount = doc.indexOf("<" + tag + ">", charCount);

        }
        return elementList;
    }

    public static void purgeDuplicates(ArrayList<Article> articles)
    {
        ArrayList<Integer> articleIds = new ArrayList<>();
        for(int i = 0; i < articles.size(); i++)
        {
            if(articleIds.contains(articles.get(i).getIdArticle()))
                articles.remove(i--);
            else
                articleIds.add(articles.get(i).getIdArticle());
        }
    }

    //Forum code
    //Extract HTML from URL and convert to String
    private static String readFromWeb(String webURL) throws IOException
    {
        URL url = new URL(webURL);
        StringBuilder output = new StringBuilder();
        InputStream is =  url.openStream();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException("URL is malformed!!");
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        return output.toString();
    }
    //Convert a Document to a String
    private static String docToString(Document doc)
    {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }
}
