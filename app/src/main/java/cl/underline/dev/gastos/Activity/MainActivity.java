package cl.underline.dev.gastos.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.underline.dev.gastos.Models.Expense;
import cl.underline.dev.gastos.R;
import cl.underline.dev.gastos.Utility.Message;


/**
 * Created by cvelasquez@underline.cl developer on 16-02-2018.
 */

public class MainActivity extends Activity {

    @BindView(R.id.layout1LabelMsg) TextView LabelMsg;
    @BindView(R.id.layout1MoneyNumber) EditText TextMoney;

    Expense expenses = new Expense();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind (this);
        }


    @OnClick(R.id.button_amount)

    public void nextActivityProject(){
        if(TextMoney.length()>0) {
            Intent activityProject = new Intent(this, ActivityProject.class);
            expenses.setExpense_amount(TextMoney.getText().toString());
            activityProject.putExtra("expenses", expenses.toGsonString());
            startActivity(activityProject);
        }else{
            String texto = "Monto esta vacio";
            Toast toast = Message.messagesToast(MainActivity.this, texto.toUpperCase());
            toast.show();
        }
    }
}
