package zz.util;

import zz.util.Page;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T,I extends Serializable> {
    List<T> findAll();
    T add(T entity);
    void update(T entity);
    T delete(I id);
    T findById(I id);
    Page<T> find(T example,Integer page,Integer rows);
    Page<Map<String,Object>> find(Map<String,Object> params, Integer page, Integer rows);
}
