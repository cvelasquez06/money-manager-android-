package cl.underline.dev.gastos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cl.underline.dev.gastos.Models.Expense;
import cl.underline.dev.gastos.R;

/**
 * Created by cvelasquez@underline.cl developer on 22-02-2018.
 */

public class AdapterItemExpense extends ArrayAdapter<Expense> {
    public AdapterItemExpense(Context context, int resource, ArrayList<Expense> items) {
        super(context, resource, items);
    }
    public View getView(int position, View view, ViewGroup parent) {
        Expense expense = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        View rowView = inflater.inflate(R.layout.layout_item_expense, null, true);

        TextView expense_amount = (TextView) rowView.findViewById(R.id.title_item_amount);
        TextView expense_date = (TextView) rowView.findViewById(R.id.title_item_date);
        TextView expense_projectname = (TextView) rowView.findViewById(R.id.title_item_project);
        TextView expense_categoryname = (TextView) rowView.findViewById(R.id.title_item_category);
        TextView expense_comment = (TextView) rowView.findViewById(R.id.text_item_comment);

        expense_amount.setText(expense.getExpense_amount());
        expense_date.setText(expense.getExpense_date());
        expense_projectname.setText(expense.getExpense_projectname());
        expense_categoryname.setText(expense.getExpense_categoryname());
        expense_comment.setText(expense.getExpense_comment());
        return rowView;
    }
}
