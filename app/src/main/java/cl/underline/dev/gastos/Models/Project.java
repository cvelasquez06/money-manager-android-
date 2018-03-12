package cl.underline.dev.gastos.Models;


/**
 * Created by cvelasquez@underline.cl developer on 12-02-2018.
 */

public class Project {
    private String project_name;
    private String project_id;
    private String project_description;

    public Project(String project_id, String project_name, String project_description){
        this.setProject_name(project_name);
        this.setProject_id(project_id);
        this.setProject_description(project_description);
    }
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }
}
