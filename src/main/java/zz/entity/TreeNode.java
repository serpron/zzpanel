package zz.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * layui Treeseelct数据节点
 */
public class TreeNode {

    private Integer id;
    private String name;
    private Integer parent;
    private Boolean open = true;
    private String selected;
    private String disabled;
    private List<TreeNode> children = new ArrayList<>();
    private String url;
    private Integer level;

    public Integer getValue(){return id;}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getSelected() {
        return selected;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }


}
