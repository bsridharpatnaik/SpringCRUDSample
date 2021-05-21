package com.companyname.springbootcrudrest.controller;

import java.util.*;

import javax.validation.Valid;

import com.companyname.springbootcrudrest.DTO.CreateLeaveDTO;
import com.companyname.springbootcrudrest.model.LeaveDetails;
import com.companyname.springbootcrudrest.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.springbootcrudrest.exception.ResourceNotFoundException;
import com.companyname.springbootcrudrest.model.User;
import com.companyname.springbootcrudrest.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	LeaveRepository leaveRepository;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(
			@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(
			@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
		        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		
		user.setEmailId(userDetails.getEmailId());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		user.setUpdatedAt(new Date());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(
			@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
		        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PostMapping("/users/{id}/leave")
	public ResponseEntity<User> addLeaveForUser(@PathVariable(value = "id") Long userId,@RequestBody CreateLeaveDTO payload) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		//user = saveData(user,payload);
		LeaveDetails ld = new LeaveDetails();
		ld.setDays(payload.getDays());
		ld.setUser(user);
		leaveRepository.save(ld);

		// Set leave back to user
		Set<LeaveDetails> leaves = new HashSet<>();
		leaves = user.getUserLeaves();
		leaves.add(ld);
		user.setUserLeaves(leaves);
		System.out.println("Current Leaves - "+user.getLeaveBalance());
		user.setLeaveBalance(user.getLeaveBalance()-payload.getDays());
		userRepository.save(user);
		//return user;
		return ResponseEntity.ok(user);
	}
}
