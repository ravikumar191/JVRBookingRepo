package com.jvr.booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvr.booking.model.User;
import com.jvr.booking.repo.UserRepository;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
    
 // Regex pattern for validating phone numbers
   // private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\+?[0-9]{10,13}$");

 
    public String registerUser(User user) {

        // Check if phone number already exists
        if (userRepository.existsByPhone(user.getPhone())) {
            return "Error: Phone number already in use!";
        }

        // Optional email check if provided
        if (user.getEmail() != null && userRepository.existsByEmail(user.getEmail())) {
            return "Error: Email already in use!";
        }

        // Check if password matches confirm password
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Error: Password and Confirm Password do not match!";
        }

        // Save user and address
        userRepository.save(user);
        return "User registered successfully!";
    }
    
	
	public User getUserById(String phone) {
		User user=null;
		Optional<User> optional=userRepository.findByPhone(phone);
		if(optional!=null)
		{
			user=optional.get();
		}
		return user;
	}
	public String getPhone(User user)
	{
		User userfetch=getUserById(user.getPhone());
		String username=userfetch.getPhone();
		return username;
	}
	public String getPassword(User user)
	{
		User userfetch=getUserById(user.getPhone());
		String password=userfetch.getPassword();
		return password;
	} 

}
