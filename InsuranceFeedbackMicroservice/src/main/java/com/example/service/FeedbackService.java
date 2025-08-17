package com.example.service;

import java.util.List;

import com.example.entity.Feedback;

public interface FeedbackService {
	public void addFeedback(Feedback feedback);
	public List<Feedback> showAll();
}
