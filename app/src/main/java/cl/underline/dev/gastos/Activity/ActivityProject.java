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
import cl.underline.dev.gastos.Adapters.AdapterItemProject;
import cl.underline.dev.gastos.Models.Expense;
import cl.underline.dev.gastos.Models.Project;
import cl.underline.dev.gastos.R;
import cl.underline.dev.gastos.Utility.Utility;


/**
 * Created by cvelasquez@underline.cl developer on 16-02-2018.
 */

public class ActivityProject extends Activity {
    @BindView(R.id.header_amount) TextView headerAmount;
    @BindView(R.id.listview_project) ListView projectListView;
    String expensesData = null;
    Expense expense = new Expense();
    Gson gson = new Gson();
    Utility util = new Utility();
    public View row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);
        expense = util.setIntent(this);
        headerAmount.setText(expense.getExpense_amount());
        try {
            setDataToListViewProject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        projectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (row != null) {
                    row.setBackgroundResource(R.color.white);
                }
                row = view;
                view.setBackgroundResource(R.color.gray);
                TextView txtId= (TextView) view.findViewById(R.id.projectItemId);
                int projectId = Integer.parseInt(txtId.getText().toString());
                TextView txtName= (TextView) view.findViewById(R.id.projectItemName);
                String projectName = txtName.getText().toString();
                expense.setExpense_projectid(projectId);
                expense.setExpense_projectname(projectName);
                Intent activityCategory = new Intent(ActivityProject.this, ActivityCategory.class);
                activityCategory.putExtra("expenses",expense.toGsonString());
                startActivity(activityCategory);
            }
        });
    }

    private void setDataToListViewProject() throws JSONException {
        Utility util = new Utility();
        String urlJson = null;
        JSONArray arrElement = null;
        urlJson = util.getDataJsonRest("https://www.underline.cl/project","GET");
        arrElement = util.getStringToJsonArray(urlJson, "project");
        ArrayList<Project> elements = new ArrayList<>();
        for (int a = 0; a < arrElement.length(); a++) {
            elements.add(new Project(
                    arrElement.getJSONObject(a).get("project_id").toString(),
                    arrElement.getJSONObject(a).get("project_name").toString(),
                    arrElement.getJSONObject(a).get("project_description").toString()
            ));
        }
        AdapterItemProject adapter = new AdapterItemProject(this, projectListView.getId(), elements);
        ListView lv = (ListView) projectListView;
        lv.setAdapter(adapter);
    }
}
