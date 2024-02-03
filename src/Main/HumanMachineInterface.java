package Main;

import Models.Person;
import Models.Student;
import Models.Teacher;
import Models.University;
import services.UniversityServices;
import tools.Action;

import java.util.Scanner;

public class HumanMachineInterface {
    private static UniversityServices universityServices;

    public static void start(){
        Scanner scanner = new Scanner(System.in);
        University university = HumanMachineInterface.createUniversity();

        System.out.println("Welcome to " + university.getName() + " in " + university.getAddress());
        String action;
        do {
            System.out.println("What do you want to do ?");
            action = scanner.next();

            switch (action){
                case "addStudent":
                    HumanMachineInterface.addStudent();
                    break;
                case "addTeacher":
                    HumanMachineInterface.addTeacher();
                    break;
                case "printAllStudents":
                    HumanMachineInterface.printAllStudents();
                    break;
                case "printAllTeachers":
                    HumanMachineInterface.printAllTeachers();
                    break;
                case "findStudentByName":
                    HumanMachineInterface.printDataPersonByName(Action.STUDENT);
                    break;
                case "findTeacherByName":
                    HumanMachineInterface.printDataPersonByName(Action.TEACHER);
                    break;
                case "removeStudent":
                    HumanMachineInterface.removePerson(Action.STUDENT);
                    break;
                case "removeTeacher":
                    HumanMachineInterface.removePerson(Action.TEACHER);
                    break;
            }

        } while (!action.equals("exit"));
    }
    private static University createUniversity(){
        University university = new University();
        university.setName("Wroclaw university");
        university.setAddress("Poland");
        university.setPhoneNumber("12365220220");
        HumanMachineInterface.universityServices = new UniversityServices(university);
        return university;
    }

    private static void addStudent(){
        Student student = new Student();
        HumanMachineInterface.setDataPerson(student);
        HumanMachineInterface.universityServices.addStudent(student);
    }
    private static void addTeacher(){
        Teacher teacher = new Teacher();
        HumanMachineInterface.setDataPerson(teacher);
        HumanMachineInterface.universityServices.addTeacher(teacher);
    }

    public static void printAllStudents(){
        for (Student student : HumanMachineInterface.universityServices.getUniversity().getStudents()){
            System.out.println(student.getData());
        }
    }
    public static void printAllTeachers(){
        for (Teacher teacher : HumanMachineInterface.universityServices.getUniversity().getTeachers()){
            System.out.println(teacher.getData());
        }
    }
    private static void setDataPerson(Person p){
        Scanner reader = new Scanner(System.in);

        System.out.println("Please enter the name");
        p.setName(reader.next());
        System.out.println("Please enter the age");
        p.setAge(reader.nextInt());


        if (p instanceof Teacher){
            System.out.println("Please enter the salary");
            ((Teacher) p).setSalary(reader.nextFloat());
        }
        else if (p instanceof Student){
            System.out.println("Please enter the phone number");
            ((Student) p).setParentPhoneNumber(reader.next());

        }

    }
    private static void printDataPersonByName(Action action){
        Person p = HumanMachineInterface.findPersonByName(action);
        HumanMachineInterface.printDataPerson(p);
    }
    private static void printDataPerson(Person person){
        if(person == null){
            System.out.println("Not Found !!");
        }
        else {
            System.out.println(person.getData());
        }
    }
    private static Person findPersonByName(Action action){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the name");
        String name = reader.next();

        Person p;
        if (action == Action.STUDENT){
            p = HumanMachineInterface.universityServices.getStudentByName(name);
        } else {
            p = HumanMachineInterface.universityServices.getTeacherByName(name);
        }
        return p;
    }
    private static void removePerson(Action action){

        Person p = HumanMachineInterface.findPersonByName(action);
        if (p instanceof Student){
            HumanMachineInterface.universityServices.removeStudent((Student) p);
        } else {
            HumanMachineInterface.universityServices.removeTeacher((Teacher) p);
        }
    }

}
