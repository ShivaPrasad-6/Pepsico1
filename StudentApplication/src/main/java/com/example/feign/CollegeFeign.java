package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "college-data", url = "http://localhost:8097/College")
public interface CollegeFeign {
	@GetMapping("/collegeid/{collegeid}")
	public String getCollegename(@PathVariable(value = "collegeid") Integer collegeid);
}