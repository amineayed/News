package Adapter;

import entities.*;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class RSSAdapter
{

    //Retrieve World News RSS feeds from all sources
    public static ArrayList<Article> retrieveWorldNews() throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Article> articles = new ArrayList<>();
        articles.addAll(BBCAdapter("http://feeds.bbci.co.uk/news/world/rss.xml"));
        articles.addAll(f24Adapter("https://www.france24.com/en/rss"));
        articles.addAll(CNNAdapter("http://rss.cnn.com/rss/edition_world.rss"));
        return articles;
    }

    //Produce an ArrayList<Article> from a given BBC RSS feed URL
    public static ArrayList<Article> BBCAdapter(String rssLink) throws IOException, ParserConfigurationException, SAXException
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

        ArrayList<String> imageList = getBBCImageURLs(docToString(doc));
        Article ArticleEntityObject=new Article();
        for(int i = 0; i < dateList.size(); i++)
        { ArticleEntityObject.setTitle(titleList.get(i));
            ArticleEntityObject.setDescription(descList.get(i));
            ArticleEntityObject.setSource("BBC");
            ArticleEntityObject.setLink(linkList.get(i));
            ArticleEntityObject.setImage(imageList.get(i));
            ArticleEntityObject.setDate(dateList.get(i));
            ArticleEntityObject.setCategory(new Category("politics"));
            articles.add(ArticleEntityObject);
        }

        return articles;
    }
    //Produce an ArrayList<Article> from a given f24 RSS feed URL
    public static ArrayList<Article> f24Adapter(String rssLink) throws IOException, ParserConfigurationException, SAXException
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

        ArrayList<String> imageList = getF24ImageURLs(docToString(doc));

        Article ArticleEntityObject=new Article();
        for(int i = 0; i < dateList.size(); i++)
        {ArticleEntityObject.setTitle(titleList.get(i));
            ArticleEntityObject.setDescription(descList.get(i));
            ArticleEntityObject.setSource("France 24");
            ArticleEntityObject.setLink(linkList.get(i));
            ArticleEntityObject.setImage(imageList.get(i));
            ArticleEntityObject.setDate(dateList.get(i));
            ArticleEntityObject.setCategory(new Category("politics"));
            articles.add(ArticleEntityObject);

        }

        return articles;
    }
    //Produce an ArrayList<Article> from a given CNN RSS feed URL
    public static ArrayList<Article> CNNAdapter(String rssLink) throws IOException, ParserConfigurationException, SAXException
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

        int nextKey = 0;
        for(int i = 0; i < itemNodeList.getLength(); i++)
        {
            //Full, title, description, link, image, date
            articleMap.put(nextKey++, new String[]{itemList.get(i), "NO_TITLE", "NO_DESCRIPTION", "NO_LINK", "NO_IMAGE", "NO_DATE"});
            for(int j = 0; j < titleList.size(); j++)
            {
                if(itemNodeList.item(i).getTextContent().contains(titleList.get(j)))
                {
                    articleMap.get(i)[1] = titleList.get(j);
                    break;
                }
            }
            for(int j = 0; j < descList.size(); j++)
            {
                if(itemNodeList.item(i).getTextContent().contains(descList.get(j)))
                {
                    articleMap.get(i)[2] = descList.get(j);
                    break;
                }
            }
            for(int j = 0; j < linkList.size(); j++)
            {
                if(itemNodeList.item(i).getTextContent().contains(linkList.get(j)))
                {
                    articleMap.get(i)[3] = linkList.get(j);
                }
            }
            for(int j = 0; j < imageList.size(); j++)
            {
                if(itemList.get(i).contains(imageList.get(j)))
                {
                    articleMap.get(i)[4] = imageList.get(j);
                }
            }
            for(int j = 0; j < dateList.size(); j++)
            {
                if(itemNodeList.item(i).getTextContent().contains(dateList.get(j)))
                {
                    articleMap.get(i)[5] = dateList.get(j);
                }
            }
        }
        Article ArticleEntityObject=new Article();
        for(int i = 0; i < dateList.size(); i++)
        {
            ArticleEntityObject.setTitle(titleList.get(i));
            ArticleEntityObject.setDescription(descList.get(i));
            ArticleEntityObject.setSource("France 24");
            ArticleEntityObject.setLink(linkList.get(i));
            ArticleEntityObject.setImage(imageList.get(i));
            ArticleEntityObject.setDate(dateList.get(i));
            ArticleEntityObject.setCategory(new Category("politics"));
            articles.add(ArticleEntityObject);
        }

        return articles;
    }

    //Used to extract the image URLs from BBC feeds
    public static ArrayList<String> getBBCImageURLs(String doc)
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
    public static ArrayList<String> getF24ImageURLs(String doc)
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
    public static ArrayList<String> getCNNImageURLs(String doc)
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
    public static ArrayList<String> nodeToArray(NodeList nodeList)
    {
        ArrayList<String> outList = new ArrayList<>();
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            outList.add(nodeList.item(i).getTextContent());
        }
        return outList;
    }
    //Get all of the text from between two XML tags
    public static ArrayList<String> getFullElementsByTag(String doc, String tag)
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

    //Forum code
    //Extract HTML from URL and convert to String
    public static String readFromWeb(String webURL) throws IOException
    {
        URL url = new URL(webURL);
        String output = "";
        InputStream is =  url.openStream();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
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
        return output;
    }
    //Convert a Document to a String
    public static String docToString(Document doc)
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
