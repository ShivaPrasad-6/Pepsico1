package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pojo.College;
import com.example.repository.CollegeRepository;


@Service
public class CollegeService  {
	
	
	
	@Autowired
	private CollegeRepository collegerepository;
	
	public Iterable<College> findAllColleges() {
		return collegerepository.findAll();
	}
	
	public College saveCollege( College college) {
		return collegerepository.save(college);
	}
	
	public Boolean deleteStudent(Integer collegeid) {
		Optional<College> college = collegerepository.findById(collegeid);
		collegerepository.delete(college.get());
		return true;
	}
	
	public Optional<College> findCollegeById(Integer collegeid)
	{
		Optional<College> college = collegerepository.findById(collegeid);
		return Optional.ofNullable(college.get());
	}

	
	
	/*
	 * public Optional<College> getCollege(Integer collegeid) {
	 * 
	 * return collegerepository.findById(collegeid);
	 * 
	 * }
	 */

	
	public String getCollegename(Integer collegeid) {
		Optional<College> college=collegerepository.findById(collegeid);
		College coll=college.get();
		return  coll.getCollegename();
	}
}