package cl.underline.dev.gastos.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.underline.dev.gastos.Adapters.AdapterItemCategory;
import cl.underline.dev.gastos.Models.Category;
import cl.underline.dev.gastos.Models.Expense;
import cl.underline.dev.gastos.R;
import cl.underline.dev.gastos.Utility.Utility;

/**
 * Created by cvelasquez@underline.cl developer on 16-02-2018.
 */

public class ActivityCategory extends Activity {
    @BindView(R.id.header_amount) TextView headerAmount;
    @BindView(R.id.header_category) TextView headerCategoryName;
    @BindView(R.id.header_project) TextView headerProjectName;
    @BindView(R.id.listview_category) ListView categoryListView;
    String expensesData = null;
    Expense expense = new Expense();
    Gson gson = new Gson();
    Utility util = new Utility();
    public View row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        expense =  util.setIntent(this);
        headerAmount.setText(expense.getExpense_amount());
        headerProjectName.setText(expense.getExpense_projectname());
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (row != null) {
                    row.setBackgroundResource(R.color.white);
                }
                row = view;
                view.setBackgroundResource(R.color.gray);
                TextView txtId= (TextView) view.findViewById(R.id.categoryItemId);
                int categoryId = Integer.parseInt(txtId.getText().toString());
                TextView txtName= (TextView) view.findViewById(R.id.categoryItemName);
                String categoryName = txtName.getText().toString();
                expense.setExpense_categoryid(categoryId);
                expense.setExpense_categoryname(categoryName);
                Intent activityCalendar = new Intent(ActivityCategory.this, ActivityCalendar.class);
                activityCalendar.putExtra("expenses", expense.toGsonString());
                startActivity(activityCalendar);
            }
        });

        try {
            setDataToListViewCategory();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setDataToListViewCategory() throws JSONException {
        Utility util = new Utility();
        String urlJson = null;
        JSONArray arrElement = null;
        urlJson = util.getDataJsonRest("https://www.underline.cl/category","GET");
        arrElement = util.getStringToJsonArray(urlJson, "category");
        ArrayList<Category> elements = new ArrayList<>();
        for (int a = 0; a < arrElement.length(); a++) {
            elements.add(new Category(
                    arrElement.getJSONObject(a).get("category_name").toString(),
                    arrElement.getJSONObject(a).get("category_description").toString(),
                    arrElement.getJSONObject(a).get("category_id").toString()
            ));
        }
        AdapterItemCategory adapter = new AdapterItemCategory(this, categoryListView.getId(), elements);
        ListView lv = (ListView) categoryListView;
        lv.setAdapter(adapter);
    }
}