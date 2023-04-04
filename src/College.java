import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class College implements Serializable {
    //addStudent, removeStudent,updateStudent, payFees,promoteStudenttoNextGrade
    //in update Student have a way to change branch
    List<Student> students = new ArrayList<>();
    static long count;
    void addStudent(){

        System.out.println("Enter student name: ");
        String name = Student.sc.next();
        System.out.println();
        System.out.println("Enter native city name: ");
        String city = Student.sc.next();
        String PRN = "s"+count++;
        Student s1 = new Student(name,city,PRN);
        students.add(s1);
        //insert row in DB
    }
    void displayStudent(){
        System.out.println("Enter name of Student whose details are needed: ");
        String name =  Student.sc.next();
        Iterator it = students.iterator();
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.sName.equals(name)) {
                System.out.println(s);
            }
        }
        //no interaction with DB
    }
    void removeStudent(){
        System.out.println("Enter name of Student who is to be remove ");
        String name =  Student.sc.next();
        Iterator it = students.iterator();
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.sName.equals(name)) {
                it.remove();
            }
        }
        //Delete a row
    }
    void payFees(){
        System.out.println("Enter name of Student whose fees is to be paid ");
        String name =  Student.sc.next();
        Iterator it = students.iterator();
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.sName.equals(name)) {
                double remainingFees = s.totalFees - s.paidFees;
                System.out.println("Total fees for your Branch : "+s.totalFees);
                System.out.println("Already paid fees for "+name+" :"+s.paidFees);
                System.out.println("Amount to be paid: "+ remainingFees);
                if(remainingFees==0){
                    System.out.println("Fees are already paid");
                    return;
                }
                System.out.println("Pay amount by \n1.Full Payment 2.Installments");
                switch (Student.sc.nextInt()){
                    case 1:{
                        System.out.println("Pay Entire amount :");
                        if(Student.sc.nextDouble()<remainingFees){
                            System.out.println("Insufficient amount paid, pay again");
                        }
                        else{
                            s.paidFees = s.totalFees;
                            System.out.println("Amount received, payment done");
                        }
                    }break;
                    case 2:{
                        System.out.println("Enter amount you want to pay: ");
                        s.paidFees = Student.sc.nextDouble();
                        System.out.println("Amount received");
                    }
                    break;
                }
            }
        }
        //update paidfess and total for the particular student in DB
    }
    void upgradeStudentYear(){
        System.out.println("Enter name of Student whose grade is to be upgrade ");
        String name =  Student.sc.next();
        Iterator it = students.iterator();
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.sName.equals(name)) {
                s.currentYear++;
                System.out.println(name+" is promoted to class"+s.currentYear);
            }
        }
        //update only current year for the particular student in DB
    }
    void updateStudentDetails(){
        System.out.println("Enter name of students whose details is to be updated");
        String name =  Student.sc.next();
        Iterator it = students.iterator();
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.sName.equals(name)) {
                System.out.println("Select which attribute to change of student");
                System.out.println("1.Name 2.Current Year 3.Native Place 4.Branch");
                //Find a logic to update in database in a optimized way/more code reuse ability
                switch (Student.sc.nextInt()){
                    case 1:{
                        System.out.println("Enter new name: ");
                        s.sName = Student.sc.next();
                    }break;
                    case 2:{
                        System.out.println("Enter new year: ");
                        s.currentYear = Student.sc.nextByte();
                    }break;
                    case 3:{
                        System.out.println("Enter native place: ");
                        s.nativeCity = Student.sc.next();
                    }break;
                    case 4:{
                        System.out.println("Enter new Branch: ");
                        System.out.println("Enter following number to select branch \n1\tMechanical\n2\tCivil\n3\tIT\n4\tComputer Science");
                        switch (Student.sc.nextInt()){
                            case 1: s.setBranchAsMech();break;
                            case 2: s.setBranchAsCivil();break;
                            case 3: s.setBranchAsIt();break;
                            case 4: s.setBranchAsCSE();break;
                        }
                    }
                }
            }
        }
    }

}
