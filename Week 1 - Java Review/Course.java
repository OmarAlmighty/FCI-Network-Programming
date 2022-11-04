package com.review;

public class Course {
    private String courseName;
    private String[] students = new String[100];
    private int numOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
        for (int i = 0; i < students.length; i++) {
            students[i] = "";
        }
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void addStudent(String sname) {
        students[numOfStudents] = sname;
        numOfStudents++;
    }

    public void dropStudent(String sname) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(sname)) {
                students[i] = "";
                System.out.println("Student removed");
                break;
            }
            System.out.println("Student not found");
        }
    }

    public String[] getStudents() {
        return students;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }
}

class StudentMain {
    public static void main(String[] args) {
        Course course1 = new Course("Data Structures");
        Course course2 = new Course("Database Systems");
        course1.addStudent("Peter Jones");
        course1.addStudent("Kim Smith");
        course1.addStudent("Anne Kennedy");

        course2.addStudent("Peter Jones");
        course2.addStudent("Steve Smith");

        System.out.println("Number of students in course1: "
                + course1.getNumOfStudents());
        String[] students = course1.getStudents();
        for (int i = 0; i < course1.getNumOfStudents(); i++)
            System.out.print(students[i] + ", ");
        System.out.println();
        System.out.print("Number of students in course2: "
                + course2.getNumOfStudents());
    }
}