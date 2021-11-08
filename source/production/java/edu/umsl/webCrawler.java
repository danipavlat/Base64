package edu.umsl;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.HashSet;

public class WebCrawler {

    public static void main(String[] args) {
        webCrawler crawler = new webCrawler();
        Scanner input = new Scanner(System.in);
        // Enter a URL
        System.out.print("Enter a URL (e.g., http://en.wikipedia.org/wiki/bitcoins) : ");
        String url = input.nextLine();

        // method to get the links from the webpage
        crawler.getLinks(url);

        System.out.println("\nTitles of pages traversed:");
        crawler.printTitles();

        System.out.println("\nWords encountered (word counts):");
        crawler.printWords();
    }
}

class webCrawler {
    // Number of links traversed
    private static final int MAX_LINKS = 10;

    Set<String> links = new HashSet<>();
    // url traversed
    Set<String> traversedURL = new HashSet<>();
    // words encountered and counts
    Map<String, Integer> map = new HashMap<>();

    // function to define the web crawler
    public void getLinks(String URL) {

        // check if we have already crawled the URLs
        if ( (!traversedURL.contains(URL)) && (links.size() < MAX_LINKS) ) {

            try {
                // if not, add it to the index
                traversedURL.add(URL);
                // Fetch the HTML code and return a HTML document
                //   the connect() method creates a connection to the URL
                //   the get() method executes a GET request and parses the result
                Document document = Jsoup.connect(URL).get();
                // Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                // read the title of an URL
                String title = document.title();
                System.out.println("Crawl " + URL);
                links.add(title);

                // read the content of the webpage
                String page = document.body().text();

                for (Element link : linksOnPage) {
                    if (links.size() < MAX_LINKS) {
                        // sleep 0.1 second between hitting each link
                        Thread.sleep(100);
                        getLinks(link.attr("abs:href"));
                    }
                }
                wordCount(page); // count words

            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    // function to get the count of each word on the traversed page
    private void wordCount(String page) {
        String[] words = page.split(" "); // break the sentences into words
        for (String word : words) {
            // if word has already encountered, increment the count (map.get(word)); otherwise 1
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else
                map.put(word, 1);
        }
    }

    // function to print out the title of each page traversed
    void printTitles() {
        for (String eachTitle : links)
            System.out.println(eachTitle);
    }

    // function to print out a list of words encountered
    void printWords(){
        for (String eachWord : map.keySet())
            System.out.println(eachWord + " (" + map.get(eachWord) + ")");
    }
}
