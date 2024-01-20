package com.mdq.springjwt.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mdq.springjwt.models.Story;
import com.mdq.springjwt.repository.StoryRepository;

@SpringBootTest
class StoryServiceTest {

	 	@MockBean
	    private StoryRepository storyRepository;

	    @Autowired
	    private StoryService storyService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testFindAll() {
	        // Mock the behavior of the storyRepository.findAll() method
	        List<Story> mockStories = new ArrayList<>();
	        when(storyRepository.findAll()).thenReturn(mockStories);

	        // Call the service method
	        List<Story> result = storyService.findAll();

	        // Verify that the repository method was called and check the result
	        verify(storyRepository, times(1)).findAll();
	        assertEquals(mockStories, result);
	    }

	    @Test
	    public void testSave() {
	        // Mock the behavior of the storyRepository.save() method
	        Story mockStory = new Story(1L, "Sample Story", "This is the content of the story", "image1.jpg", "image2.jpg", "image3.jpg", "City Name", "JohnDoe");
	        when(storyRepository.save(mockStory)).thenReturn(mockStory);

	        // Call the service method
	        Story result = storyService.save(mockStory);

	        // Verify that the repository method was called and check the result
	        verify(storyRepository, times(1)).save(mockStory);
	        assertEquals(mockStory, result);
	    }

	   

	    @Test
	    public void testFindLastStoryWhenListNotEmpty() {
	        // Mock the behavior of the storyRepository.findAll() method
	        List<Story> mockStories = new ArrayList<>();
	        mockStories.add(new Story(1L, "Sample Story", "This is the content of the story", "image1.jpg", "image2.jpg", "image3.jpg", "City Name", "JohnDoe"));
	        when(storyRepository.findAll()).thenReturn(mockStories);

	        // Call the service method
	        Story result = storyService.findLastStory();

	        // Verify that the repository method was called and check the result
	        verify(storyRepository, times(1)).findAll();
	        assertEquals(mockStories.get(0), result);
	    }

	    @Test
	    public void testFindLastStoryWhenListEmpty() {
	        // Mock the behavior of the storyRepository.findAll() method
	        when(storyRepository.findAll()).thenReturn(new ArrayList<>());

	        // Call the service method
	        Story result = storyService.findLastStory();

	        // Verify that the repository method was called and check the result
	        verify(storyRepository, times(1)).findAll();
	        assertNull(result);
	    }
}
