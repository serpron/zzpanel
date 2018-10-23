package zz.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Layui表格数据
 * {
 *   "code": 0,
 *   "msg": "",
 *   "count": 1000,
 *   "data": [{}, {}]
 * }
 */
public class LayuiTableData<T> {

    private int code = 0;
    private int count = 1000;
    private String msg = "";
    private List<T> data = new ArrayList<>();

    public static <T> LayuiTableData<T> fromPage(Page<T> pageBean){
        LayuiTableData<T> tableData = new LayuiTableData<>();
        tableData.setCount(pageBean.getTotals());
        tableData.setData(pageBean.getList());
        return tableData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
