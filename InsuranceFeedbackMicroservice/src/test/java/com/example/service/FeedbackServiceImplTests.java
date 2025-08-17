package com.example.service;

import com.example.entity.Feedback;
import com.example.repo.FeedbackRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FeedbackServiceImplTests {
    @InjectMocks
    private FeedbackServiceImpl feedbackService;
    @Mock
    private FeedbackRepo feedbackRepo;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFeedback() {
        Feedback feedback = Feedback.builder()
                .feedbackId(1L)
                .feedbackDate(new Date())
                .orderId(2L)
                .feedbackDescription("Awesome")
                .build();
        feedbackService.addFeedback(feedback);
        verify(feedbackRepo, times(1)).save(feedback);
    }


    @Test
    public void testShowAll() {
        List<Feedback> feedbackList = Arrays.asList(
                Feedback.builder()
                        .feedbackId(1L)
                        .feedbackDate(new Date())
                        .orderId(2L)
                        .feedbackDescription("Awesome")
                        .build(),
                Feedback.builder()
                        .feedbackId(3L)
                        .feedbackDate(new Date())
                        .orderId(4L)
                        .feedbackDescription("Super se bhi upar")
                        .build()
        );
        when(feedbackRepo.findAll()).thenReturn(feedbackList);
        List<Feedback> result = feedbackService.showAll();
        assertNotNull(result);
        assertEquals(feedbackList, result);
    }
}
