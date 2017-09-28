package model;

//@Entity//声明当前类为hibernate映射到数据库中的实体类
//@Table(name = "ruyanfly_user")//声明在数据库中自动生成的表名为ruyanfly_user
public class User {
//    @Id//声明此列为主键
//    
//    //根据不同数据库自动选择合适的id生成方案,这里使用mysql,为递增型
////    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer userId;
    private String userName;

    public Integer getId() {
        return userId;
    }
    public void setId(Integer userId) {
        this.userId = userId;
    }
    public String getName() {
        return userName;
    }
    public void setName(String userName) {
        this.userName = userName;
    }
}
