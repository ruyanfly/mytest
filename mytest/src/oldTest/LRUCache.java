package oldTest;

import java.util.HashMap;

public class LRUCache<K, V> {
	
	class CacheNode{
		CacheNode pre;
		CacheNode next;
		Object key;
		Object value;
		//LRU-K算法中的计数器，用于防止LRU出现的缓存污染，当节点被访问次数达到K时节点放置到初始位置
		//缓存污染是指LRU可能会把不经常使用的节点保存在内存中
		//int count;
		public CacheNode() {
			
		}
	}
	
	@SuppressWarnings("unused")
	private int currentCacheSize;
	//队列最大值
	private int CacheCapcity;
	//队列缓存容器
	private HashMap<K, CacheNode> caches;
	private CacheNode first;
	private CacheNode last;
	//LRUCahce初始化
	public LRUCache(int size) {
		currentCacheSize = 0;
		this.CacheCapcity = size;
		caches = new HashMap<K, CacheNode>(size);
	}
	//实现新增插入
	public void put(K key, V value) {
		CacheNode node = caches.get(key);
		if(node == null) {
			//当前队列中无新增Key值，或者原Key值为空
			if(caches.size() >= CacheCapcity) {
				//队列当前长度超过允许最大值
				caches.remove(last.key);
				removeLast();
			}
			//当前队列没有新增值时新建一个对象用于插入
			node = new CacheNode();
			node.key = key;
		}
		node.value =value;
		//把新增的对象移动到最上层
		moveToFirst(node);
		caches.put(key, node);
	}
	//这里表示的是被访问的节点
	public Object get(K key) {
		CacheNode node = caches.get(key);
		if(null == node) {
			return null;
		}
		//根据LRU原理被访问的节点也需要变更为初始节点
		moveToFirst(node);
		//LRU-k算法在这里需要判断计数器大小
		return node.value;
	}
	
	public Object remove(K key) {
		CacheNode node = caches.get(key);
		if(null != node) {
			if(null != node.pre) {
				node.pre.next = node.next;
			}
			if(null != node.next) {
				node.next.pre = node.pre;
			}
			if(node == first) {
				first = node.next;
			}
			if(node == last) {
				last = node.pre;
			}
		}
		return caches.remove(key);
	}
	
	public void clear() {
		first = null;
		last = null;
		caches.clear();
	}
	
	private void moveToFirst(CacheNode node) {
		if(first == node) {
			return;
		}
		if(null != node.next) {
			node.next.pre = node.pre;
		}
		if(null != node.pre) {
			node.pre.next = node.next;
		}
		if(node == last) {
			last = last.pre;
		}
		if(null == first || null == last) {
			first = last = node;
			return;
		}
		node.next = first;
		first.pre = node;
		first = node;
		first.pre= null;
	}
	
	private void removeLast() {
		if(null != last) {
			last = last.pre;
			if (null == last) {
				first = null;
			}else {
				last.next = null;
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		CacheNode node = first;
		while(null != node) {
			sb.append(String.format("%s:%s", node.key, node.value));
			node = node.next;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		LRUCache<Integer,String> lru = new LRUCache<Integer,String>(3);
		
		lru.put(1, "a");    // 1:a
		System.out.println(lru.toString());
		lru.put(2, "b");    // 2:b 1:a 
        System.out.println(lru.toString());
        lru.put(3, "c");    // 3:c 2:b 1:a 
        System.out.println(lru.toString());
        lru.put(4, "d");    // 4:d 3:c 2:b
        System.out.println(lru.toString());
        lru.put(1, "aa");   // 1:aa 4:d 3:c
        System.out.println(lru.toString());
        lru.put(2, "bb");   // 2:bb 1:aa 4:d
        System.out.println(lru.toString());
        lru.put(5, "e");    // 5:e 2:bb 1:aa
        System.out.println(lru.toString());
        lru.get(1);         // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(11);     // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(1);      //5:e 2:bb
        System.out.println(lru.toString());
        lru.put(1, "aaa");  //1:aaa 5:e 2:bb
        System.out.println(lru.toString());
	}
}
