package cl.underline.dev.gastos.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import cl.underline.dev.gastos.Models.Project;
import cl.underline.dev.gastos.R;

/**
 * Created by cvelasquez@underline.cl developer on 13-02-2018.
 */

public class AdapterItemProject extends ArrayAdapter<Project> {

    public AdapterItemProject(Context context, int resource, ArrayList<Project> items) {
        super(context, resource, items);
    }
    public View getView(int position, View view, ViewGroup parent) {
        Project project = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        
        View rowView = inflater.inflate(R.layout.layout_item_project, null, true);
        TextView project_name = (TextView) rowView.findViewById(R.id.projectItemName);
        TextView project_id = (TextView) rowView.findViewById(R.id.projectItemId);
        TextView project_description = (TextView) rowView.findViewById(R.id.projectDescription);
        project_name.setText(project.getProject_name());
        project_id.setText(project.getProject_id());
        project_description.setText(project.getProject_description());
        return rowView;
    }
}