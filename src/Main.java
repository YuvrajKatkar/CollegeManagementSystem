import java.io.*;

public class Main {
    //List of remaining tasks
    //1. Test cases pending and debugging
    //2. Connecting with DB
    //3. Create a new DB for this project after connecting with DB

    static College c;
    static{
        try {
            FileInputStream fIn = new FileInputStream("abc.ser");
            ObjectInputStream in = new ObjectInputStream(fIn);

            c = (College) in.readObject();
            in.close();
            fIn.close();
            System.out.println("welcome to Student Management app");



        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(c==null) {
            c =  new College();
            System.out.println("new hotel");

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
        System.out.println("Hello");
        while(true){
            System.out.println("1. add Student 2. remove Student 3.display student 4.pay fees 5.Upgrade students grade 6.Update Student details");
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
                    System.exit(0);}
            }
        }
    }
}