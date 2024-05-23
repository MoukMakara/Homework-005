package repository;

import model.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course getCourseById(String id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new exception.CourseNotFoundException("Course with ID " + id + " not found"));
    }

    public List<Course> getCoursesByTitle(String title) {
        return courses.stream()
                .filter(course -> course.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    public void removeCourseById(String id) {
        Course course = getCourseById(id);
        courses.remove(course);
    }
}
