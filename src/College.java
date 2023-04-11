import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class College implements Serializable {
    //addStudent, removeStudent,updateStudent, payFees,promoteStudenttoNextGrade - done
    //in update Student have a way to change branch - branch
    //Change name to multi-word string
    //each branch should also have different capacity so after filling that capacity students can't be added
    //makes paid fees as 0 every time year changes, as fees are yearly - done
    //class will be promoted only if fees for current year are paid3 - done
    //Write a logic avoid paying money if fees are already - done
    //Bug - Display if student is not found in each method -done
    //Add marks table in database and create logic to check whether a student is passed or failed
    //Generate prn automatically - done
    //write a logic to make the course of 4 years only - done
    //Check logic of file db for current year
    public List<Student> students = new ArrayList<>();
     long count;
    void addStudent(){

        System.out.println("Enter student name: ");
        String name = Student.sc.nextLine();
        name = Student.sc.nextLine();
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
    }
    void removeStudent(){
        System.out.println("Enter PRN of Student who is to be remove ");
        String PRN =  Student.sc.next();
        Iterator it = students.iterator();
        count--;
        Student s;
        while(it.hasNext()){
            s = (Student) it.next();
            if(s.PRN.equals(PRN)) {
                it.remove();
                try{
                    Connection con  = ConnectionWithDB.createCon();
                    Statement st = con.createStatement();
                    String query = "delete from College where PRN = '"+PRN+"';";
                    st.executeUpdate(query);
                    System.out.println(PRN+"'s details removed from the database");
                    st.close();
                    con.close();
                    return;
                }
                catch(Exception e){
                    e.printStackTrace();
                    System.out.println("Unable to add user to database but Student is removed from list");

                }
            }
        }
        System.out.println("User not found");
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
                System.out.println("Amount to be paid: "+ (float)remainingFees);
                if(remainingFees==0){
                    System.out.println("Fees are already paid, no need to pay again");
                    return;
                }
                System.out.println("Pay amount by \n1.Full Payment 2.Installments");
                switch (Student.sc.nextInt()){
                    case 1:{
                        System.out.println("Pay Entire amount :");
                        if(Student.sc.nextDouble()<remainingFees){
                            System.out.println("Insufficient amount paid, pay again");
                            return;
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
                    default:{
                        System.out.println("select appropriate option, try again");return;
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
                    System.out.println("Updated in database");
                    st.close();
                    con.close();
                    return;
                }
                catch(Exception e){
                    e.printStackTrace();
                    System.out.println("Unable to fetch student's fees from database but Student's money are stored in list ");
                }
            }
        }
        //s1 = students.get(index);
        System.out.println("Student not found");
    }
    void upgradeStudentYear(){
        System.out.println("Enter PRN of Student whose grade is to be upgrade ");
        String PRN =  Student.sc.next();
        Iterator it = students.iterator();
        Student s1; int index =0;
        while(it.hasNext()){
            Student s = (Student) it.next();
            index = students.indexOf(s);
            if(s.PRN.equals(PRN)) {
                if(s.currentYear<4){
                    if(s.paidFees>=s.totalFees){
                        s.currentYear++;
                        s.paidFees=0;
                        System.out.println(PRN+" is promoted to class"+s.currentYear);
                        s1 = students.get(index);
                        try{
                            Connection con  = ConnectionWithDB.createCon();
                            Statement st = con.createStatement();
                            String query = "update College" +
                                    " set paidFees = "+ s1.paidFees +"," +
                                    " currentYear = "+ s1.currentYear+
                                    " where PRN = '"+PRN +"';";
                            st.executeUpdate(query);
                            System.out.println("Updated in database");
                            //System.out.println(PRN+"'s total fees paid till date are: "+s1.paidFees);// change this line
                            st.close();
                            con.close();
                            return;
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            System.out.println("Unable to fetch student's fees from database but Student's money are stored in list ");
                        }
                    }
                    else{
                        System.out.println("First please pay complete fees for "+s.currentYear+"'s year");return;
                    }
                }
                else{
                    System.out.println("Engineering completed for "+PRN);return;
                }
            }
        }
        System.out.println("Student not found");
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
                    switch (Student.sc.nextInt()) {
                        case 1 -> {

                            System.out.println("Enter new name: ");
                            s.sName = Student.sc.next();
                            Connection con = ConnectionWithDB.createCon();
                            String query = "update College set sName = ? where PRN  = ?;";
                            PreparedStatement st = con.prepareStatement(query);
                            st.setString(1, s.sName);
                            st.setString(2, PRN);
                            st.executeUpdate();
                            System.out.println("Name updated successfully");
                            st.close();
                            con.close();
                            return;
                        }
                        case 2 -> {
                            System.out.println("Enter new year: ");
                            s.currentYear = Student.sc.nextByte();
                            Connection con = ConnectionWithDB.createCon();
                            String query = "update College set currentYear = ? where PRN  = ?;";
                            PreparedStatement st = con.prepareStatement(query);
                            st.setByte(1, s.currentYear);
                            st.setString(2, PRN);
                            st.executeUpdate();
                            System.out.println("Current Year updated successfully");
                            st.close();
                            con.close();
                            return;
                        }
                        case 3 -> {
                            System.out.println("Enter native place: ");
                            s.nativeCity = Student.sc.next();

                            Connection con = ConnectionWithDB.createCon();
                            String query = "update College set nativeCity = ? where PRN  = ?;";
                            PreparedStatement st = con.prepareStatement(query);
                            st.setString(1, s.nativeCity);
                            st.setString(2, PRN);
                            st.executeUpdate();
                            System.out.println("Native City updated successfully");
                            st.close();
                            con.close();
                            return;
                        }
                        case 4 -> {
                            System.out.println("Enter new Branch: ");
                            System.out.println("Enter following number to select branch \n1\tMechanical\n2\tCivil\n3\tIT\n4\tComputer Science");
                            String b1 = "";
                            switch (Student.sc.nextInt()) {
                                case 1: {
                                    s.setBranchAsMech();
                                    b1 = "Mechanical";
                                }
                                break;
                                case 2: {
                                    s.setBranchAsCivil();
                                    b1 = "Civil";
                                }
                                break;
                                case 3: {
                                    s.setBranchAsIt();
                                    b1 = "It";
                                }
                                break;
                                case 4: {
                                    s.setBranchAsCSE();
                                    b1 = "Computer Science Engineering";
                                }
                                break;

                            }
                            Connection con = ConnectionWithDB.createCon();
                            String query = "update College set branch = ? where PRN  = ?;";
                            PreparedStatement st = con.prepareStatement(query);
                            st.setString(1, b1);
                            st.setString(2, PRN);
                            st.executeUpdate();
                            System.out.println("Branch updated successfully");
                            st.close();
                            con.close();
                            return;
                        }
                    }
                }
                catch (Exception e){
                   e.printStackTrace();
                    System.out.println("Unable to connect to the database");
                }

            }
        }
        System.out.println("Student not found");
    }

}
