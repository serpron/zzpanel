package zz.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {
    private Integer id;
    private Date create_time;
    private String description;
    private String name;
    private String title;
    private Integer[] users ;
    private Integer[] resources;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getUsers() {
        return users;
    }

    public void setUsers(Integer[] users) {
        this.users = users;
    }

    public Integer[] getResources() {
        return resources;
    }

    public void setResources(Integer[] resources) {
        this.resources = resources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
