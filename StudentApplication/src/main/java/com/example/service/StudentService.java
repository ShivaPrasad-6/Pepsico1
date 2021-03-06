package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.example.feign.CollegeFeign;
//import com.example.pojo.College;
import com.example.pojo.Student;
import com.example.repository.StudentRepository;
import com.example.studentbo.Studentbo;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentrepository;

	@Autowired
	CollegeFeign collegeFeign;

	public Iterable<Student> findAllStudents() {

		Iterable<Student> Itstudent = studentrepository.findAll();
		Itstudent.forEach(data -> {
			if (data.getGender().equals("M")) {
				data.setName("Mr." + data.getName());
			}
			else{
				data.setName("Ms." + data.getName());
			}
		});

		return Itstudent;
	}

	public Student saveStudent(Student student) {
		return studentrepository.save(student);
	}

	public Boolean deleteStudent(Integer id) {
		Optional<Student> student = studentrepository.findById(id);
		studentrepository.delete(student.get());
		return true;
	}

	/*
	 * @Autowired private RestTemplate restTemplate;
	 */

	public Optional<Student> findStudentById(Integer id) {

		Optional<Student> student = studentrepository.findById(id);

		/*
		 * College college = restTemplate.getForObject( "http://localhost:8097/College",
		 * College.class); Optional<College> clg = college.findById(college.get
		 * collegeid());
		 */

		return Optional.ofNullable(student.get());
	}
	

	public Studentbo findOneInAll3(Integer id) {
		Optional<Student> student = studentrepository.findById(id);
		Student stud = student.get();

		Studentbo bo = new Studentbo();

		bo.setName(stud.getName());
		bo.setClgid(stud.getClgid());
		bo.setAge(stud.getAge());
		bo.setGender(stud.getGender());
		bo.setId(stud.getId());
		bo.setCollegename(collegeFeign.getCollegename(stud.getClgid()));
		return bo;
	}

}