package com.mdq.springjwt.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.mdq.springjwt.models.Comment;
import com.mdq.springjwt.models.Story;
import com.mdq.springjwt.repository.CommentRepository;
import com.mdq.springjwt.repository.StoryRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SpringBootTest
class CommentServiceTest {
	@MockBean
	 private CommentRepository commentRepository;
	@MockBean
	    private StoryRepository storyService;
	@Autowired
	    private CommentService commentService;

	    @BeforeEach
	    public void setUp() {
	    	 MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testFindAll() {
	        // Mock the behavior of the commentRepository.findAll() method
	        List<Comment> mockComments = new ArrayList<>();
	        when(commentRepository.findAll()).thenReturn(mockComments);

	        // Call the service method
	        List<Comment> result = commentService.findAll();

	        // Verify that the repository method was called and check the result
	        verify(commentRepository, times(1)).findAll();
	        assertEquals(mockComments, result);
	    }

	    @Test
	    public void testSave() {
	        // Mock the behavior of the commentRepository.save() method
	    	// Creating a Comment object with specific values
	    	Story story =  new Story(1L, "Sample Story", "This is the content of the story", "image1.jpg", "image2.jpg", "image3.jpg", "City Name", "JohnDoe");
	        Comment mockComment =new Comment(1L, "This is a comment text", "John Doe", story);
	        when(commentRepository.save(mockComment)).thenReturn(mockComment);

	        // Call the service method 
	        Comment result = commentService.save(mockComment);

	        // Verify that the repository method was called and check the result
	        verify(commentRepository, times(1)).save(mockComment);
	        assertEquals(mockComment, result);
	    }


	    @Test
	    public void testFindCommentsByStoryIdWhenStoryExists() {
	        // Mock the behavior of the storyService.findById() method
	        Long storyId = 1L;
	        Story mockStory = new Story(1L, "Sample Story", "This is the content of the story", "image1.jpg", "image2.jpg", "image3.jpg", "City Name","JohnDoe");
	        when(storyService.findById(storyId)).thenReturn(Optional.of(mockStory));

	        // Mock the behavior of the commentRepository.findByStory() method
	        List<Comment> mockComments = new ArrayList<>();
	        when(commentRepository.findByStory(mockStory)).thenReturn(mockComments);

	        // Call the service method
	        List<Comment> result = commentService.findCommentsByStoryId(storyId);

	        // Verify that the repository methods were called and check the result
	        verify(storyService, times(1)).findById(storyId);
	        verify(commentRepository, times(1)).findByStory(mockStory);
	        assertEquals(mockComments, result);
	    }

	    @Test
	    public void testFindCommentsByStoryIdWhenStoryDoesNotExist() {
	        // Mock the behavior of the storyService.findById() method
	        Long storyId = 1L;
	        when(storyService.findById(storyId)).thenReturn(Optional.empty());

	        // Call the service method
	        List<Comment> result = commentService.findCommentsByStoryId(storyId);

	        // Verify that the repository methods were called and check the result
	        verify(storyService, times(1)).findById(storyId);
	        assertTrue(result.isEmpty());
	    }

}
