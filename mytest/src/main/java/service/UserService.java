package service;

import beans.User;
import dao.UserDao;

public class UserService {
	
	static UserDao test = new UserDao();
	
    public boolean valid(int id, String password) {        
        User user = test.selectUser(id);
        if(user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        UserService service = new UserService();
        test.addUser("ruyanfly", "123456");
        boolean login = service.valid(1, "admin");
        System.out.println("验证结果："+login);
    }
}
