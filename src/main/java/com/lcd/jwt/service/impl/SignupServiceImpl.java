package com.lcd.jwt.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcd.jwt.constant.ERole;
import com.lcd.jwt.entity.Role;
import com.lcd.jwt.entity.User;
import com.lcd.jwt.repository.RoleRepository;
import com.lcd.jwt.repository.UserRepository;
import com.lcd.jwt.rq.SignupRequest;
import com.lcd.jwt.rsp.MessageResponse;
import com.lcd.jwt.service.SignupService;

@Service
@Transactional(readOnly = true)
public class SignupServiceImpl implements SignupService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object signup(SignupRequest request) {
		
		if (userRepository.existsByEmail(request.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		
		}
		
		if (userRepository.existsByUsername(request.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		User user = new User(request.getUsername(), 
				 request.getEmail(),
				 encoder.encode(request.getPassword()));
		
		Set<String> srtRoles = request.getRoles();
		Set<Role> roles = new HashSet<Role>();
		

		if (srtRoles == null) {
			Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			srtRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByRole(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByRole(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return new MessageResponse("User registered successfully!");
	}

	
}
