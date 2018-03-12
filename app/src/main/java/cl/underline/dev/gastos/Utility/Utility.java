package cl.underline.dev.gastos.Utility;
import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

import cl.underline.dev.gastos.Models.Expense;

/**
 * Created by cvelasquez@underline.cl developer on 16-02-2018.
 */

public class Utility {
    public String getDataJsonRest(String url, String method) {
        String dataJson = null;
        try {
            dataJson = new getJsonUrl().execute(url, method).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return dataJson;
    }

    public String setDataJsonRest(String url, String method, String data) {
        String dataJson = null;
        try {
            dataJson = new sendJsonUrl().execute(url, method, data).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return dataJson;
    }

    public JSONArray getStringToJsonArray(String dataString, String className) throws JSONException {
        JSONObject jsonDataObj = new JSONObject(dataString.toString());
        JSONArray arrJsonElement = jsonDataObj.getJSONArray(className);
        return arrJsonElement;
    }

    public Expense setIntent(Activity activity){
        String expensesData;
        Expense expense;
        Gson gson = new Gson();
        Intent intent = activity.getIntent();
        expensesData = intent.getStringExtra("expenses");
        expense = gson.fromJson(expensesData, Expense.class);
        return expense;
    }
}
