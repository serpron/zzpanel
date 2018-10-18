package zz.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * layui Treeseelct数据节点
 */
public class TreeselectNode {

    private Integer id;
    private String name;
    private Integer parent_id;
    private List<TreeselectNode> children = new ArrayList<>();
    private String href;

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

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public List<TreeselectNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeselectNode> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
