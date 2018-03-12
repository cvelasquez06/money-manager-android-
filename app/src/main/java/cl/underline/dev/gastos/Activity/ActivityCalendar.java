package cl.underline.dev.gastos.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.underline.dev.gastos.Models.Expense;
import cl.underline.dev.gastos.R;
import cl.underline.dev.gastos.Utility.Utility;

public class ActivityCalendar extends Activity {
    @BindView(R.id.header_amount) TextView headerAmount;
    @BindView(R.id.header_category) TextView headerCategoryName;
    @BindView(R.id.header_project) TextView headerProjectName;
    @BindView(R.id.header_fecha) TextView headerFecha;
    @BindView(R.id.calendar_amount) CalendarView calendarView;


    String dateCalendar = null;
    Expense expense = new Expense();

    Utility util = new Utility();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        expense =  util.setIntent(this);
        headerAmount.setText(expense.getExpense_amount());
        headerProjectName.setText(expense.getExpense_projectname());
        headerCategoryName.setText(expense.getExpense_categoryname());
        calendarView.setFirstDayOfWeek(2);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                month++;
                dateCalendar = String.valueOf(year+"/"+month+"/"+dayOfMonth);
                expense.setExpense_date(dateCalendar);
                headerFecha.setText(expense.getExpense_date());
        Intent activitySave = new Intent(ActivityCalendar.this, ActivitySave.class);
        activitySave.putExtra("expenses",expense.toGsonString());
        startActivity(activitySave);
            }
        });
    }
}