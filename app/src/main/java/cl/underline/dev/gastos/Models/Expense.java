package cl.underline.dev.gastos.Models;
import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by cvelasquez@underline.cl developer on 18-02-2018.
 */

public class Expense {

    private int expense_id;
    private String expense_projectname = "sin información";
    private int expense_projectid = 0;
    private String expense_categoryname = "sin información";
    private int expense_categoryid = 0;
    private String expense_date = "sin información";
    private String expense_amount=null;
    private String expense_comment=null;

    public String getExpense_comment() {
        return expense_comment;
    }

    public void setExpense_comment(String expense_comment) {
        this.expense_comment = expense_comment;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public String getExpense_projectname() {
        return expense_projectname;
    }

    public void setExpense_projectname(String expense_projectname) {
        this.expense_projectname = expense_projectname;
    }

    public int getExpense_projectid() {
        return expense_projectid;
    }

    public void setExpense_projectid(int expense_projectid) {
        this.expense_projectid = expense_projectid;
    }

    public String getExpense_categoryname() {
        return expense_categoryname;
    }

    public void setExpense_categoryname(String expense_categoryname) {
        this.expense_categoryname = expense_categoryname;
    }

    public int getExpense_categoryid() {
        return expense_categoryid;
    }

    public void setExpense_categoryid(int expense_categoryid) {
        this.expense_categoryid = expense_categoryid;
    }

    public String getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(String expense_date) {
        this.expense_date = expense_date;
    }

    public String getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(String expense_amount) {
        this.expense_amount = expense_amount;
    }

    public String toGsonString(){
        Gson gson = new Gson();
        String expensesData = gson.toJson(this);
        return expensesData;
    }
}
