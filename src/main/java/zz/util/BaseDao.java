package zz.util;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import zz.util.Page;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T,I extends Serializable> {
    List<T> findAll();
    void add(T entity);
    void update(T entity);
    void delete(I id);
    T findById(I id);
    T findByExampleWithBean(T example);
    int count(@Param("example") T example);
    List<T> findByExample(@Param("example") T example);
    List<T> findByExampleWithPage(@Param("example") T example, @Param("start") Integer start, @Param("rows") Integer rows);
    List<Map<String,Object>> findByMapWithPage(Map<String,Object> params, Integer page, Integer rows);
}
