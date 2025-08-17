package com.example.controller;

import com.example.entity.Feedback;
import com.example.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTests {
    @InjectMocks
    private AppController appController;
    @Mock
    private FeedbackService feedbackService;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addFeedbackTest() throws URISyntaxException {
        Feedback feedback = Feedback.builder()
                .feedbackId(1L)
                .feedbackDate(new Date())
                .orderId(2L)
                .feedbackDescription("Great service")
                .build();
        doNothing().when(feedbackService).addFeedback(any(Feedback.class));
        ResponseEntity<String> response = appController.addFeedback(feedback);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        HttpHeaders headers = response.getHeaders();
        assertTrue(headers.containsKey("Order-Id"));
        assertEquals("Feedback created successfully", response.getBody());
    }


    @Test
    public void getFeedbackTest() {
        List<Feedback> feedbackList = Arrays.asList(
                Feedback.builder()
                        .feedbackId(1L)
                        .feedbackDate(new Date())
                        .orderId(2L)
                        .feedbackDescription("Great service")
                        .build(),
                Feedback.builder()
                        .feedbackId(3L)
                        .feedbackDate(new Date())
                        .orderId(4L)
                        .feedbackDescription("Excellent support")
                        .build()
        );
        when(feedbackService.showAll()).thenReturn(feedbackList);
        ResponseEntity<Object> response = appController.getFeedback();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        HttpHeaders headers = response.getHeaders();
        assertTrue(headers.isEmpty());
        assertEquals(feedbackList, response.getBody());
    }
}
