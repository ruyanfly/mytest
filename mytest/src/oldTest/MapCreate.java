package oldTest;

class mapEntry {
	
	Object key;  
    Object value;
    
    public mapEntry(Object key, Object value) {
    	super();
    	this.key = key;
    	this.value = value;
    }  
      
} 

public class MapCreate {
	
	mapEntry[] arr = new mapEntry[990];//放多点，不用扩容;
	int size;
	//put方法 
	public void put(Object key, Object value) {
		mapEntry e= new mapEntry(key, value);
		//解决键值对重复的处理，直接覆盖
		for(int i = 0; i < size; i++) {//遍历数组
			if(arr[i].key.equals(key)){//注意：使用的equals
				arr[i].value = value;
				return;//跳出 
			}
		}
		arr[size++] = e;
	}
	//get方法  
    public Object get(Object key) {
    	
    	for(int i = 0; i < size; i++) {
    		if(arr[i].key.equals(key)) {
    			return arr[i].value;
    		}
    	}
    	return null;
    }
    
    public boolean containKey(Object key) {
    	for(int i = 0; i < size; i++) {
    		if(arr[i].key.equals(key)) {
    			return true;
    		}
    	}
    	return false;
    }  
    
    public boolean containValue(Object value) {
    	for(int i = 0; i < size; i++) {
    		if(arr[i].value.equals(value)) {
    			return true;
    		}
    	}
    	return false;  
    }  

    public static void main(String[] args) {
    	MapCreate m= new MapCreate();  
        m.put("soto", "呆");  
        m.put("张三", "李四");  
        m.put("张三", "王五");  
        String w = (String) m.get("张三");  
        System.out.println(w);  
          
    }  
}
