package com.codemeanslove.journalApp.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Recipe {

        public int id;
        public String title;
        public String image;
        public String imageType;
        public int usedIngredientCount;
        public int missedIngredientCount;
        public List<MissedIngredient> missedIngredients;
        public List<UsedIngredient> usedIngredients;
        public List<UnUsedIngredient> unusedIngredients;
        public int likes;

    @Getter
    @Setter
    public static class MissedIngredient{
        public int id;
        public double amount;
        public String unit;
        public String unitLong;
        public String unitShort;
        public String aisle;
        public String name;
        public String original;
        public String originalName;
        public List<String> meta;
        public String image;
        public String extendedName;
    }


    @Getter
    @Setter
    public static class UsedIngredient{
        public int id;
        public double amount;
        public String unit;
        public String unitLong;
        public String unitShort;
        public String aisle;
        public String name;
        public String original;
        public String originalName;
        public List<String> meta;
        public String extendedName;
        public String image;
    }

    @Getter
    @Setter
    public static class UnUsedIngredient{
        public int id;
        public double amount;
        public String unit;
        public String unitLong;
        public String unitShort;
        public String aisle;
        public String name;
        public String original;
        public String originalName;
        public List<String> meta;
        public String extendedName;
        public String image;
    }
}
