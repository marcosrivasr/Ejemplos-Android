package com.marivr.dbazure;

/**
 * Created by marivr on 23/09/2017.
 */

public class TodoItem {
    public String Id;
    public String Text;

    public TodoItem(String id, String text) {
        Id = id;
        Text = text;
    }

    public TodoItem(String text) {
        Text = text;
    }

    public TodoItem() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}