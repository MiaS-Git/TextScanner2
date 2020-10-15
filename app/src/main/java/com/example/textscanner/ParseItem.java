package com.example.textscanner;

public class ParseItem {
    private String title;
    private String ingredients;

public ParseItem(){
}

public ParseItem(String title, String ingredients){
    this.title = title;
    this.ingredients = ingredients;
}

    public String getTitle() {
        return title;
    }
    public String getIngredients() {
        return ingredients;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
