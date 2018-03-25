package pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.model;

/**
 * Created by monoc on 3/24/2018.
 */

public class Todo {

    //deklarasikan variabel yang akan digunakan
    private String todo;
    private String description;
    private String priority;

    //konstruktor class
    public Todo(String todo, String description,  String priority) {
        this.todo = todo;
        this.description = description;
        this.priority = priority;
    }


    //Setter getter--
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}