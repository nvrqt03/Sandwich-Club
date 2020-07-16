package com.udacity.sandwichclub.utils;

import android.widget.ImageView;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_AKA = "alsoKnownAs";
        final String SANDWICH_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE_URL = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        String name = "", mainName = "", placeOfOrigin = "", image = "", description = "";
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();

        JSONObject mainObj, nameObj;
        JSONArray arrayAka, arrayIngredients;

        try {
            mainObj = new JSONObject(json);

            nameObj = mainObj.getJSONObject(SANDWICH_NAME);
            mainName = nameObj.getString(SANDWICH_MAIN_NAME);
            image = mainObj.getString(SANDWICH_IMAGE_URL);
            placeOfOrigin = mainObj.getString(SANDWICH_ORIGIN);
            description = mainObj.getString(SANDWICH_DESCRIPTION);

            ingredients = jsonArrayToList(mainObj.getJSONArray(SANDWICH_INGREDIENTS));
            alsoKnownAs = jsonArrayToList(nameObj.getJSONArray(SANDWICH_AKA));

//    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients)

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    public static List<String> jsonArrayToList(JSONArray array) {
        List<String> sandwichList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                sandwichList.add(array.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return sandwichList;
    }
}

