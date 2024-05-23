package view;

import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import service.CourseService;
import service.CourseServiceImp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {
    private CourseService courseService = new CourseServiceImp();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("1. Add new Course");
            System.out.println("2. List Courses");
            System.out.println("3. Find Course BY ID");
            System.out.println("4. Find Course By Title");
            System.out.println("5. Remove Course By ID");
            System.out.println("0/99. Exit");
            System.out.print("[+] Insert option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    listCourses();
                    break;
                case 3:
                    findCourseById();
                    break;
                case 4:
                    findCourseByTitle();
                    break;
                case 5:
                    removeCourseById();
                    break;
                case 0:
                case 99:
                    System.exit(0);
                default:
                    System.out.println("[!] Invalid option. Please try again.");
            }
        }
    }

    private void addCourse() {
        try {
            System.out.print("[+] Insert course title: ");
            String title = scanner.nextLine();

            System.out.print("[+] Insert instructor names (comma-separated): ");
            String[] instructors = scanner.nextLine().split(",");

            System.out.print("[+] Insert course requirements (comma-separated): ");
            String[] requirements = scanner.nextLine().split(",");

            Date startDate = new Date();

            String id = String.valueOf((int) (Math.random() * 10000));
            Course course = new Course(id, title, instructors, requirements, startDate);
            courseService.addCourse(course);
            System.out.println("[*] Course added successfully.");
        } catch (Exception e) {
            System.out.println("[!] An unexpected error occurred: " + e.getMessage());
        }
    }

    private void listCourses() {
        List<Course> courses = courseService.listCourses();
        listCoursesResult(courses);
    }

    private void listCoursesResult(List<Course> courses) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("TITLE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("INSTRUCTOR", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("REQUIREMENT", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("START DATE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.setColumnWidth(0, 25, 40);
        table.setColumnWidth(1, 25, 40);
        table.setColumnWidth(2, 25, 40);
        table.setColumnWidth(3, 25, 40);
        table.setColumnWidth(4, 25, 40);

        for (Course course : courses) {
            String yellowColor = "\u001B[33m"; // Code for column color
            String resetColor = "\u001B[0m"; // Reset color
            table.addCell(yellowColor + course.getId() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getTitle() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + Arrays.toString(course.getInstructorNames()) + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + Arrays.toString(course.getRequirements()) + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getStartDate().toString() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }
        System.out.println(table.render());
    }

    private void findCourseById() {
        System.out.print("[+] Insert course ID: ");
        String id = scanner.nextLine();
        try {
            Course course = courseService.findCourseById(id);
            listCoursesResult(Arrays.asList(course));
        } catch (exception.CourseNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }


    private void findCourseByTitle() {
        System.out.print("[+] Insert Course Title: ");
        String title = scanner.nextLine();
        List<Course> courses = courseService.findCoursesByTitle(title);
        if (courses.isEmpty()) {
            System.out.println("[!] No courses found with title containing: " + title);
        } else {
            listCoursesResult(courses);
        }
    }

    private void removeCourseById() {
        System.out.print("[+] Insert course ID to remove: ");
        String id = scanner.nextLine();
        try {
            courseService.removeCourseById(id);
            System.out.println("[*] Course has been removed successfully.");
//            listCoursesResult(courses);
        } catch (exception.CourseNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }
}
