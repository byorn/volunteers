package com.desilvas.volunteerapp.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.desilvas.volunteerapp.domain.Category;

public interface CommonDAO {

	
	@Cacheable({"categories"})
	public List<Category> getCategories();
	
}
