package cl.underline.dev.gastos.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.underline.dev.gastos.Adapters.AdapterItemExpense;
import cl.underline.dev.gastos.Models.Expense;
import cl.underline.dev.gastos.R;
import cl.underline.dev.gastos.Utility.Utility;

import static java.lang.System.exit;

public class ActivityMenu extends Activity {
    @BindView(R.id.floating_menu) FloatingActionButton floatingButtonMenu;
    @BindView(R.id.listview_amount) ListView expenseListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        try {
            setDataToListViewExpense();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @OnClick(R.id.floating_menu)
    public void addExpense(){
        Intent activityMain = new Intent(ActivityMenu.this, MainActivity.class);
        startActivity(activityMain);
    }

    private Boolean setDataToListViewExpense() throws JSONException {
        Utility util = new Utility();
        String urlJson = null;
        JSONArray arrElement = null;

        urlJson = util.getDataJsonRest("https://www.underline.cl/expense/10","GET");
        if(urlJson == null){
            return false;
        }
        arrElement = util.getStringToJsonArray(urlJson, "expense");
        ArrayList<Expense> elements = new ArrayList<>();
        for (int a = 0; a < arrElement.length(); a++) {
            Expense expense = new Expense();
            expense.setExpense_id((Integer) arrElement.getJSONObject(a).get("expense_id"));
            expense.setExpense_amount(arrElement.getJSONObject(a).get("expense_amount").toString());
            expense.setExpense_date(arrElement.getJSONObject(a).get("expense_date").toString());
            expense.setExpense_categoryname(arrElement.getJSONObject(a).get("category_name").toString());
            expense.setExpense_projectname(arrElement.getJSONObject(a).get("project_name").toString());
            expense.setExpense_comment(arrElement.getJSONObject(a).get("expense_comment").toString());
            elements.add(expense);
        }
        AdapterItemExpense adapter = new AdapterItemExpense(this, expenseListView.getId(), elements);
        ListView lv = (ListView) expenseListView;
        lv.setAdapter(adapter);
        return true;
    }
}
