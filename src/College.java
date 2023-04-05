import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class College implements Serializable {
    //addStudent, removeStudent,updateStudent, payFees,promoteStudenttoNextGrade
    //in update Student have a way to change branch
    //Two users can have same name so, instead of showing details by using name, use prn
    //makes paid fees as 0 every time year changes, as fees are yearly
    //Write a logic avoid paying money if fees are already
    //Add marks table in database and create logic to check whether a student is passed or failed
    //write a logic to make the course of 4 years only
    //Check logic of file db for current year
    public List<Student> students = new ArrayList<>();
     long count;
    void addStudent(){

        System.out.println("Enter student name: ");
        String name = Student.sc.next();
        System.out.println();
        System.out.println("Enter native city name: ");
        String city = Student.sc.next();
        String PRN = "s"+count++;
        Student s1 = new Student(name,city,PRN);
        students.add(s1);
        try{
            Connection con  = ConnectionWithDB.createCon();
            Statement st = con.createStatement();
            String query = "insert into College (PRN,sName,currentYear,paidFees,totalFees,nativeCity,branch) values ('"
                    +PRN+"' ,'"+name+"' ,"+s1.currentYear+" ,"+s1.paidFees+" ,"
                    +s1.totalFees+" ,'"+s1.nativeCity+"' ,'"+s1.branch+"');";
            st.executeUpdate(query);
            System.out.println(name+"'s details added to the database with PRN Number as :"+PRN);
            st.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Unable to add user to database but Student add in list");
        }
    }
    void displayStudent(){
        System.out.println("Enter PRN of Student whose details are needed: ");
        String PRN =  Student.sc.next();
        Iterator it = students.iterator();
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.PRN.equals(PRN)){
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found");
        //no interaction with DB
    }
    void removeStudent(){
        System.out.println("Enter PRN of Student who is to be remove ");
        String PRN =  Student.sc.next();
        Iterator it = students.iterator();
        Student s;
        while(it.hasNext()){
            s = (Student) it.next();
            if(s.PRN.equals(PRN)) {
                it.remove();
            }
        }
        try{
            Connection con  = ConnectionWithDB.createCon();
            Statement st = con.createStatement();
            String query = "delete from College where PRN = '"+PRN+"';";
            st.executeUpdate(query);
            System.out.println(PRN+"'s details removed from the database");
            st.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Unable to add user to database but Student is removed from list");
        }
    }
    void payFees(){
        System.out.println("Enter PRN of Student whose fees is to be paid ");
        String PRN =  Student.sc.next();
        Iterator it = students.iterator();
        Student s1; int index = 0;
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.PRN.equals(PRN)) {
                index = students.indexOf(s);
                double remainingFees = s.totalFees - s.paidFees;
                System.out.println("Total fees for your Branch : "+s.totalFees);
                System.out.println("Already paid fees for "+PRN+" :"+s.paidFees);
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
                        s.paidFees = s.paidFees+ Student.sc.nextDouble();
                        System.out.println("Amount received");
                    }
                    break;
                }
            }
        }
        s1 = students.get(index);
        try{
            Connection con  = ConnectionWithDB.createCon();
            Statement st = con.createStatement();
            String query = "update College" +
                    " set paidFees = "+ s1.paidFees +
                    " where PRN = '"+PRN +"';";


            st.executeUpdate(query);
            System.out.println(PRN+"'s total fees paid till date are: "+s1.paidFees);// change this line
            st.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Unable to fetch student's fees from database but Student's money are stored in list ");
        }

    }
    void upgradeStudentYear(){
        System.out.println("Enter PRN of Student whose grade is to be upgrade ");
        String PRN =  Student.sc.next();
        Iterator it = students.iterator();
        Student s1; int index =0;
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.PRN.equals(PRN)) {
                s.currentYear++;
                s.paidFees=0;
                System.out.println(PRN+" is promoted to class"+s.currentYear);
            }
        }
        s1 = students.get(index);
        try{
            Connection con  = ConnectionWithDB.createCon();
            Statement st = con.createStatement();
            String query = "update College" +
                    " set paidFees = "+ s1.paidFees +"," +
                    " currentYear = "+ s1.currentYear+
                    " where PRN = '"+PRN +"';";
            st.executeUpdate(query);
            System.out.println(PRN+"'s total fees paid till date are: "+s1.paidFees);// change this line
            st.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Unable to fetch student's fees from database but Student's money are stored in list ");
        }
    }
    void updateStudentDetails(){
        System.out.println("Enter PRN of students whose details is to be updated");
        String PRN =  Student.sc.next();
        Iterator it = students.iterator();
        while(it.hasNext()){
            Student s = (Student) it.next();
            if(s.PRN.equals(PRN)) {
                try {
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
                                case 1:{
                                    s.setBranchAsMech();
//
                                }break;
                                case 2:{
                                    s.setBranchAsCivil();
//
                                }break;
                                case 3:{
                                    s.setBranchAsIt();
//
                                }break;
                                case 4:{
                                    s.setBranchAsCSE();
//
                                }break;

                            }
                        }
                    }
//
                }
                catch (Exception e){
                   e.printStackTrace();
                    System.out.println("Unable to connect to the database");
                }

            }
        }
    }

}
