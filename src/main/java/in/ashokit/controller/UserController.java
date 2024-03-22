package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.bindings.LoginUser;
import in.ashokit.entity.User;

import in.ashokit.service.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImp userSer;

	//login form loading 
	@GetMapping("/")
	public String LoginForm(Model model)
	{
		model.addAttribute("Login", new LoginUser());
		return "LoginForm";
		
	}
	
	//Register Page Loading
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
	    model.addAttribute("User", new User());
	    return "Register";
	}

	
	
	@PostMapping("/registerUser")
	public String RegisterUser(Model model,@ModelAttribute("User") User u)
	{
		boolean saveUser = userSer.saveUser(u);
		if(saveUser)
		{
			model.addAttribute("Suc", "Register Success");
		}
		else {
			model.addAttribute("Fail", "Given mail id is exists Try Another");
		}
		
		return "Register";
		
	}
	
	
	@PostMapping("/loginUser")
	public String LoginUser(Model model,@ModelAttribute("Login")LoginUser l,HttpServletRequest req)
	{
		User loginUser = userSer.loginUser(l.getUserEmail(), l.getUserPassword());
		
		if(loginUser == null)
		{
			model.addAttribute("invalid", "invalid Crediatials");
			return "LoginForm";
		}
		
		
		HttpSession session = req.getSession(true);
		session.setAttribute("UID", loginUser.getUserId());
		   
			return "redirect:/dashboard";
		
		
	}
	
	@GetMapping("/dashboard")
	public String DashBoard(Model model,HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		Object attribute = session.getAttribute("UID");
		Long id=(Long)attribute;
		String name = userSer.getName(id);
		model.addAttribute("My", "Hii "+name+" wirte your notes");
		return "DashBoardView";
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
}
