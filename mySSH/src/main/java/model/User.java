package model;

/**
 * 文中注释是hibernate的另一种映射方式通过注解
 * @author yanru02
 *
 */
//@Entity//声明当前类为hibernate映射到数据库中的实体类
//@Table(name = "ruyanfly_user")//声明在数据库中自动生成的表名为ruyanfly_user
public class User {
//    @Id//声明此列为主键
//    
//    //根据不同数据库自动选择合适的id生成方案,这里使用mysql,为递增型
////    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id;
    private String name;
    
    public User(){
    	
    }

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
    /**
     * 如果属性声明如下
     * private Integer userId;
     * private String userName;
     * 则方法名也需要重新修改如下才可以使用
     * 同理如果修改成theUserId, theUserName,方法名就要对应的修改成setTheUserId,
     * getTheUserId,setTheUserName,getTheUserName
     * @return
     */
//    public Integer getUserId() {
//        return userId;
//    }
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//    public String getUserName() {
//        return userName;
//    }
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
}
