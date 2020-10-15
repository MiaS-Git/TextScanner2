package com.example.textscanner.ModifiedwebScraper.src;

import android.content.ClipData;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.textscanner.MainActivity;
import com.example.textscanner.ParseItem;
import com.example.textscanner.R;
import com.example.textscanner.ScrappedActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class JsoupRun extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScrappedActivity adaptor;
    private static ArrayList<String> Items;
    private ProgressBar progressBar;
    EditText recipes;
    TextView tv;
    TextView tv2;
    String st;
    Button button;
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup_run2);
        Items = new ArrayList<String>();

       st = getIntent().getExtras().getString("Title");
       tv = findViewById(R.id.textView);
       tv.setText(Items.toString());

    }



    public void main(String[] args) throws IOException {

        //Elements recipeLinks = findUrls("https://www.allrecipes.com/search/results/?wt=spaghetti&sort=re", "h3.fixed-recipe-card__h3");
        //Elements recipeLinks = findUrlas("https://www.allrecipes.com/recipes/78/breakfast-and-brunch/", "recipeCard__titleLinka");
        String baseUrl = baseUrlInput();

        System.out.println(baseUrl);

        String pageUrl = baseUrl;
        int pageNum = 2;
        while (isValid(pageUrl) && pageNum < 3) {
            Elements recipeLinks = findUrls(pageUrl, "h3.fixed-recipe-card__h3");
            pageUrl = baseUrl + "&page=" + pageNum;
            pageNum++;
            printLinks(recipeLinks);
        }

    }

    public static Elements findUrls(String mainUrl, String recipesHeader) throws IOException {
        Document doc = Jsoup.connect(mainUrl).get();
        Elements recipes = doc.select(recipesHeader);
        Elements links = recipes.select("a[href]");
        return links;
    }

    public static boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    public static void printLinks(Elements links) throws IOException {
        int count = 1;
        for (Element link : links) {
            System.out.println(count + ": " + link.attr("abs:href"));
            getInfo(link.attr("abs:href"));
            count++;
        }
    }


    public String baseUrlInput(){
        EditText recipes;
        recipes = findViewById(R.id.editTextId);

        String recipesList = recipes.getText().toString();

        StringBuilder result = new StringBuilder();
        String beginning = "https://www.allrecipes.com/search/results/?wt=";
        String filler = "%20";
        String ending = "&sort=re";

        result.append(beginning);

        for (int i = 0; i < recipesList.length(); i++) {
            if (Character.isWhitespace(recipesList.charAt(i))) {
                result.append(filler);
            } else {
                result.append(recipesList.charAt(i));
            }
        }
        result.append(ending);
        String list = result.toString();
        return list;
    }


        public static void getInfo(String url) throws IOException {
            Document doc = Jsoup.connect(url).get();
            Elements base = doc.select("div");
            String Title = base.select("h1.headline.heading-content").text();
            String Summary = base.select("p").text();
            String info = base.select("section.recipe-meta-container.two-subcol-content.clearfix").text();
            String Ingredients = base.select("ul.ingredients-section").text();
            String Directions = base.select("ul.instructions-section").text();

            Items.add(Title);
            Log.d("ItemsZFromParse", "getInfo: Title" + Title);

            System.out.println("Title:" + Title);
            System.out.println("Summary:" + Summary);
            System.out.println("info:" + info);
            System.out.println("Ingredients:" + Ingredients);
            System.out.println("Directions:" + Directions);


        }



}
