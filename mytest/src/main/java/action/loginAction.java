package action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * action方法有如下三种创建方式
 * 1public class AnotherLoginAction implements Action
 * 2 public class loginAction extends ActionSupport
 * @author yanru02
 *
 */
public class loginAction {
	
	private String userName;
	private String passWord;
	
	public String getUserName(){
		return userName;
	}
	
	public String getPassWord(){
		return passWord;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setPassWord(String passWord){
		this.passWord = passWord;
	}
	
//	public String execute() throws Exception{
//		return "success";
//	}
	
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String username = request.getParameter("userName");
			String password = request.getParameter("passWord");
			System.out.println("name-> " + username + ", password->" + password);
			if ("admin".equals(username) && "123456".equals(password)){
				return "success";
			}else{
				return "error";
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	public String test(){
		System.out.println("this is at test!");
		return "error";
	}

}
