package service;

import model.Course;
import java.util.List;

public interface CourseService {
    void addCourse(Course course);
    List<Course> listCourses();
    Course findCourseById(String id);
    List<Course> findCoursesByTitle(String title);
    void removeCourseById(String id);
}
