package com.example.alex.budget;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import android.content.Context;

public class Data {
    static private Data data;
    private float income;
    private float budget;
    private HashMap<String, Float> outcome = new HashMap<>();
    private Data()
    {
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    static public Data getData(Context context)
    {
        if (data == null) {
            data = new Data();
            ////// read the data
            //context.getAssets().open("data.json")
            String FilePath = context.getFilesDir().getPath() + "/";
            //context.get
            String FileName = "data.json";
            File f = new File(FileName);

            try (Reader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(FileName)))){
            //try (Reader reader = new FileReader(f)){
                Gson gson = new GsonBuilder().create();
                Type listType = new TypeToken<Data>(){}.getType();
                data = gson.fromJson(reader, listType);
                //data = l.get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
    public float getOutcome(String category)
    {
        Float f = outcome.get(category);
        if (f != null)
            return f;
        else
            return 0.0f;
    }
    public void setOutcome(String category, float amout)
    {
        outcome.put(category, amout);
    }
}
