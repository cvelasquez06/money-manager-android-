package cl.underline.dev.gastos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cl.underline.dev.gastos.Models.Category;
import cl.underline.dev.gastos.R;

/**
 * Created by cvelasquez@underline.cl developer on 16-02-2018.
 */

public class AdapterItemCategory extends ArrayAdapter<Category> {

    public AdapterItemCategory(Context context, int resource, ArrayList<Category> items) {
        super(context, resource, items);
    }

    public View getView(int position, View view, ViewGroup parent) {
        Category category = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        View rowView = inflater.inflate(R.layout.layout_item_category, null, true);
        TextView category_name = (TextView) rowView.findViewById(R.id.categoryItemName);
        TextView category_id = (TextView) rowView.findViewById(R.id.categoryItemId);
        TextView category_description = (TextView) rowView.findViewById(R.id.categoryDescription);
        category_name.setText(category.getName());
        category_id.setText(category.getId());
        category_description.setText(category.getDescription());
        return rowView;
    }
}
