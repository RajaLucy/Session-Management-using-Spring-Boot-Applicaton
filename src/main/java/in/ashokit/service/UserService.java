package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.User;

public interface UserService {
	
	
	public boolean saveUser(User u);
	
    public User loginUser(String email,String password);
    
    
    public String getName(Long id);
    
    
   
    
    
    public void deleteByid(String id);

}
