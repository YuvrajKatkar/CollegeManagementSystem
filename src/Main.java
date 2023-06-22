import java.io.*;

public class Main {
    //# CollegeManagementSystem
    //College Management System is standalone console based application which uses the pillars of Object Oriented Programming, serialization to store the state of objects and MySQL as database to store the records using JDBC.
    //Features of this College Mangement System:
    //1. College has four Branches
    //2. We can add a student in any one of the branch
    //3. Every student will be assigned a unique PRN(Permanent Registration Number) automatically by the application, which will used to access the student
    //4. Every branch has different fees
    //5. Before promoting any student to next year, the application ensures that the fees for current year is completely paid
    //6. We can add student, remove student, check details of any student, promote student to next year, update/change student details
    //7. Every time any changes are made in the application, changes are automatically updated in the database using JDBC

    static College c;
    static{
        try {
            FileInputStream fIn = new FileInputStream("abc.ser");
            ObjectInputStream in = new ObjectInputStream(fIn);

            c = (College) in.readObject();

            in.close();
            fIn.close();
            System.out.println("Welcome to Student Management app");



        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(c==null) {
            c =  new College();
            System.out.println("new College");

        }
    }
    static void saveChanges(){
        try {
            FileOutputStream fileOut = new FileOutputStream("abc.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(c);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        while(true){
            System.out.println("_____________________________________");

            System.out.println("1. Add Student \n2. Remove Student \n3. Display student \n4. Pay fees \n5. Upgrade students grade \n6. Update Student details");
            System.out.println("Press 100  to exit");
            switch (Student.sc.nextInt()){
                case 1: c.addStudent();saveChanges();break;
                case 2: c.removeStudent();saveChanges();break;
                case 3: c.displayStudent();saveChanges();break;
                case 4: c.payFees();saveChanges();break;
                case 5: c.upgradeStudentYear();saveChanges();break;
                case 6: c.updateStudentDetails();saveChanges();break;
                default: {
                    //save code
                    saveChanges();
                    System.out.println("Application closed");
                    System.exit(0);}
            }
        }
    }
}