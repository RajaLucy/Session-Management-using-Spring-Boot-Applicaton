package in.ashokit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.User;
import in.ashokit.repo.UserRepository;


@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	
	
	@Override
	public boolean saveUser(User u) {
	
		User findByUserEmail = userRepo.findByUserEmail(u.getUserEmail());
		if(findByUserEmail != null)
		{
			return false;
		}
		
		User save = userRepo.save(u);
		if(save.getUserId()!=null)
		{
			return true;
		}
		return false;
	}


	@Override
	public User loginUser(String email, String password) {
		User user = userRepo.findByUserEmailAndUserPassWord(email, password);
		
		return user;
	}


	@Override
	public String getName(Long id) {
		User user=null;
		Optional<User> findById = userRepo.findById(id);
		if(findById.isPresent())
		{
			 user = findById.get();
			
		}
		return user.getUserName();
	}


	@Override
	public void deleteByid(String id) {
		// TODO Auto-generated method stub
		
	}
	
}

