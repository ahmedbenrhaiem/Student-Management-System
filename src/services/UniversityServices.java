package services;

import Models.Grade;
import Models.Student;
import Models.Teacher;
import Models.University;

public class UniversityServices {
    University university;

    public UniversityServices(University university) {
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }
    public void addStudent(Student student){
        this.university.getStudents().add(student);
    }
    public void addTeacher(Teacher teacher){
        this.university.getTeachers().add(teacher);
    }
    public void addGrade(Grade grade){
        this.university.getGrades().add(grade);
    }
    public void removeStudent(Student student){
        this.university.getStudents().remove(student);
    }
    public void removeTeacher(Teacher teacher){
        this.university.getTeachers().remove(teacher);
    }
    public void removeGrade(Grade grade){
        this.university.getGrades().remove(grade);
    }
    public Student getStudentByName(String name){
        Student a = null;
        for (Student student : this.university.getStudents()){
            if (student.getName().toUpperCase().equals(name.toUpperCase())){
                a = student;
                break;
            }
        }
        return a;
    }
    public Teacher getTeacherByName(String name){
        for (Teacher teacher : this.university.getTeachers()){
            if (teacher.getName().toUpperCase().equals(name.toUpperCase())){
                return teacher;
            }
        }
        return null;
    }
    public Grade getGradeByCode(String code){
        for (Grade grade : this.university.getGrades()){
            if (grade.getCode().equals(code)){
                return grade;
            }
        }
        return null;
    }



}
