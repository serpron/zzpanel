package zz.util;
import java.util.*;
import zz.entity.Dictionary;
import zz.dao.DictionaryMapper;

/***************************************************
 * 数据字典基础类
 * @author zyg
 ***************************************************/
public class DictionaryService {

	/**
	 * 静态实例便于访问
	 */
	private static DictionaryService instance = null;
	
	private DictionaryMapper dictionaryMapper = null;
	
	/**
	 * 数据字典类型
	 */
	private String typenames[] = new String[0];
	
	/**
	 * 数据字典SQL字符串
	 */
	private String typenameString = "";
	
	/**
	 * 字典内存存储
	 */
	private HashMap<String,LinkedHashMap<String,String>> propsTab = new HashMap<>();
	
	/**
	 * 缺省构造函数
	 */
	public DictionaryService(){}
	/**
	 * 构造方法，初始化数据字典类型
	 * @param typenames
	 */
	public DictionaryService(String[] typenames){
		if(typenames==null) return;
		this.typenames = typenames;
		this.typenameString = arrayToSqlString();
	}
	
	/**
	 * 查询内存存储返回键对应的值，如果找到则返回空字符串
	 * @param key
	 * @return
	 */
	public String getProperty(String key,String typeName){
		LinkedHashMap<String,String> map  = propsTab.get(typeName);
		String value = "";
		if(map != null){
			value = map.get(key);
		}
		if(value==null){
			value= "";
		}
		return value;
	}
	
	/**
	 * 返回指定类型的所有字典数据
	 * @param typeName
	 * @return
	 */
	public Map<String,String> getProperties(String typeName){
		if(this.propsTab.containsKey(typeName)){
			return this.propsTab.get(typeName);
		}
		else{
			return new HashMap();
		}
	}
	
	public static Map<String,String> properties(String typeName){
		if(instance.propsTab.containsKey(typeName)){
			return instance.propsTab.get(typeName);
		}
		else{
			return new HashMap();
		}	
	}
	
	/**
	 * 添加键值对
	 * @param key
	 * @param value
	 */
	public void addProperty(String key,String value,String typeName){
		if(this.propsTab.containsKey(typeName)){
			this.propsTab.get(typeName).put(key, value);
		}
		else{
			LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
			map.put(key, value);
			this.propsTab.put(typeName, map);
		}
	}
	
	/**
	 * 删除字典项
	 * @param key
	 */
	public void delProperty(String key){
		this.propsTab.remove(key);
	}
	
	/**
	 * 将已有的集合添加到字典中
	 * @param props
	 */
	public void addProperties(Hashtable props){
		this.propsTab.putAll(props);
	}
	
	public String arrayToSqlString(){
		StringBuffer sqlBuf = new StringBuffer();
		for(String str : this.typenames){
			sqlBuf.append("'").append(str).append("',");
		}
		return sqlBuf.toString();
	}
	
	/**
	 * 从数据库加载字典
	 */
	public void load(){
		List<Dictionary> list = this.dictionaryMapper.findByExample(null);
		for(Dictionary dic : list){
			this.addProperty(dic.getCode(),dic.getName(),dic.getTypename());
		}
		//字典加载时初始化静态实例
		instance = this;
	}
	
	public void reload(){
		this.propsTab.clear();
		load();
	}
	
	public static DictionaryService getInstance(){
		return instance;
	}

	public static void setInstance(DictionaryService instance) {
		DictionaryService.instance = instance;
	}

	public DictionaryMapper getDictionaryMapper() {
		return dictionaryMapper;
	}

	public void setDictionaryMapper(DictionaryMapper dictionaryMapper) {
		this.dictionaryMapper = dictionaryMapper;
	}

	public String[] getTypenames() {
		return typenames;
	}

	public void setTypenames(String[] typenames) {
		this.typenames = typenames;
	}

	public String getTypenameString() {
		return typenameString;
	}

	public void setTypenameString(String typenameString) {
		this.typenameString = typenameString;
	}

	public HashMap<String, LinkedHashMap<String, String>> getPropsTab() {
		return propsTab;
	}

	public void setPropsTab(HashMap<String, LinkedHashMap<String, String>> propsTab) {
		this.propsTab = propsTab;
	}
}
