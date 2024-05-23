package service;

import model.Course;
import repository.CourseRepository;

import java.util.List;

public class CourseServiceImp implements CourseService {
    private CourseRepository repository = new CourseRepository();

    @Override
    public void addCourse(Course course) {
        repository.addCourse(course);
    }

    @Override
    public List<Course> listCourses() {
        return repository.getAllCourses();
    }

    @Override
    public Course findCourseById(String id) {
        return repository.getCourseById(id);
    }

    @Override
    public List<Course> findCoursesByTitle(String title) {
        return repository.getCoursesByTitle(title);
    }

    @Override
    public void removeCourseById(String id) {
        repository.removeCourseById(id);
    }
}
