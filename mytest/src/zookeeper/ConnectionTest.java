package zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ConnectionTest {
	//会话超时时间，设置为与系统默认时间一致
	private static final int SEEION_TIMEOUT = 30*1000;
	//创建ZooKeeper实例
	private ZooKeeper zk;
	//创建Watcher实例
	private Watcher wh = new Watcher() {
		public void process(WatchedEvent event) {
			System.out.println("WatchedEvent: " + event.toString());
		}
	};
	//初始化ZooKeeper实例
	private void createZKInstance() throws IOException {
		zk = new ZooKeeper("127.0.0.1:2181", SEEION_TIMEOUT, this.wh);
	}
	//ZooKeeper操作
	private void ZKOperation() throws KeeperException, InterruptedException {
		System.out.println("\n1.创建ZooKeeper节点(znode:zoo2,数据：myData2,权限,OPEN_ACL_UNSAFE,节点类型:Persistent)");
		zk.create("/zoo2", "myData2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("\n2.查看是否创建成功：");
		//添加Watcher
		System.out.println(new String(zk.getData("/zoo2", this.wh, null)));
		//这里再次进行修改,则不会触发Watcher时间,这就是验证zk的一个特性-一次触发,也就是说设置一次监视,只会对下次操作起一次作用
		System.out.println("\n3.再次修改节点数据:");
		zk.setData("/zoo2", "shanhy20160310-ABCD".getBytes(), -1);
		
		System.out.println("\n4.查看是否修改成功:");
		System.out.println(new String(zk.getData("/zoo2", false, null)));
		
		System.out.println("\n5.删除节点:");
		zk.delete("/zoo2", -1);
		
		System.out.println("\n6.查看节点是否被删除:");
		System.out.println("节点状态:["+zk.exists("/zoo2", false)+"]");
	}
	private void ZKClose() throws InterruptedException {
		zk.close();
	}
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		//创建一个与服务器连接的Zookeeer
		ConnectionTest ct = new ConnectionTest();
		ct.createZKInstance();
		ct.ZKOperation();
		ct.ZKClose();
	}
}
