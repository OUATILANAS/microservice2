package com.mdq.springjwt.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mdq.springjwt.models.User;
import com.mdq.springjwt.repository.UserRepository;

import org.junit.jupiter.api.Test;

@SpringBootTest
class UserServiceTest {
	@MockBean
	 private UserRepository userRepository;
	@Autowired
	    private UserService userService;

	    @BeforeEach
	    public void setUp() {
	    	 MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testFindAll() {
	        // Mock the behavior of the userRepository.findAll() method
	        List<User> mockUsers = new ArrayList<>();
	        when(userRepository.findAll()).thenReturn(mockUsers);

	        // Call the service method
	        List<User> result = userService.findAll();

	        // Verify that the repository method was called and check the result
	        verify(userRepository, times(1)).findAll();
	        assertEquals(mockUsers, result);
	    }

	    @Test
	    public void testSave() {
	        // Mock the behavior of the userRepository.save() method
	        User mockUser = new User("John", "Doe", "john.doe", "john@example.com", "securePassword", "123456789");
	        when(userRepository.save(mockUser)).thenReturn(mockUser);

	        // Call the service method
	        User result = userService.save(mockUser);

	        // Verify that the repository method was called and check the result
	        verify(userRepository, times(1)).save(mockUser);
	        assertEquals(mockUser, result);
	    }

	    @Test
	    public void testFindByIdWhenUserExists() {
	        // Mock the behavior of the userRepository.findById() method
	        Long userId = 1L;
	        User mockUser = new User("John", "Doe", "john.doe", "john@example.com", "securePassword", "123456789");
	        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

	        // Call the service method
	        Optional<User> result = userService.findById(userId);

	        // Verify that the repository method was called and check the result
	        verify(userRepository, times(1)).findById(userId);
	        assertTrue(result.isPresent());
	        assertEquals(mockUser, result.get());
	    }

	    @Test
	    public void testFindByIdWhenUserDoesNotExist() {
	        // Mock the behavior of the userRepository.findById() method
	        Long userId = 1L;
	        when(userRepository.findById(userId)).thenReturn(Optional.empty());

	        // Call the service method
	        Optional<User> result = userService.findById(userId);

	        // Verify that the repository method was called and check the result
	        verify(userRepository, times(1)).findById(userId);
	        assertTrue(result.isEmpty());
	    }

}
