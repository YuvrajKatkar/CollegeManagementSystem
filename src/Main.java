import java.io.*;

public class Main {


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