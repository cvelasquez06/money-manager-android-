package cl.underline.dev.gastos.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.underline.dev.gastos.Models.Expense;
import cl.underline.dev.gastos.R;
import cl.underline.dev.gastos.Utility.Message;
import cl.underline.dev.gastos.Utility.Utility;

public class ActivitySave extends Activity {
    Expense expense = new Expense();
    Utility util = new Utility();
    @BindView(R.id.text_savestatus) TextView saveStatus;
    @BindView(R.id.header_amount) TextView headerAmount;
    @BindView(R.id.header_category) TextView headerCategoryName;
    @BindView(R.id.header_project) TextView headerProjectName;
    @BindView(R.id.header_fecha) TextView headerFecha;
    @BindView(R.id.expense_comment) EditText textComment;
    @BindView(R.id.button_save_expense) Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        ButterKnife.bind(this);
        expense = util.setIntent(this);
        headerAmount.setText(expense.getExpense_amount());
        headerProjectName.setText(expense.getExpense_projectname());
        headerCategoryName.setText(expense.getExpense_categoryname());
        headerFecha.setText(expense.getExpense_date());
    }
    private void save_comment(){
        expense.setExpense_comment(textComment.getText().toString());
    }
    @OnClick(R.id.button_save_expense)
    public void closeAll(){
        buttonSave.setEnabled(false);
        save_comment();
        String status = util.setDataJsonRest("https://www.underline.cl/expense","POST", expense.toGsonString());
        if(Objects.equals(status, "200")){
            saveStatus.setText("Su información ha sido almacenada con éxito.");
            Toast message = Message.messagesToast(ActivitySave.this, "Su información ha sido almacenada con éxito.");
            message.show();
            finishAffinity();
            Intent activityMenu = new Intent(ActivitySave.this, ActivityMenu.class);
            startActivity(activityMenu);
            finish();
        }else{
            saveStatus.setText("A ocurrido un error: " + status);
            Toast message = Message.messagesToast(ActivitySave.this, "A ocurrido un error: " + status);
            message.show();
        }
        //Toast message = Message.messagesToast(ActivitySave.this, "Redireccionando ....");
        //message.show();
      /*  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){*/
                //command to execute();
   /*        }
        },2000);*/
    }
}
