package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IProfessorRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IFilteringService;

@Service
public class FilteringServiceImpl implements IFilteringService{

	@Autowired
	private IGradeRepo gradeRepo;
	
	@Autowired
	private IProfessorRepo profRepo;

	@Autowired
	private ICourseRepo courseRepo;
	
	@Autowired
	private IStudentRepo studentRepo;
	
	@Override
	public ArrayList<Grade> selectFailedGradesInSystem() throws Exception {
		ArrayList<Grade> result = gradeRepo.findByGrvalueLessThan(4);
		
		if(result.isEmpty()) throw new Exception("There is no failed grade in the system");
		
		return result;
	}

	@Override
	public ArrayList<Grade> selectGradesByStudentId(long id) throws Exception {
		if(id < 1) throw new Exception("Id should be positive");
		
		if(!studentRepo.existsById(id)) 
			throw new Exception("Student with id (" + id + ") is not in the system");
		
		ArrayList<Grade> result = gradeRepo.findByStudentIds(id);
		
		if(result.isEmpty()) throw new Exception("Student with id (" + id + ") has not any grade");

		return result;
	}
	
	
	@Override
	public ArrayList<Course> selectCoursesByProfessorId(long id) throws Exception {
if(id < 1) throw new Exception("Id should be positive");
		
		if(!profRepo.existsById(id)) 
			throw new Exception("Professor with id (" + id + ") is not in the system");
		
		ArrayList<Course> result = courseRepo.findByProfessorIdp(id);
		
		if(result.isEmpty()) throw new Exception("Professor with id (" + id + ") has not any course");

		return result;
	}

	@Override
	public ArrayList<Course> selectCoursesByStudentId(long id) throws Exception {
		if(id < 1) throw new Exception("Id should be positive");

		if(!studentRepo.existsById(id)) 
			throw new Exception("Student with id (" + id + ") is not in the system");

		ArrayList<Course> result = courseRepo.findByGradesStudentIds(id);

		if(result.isEmpty()) throw new Exception("Student with id (" + id + ") has not any course");

		return result;
	}



	@Override
	public float calculateAVGGradeInCourseId(long id) throws Exception {
		if(id < 1) throw new Exception("Id should be positive");

		if(!courseRepo.existsById(id)) 
			throw new Exception("Course with id (" + id + ") is not in the system");

		float result = gradeRepo.calculateAVGGrade_MyFunction(id);

		if(result == 0) throw new Exception("There is no grade in this course");

		return result;
	}

}