package org.example;

import java.util.Random;

public class Category {
    private int idCategory;
    private String CategoryName;

    public Category(int idCategory, String categoryName) {
        this.idCategory = idCategory;
        this.CategoryName = categoryName;
    }

    public Category(int idCategory) {
        String [] LCategories = {
                "Fiction",
                "Non-fiction",
                "Mystery",
                "Science Fiction",
                "Fantasy",
                "Romance",
                "Thriller",
                "Horror",
                "Biography",
                "Self-Help",
                "History",
                "Travel",
                "Cooking",
                "Poetry",
                "Art",
                "Science",
                "Business",
                "Philosophy",
                "Religion",
                "Children's"
        };
        this.idCategory = idCategory;
        this.CategoryName = LCategories[(new Random()).nextInt(LCategories.length)];
    }
    public int getIdC() {
        return idCategory;
    }

    public void setIdC(int idC) {
        this.idCategory = idC;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
