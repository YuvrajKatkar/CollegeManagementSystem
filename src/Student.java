import java.io.Serializable;
import java.util.Scanner;

public class Student implements Serializable {
    String sName;
    byte currentYear=1;
    String PRN;
    double paidFees ;
    double totalFees;
    String nativeCity;
    Branch branch;

    @Override
    public String toString() {
        return "\nName: \t\t\t"+sName+"\nNative Place: \t"+nativeCity+"\nBranch: \t\t"+branch+
                "\nYear: \t\t\t"+currentYear+"\nPRN: \t\t\t"+PRN+
                "\nPaid fees: \t\t"+paidFees +"\nTotal fees: \t"+totalFees;
    }


    static Scanner sc = new Scanner(System.in);
    Student(String sName,String nativeCity,String PRN){
        this.sName = sName;
        this.nativeCity = nativeCity;
        this.PRN = PRN;
        System.out.println("Enter following number to select branch \n1\tMechanical\n2\tCivil\n3\tIT\n4\tComputer Science");
        switch (sc.nextInt()){
            case 1: setBranchAsMech();break;
            case 2: setBranchAsCivil();break;
            case 3: setBranchAsIt();break;
            case 4: setBranchAsCSE();break;
        }
    }
    void setBranchAsMech(){
        //this.branch = new MechBranch();
        // creates object of mechanical everytime a student is added so not good
        //try to make this as static and share
        this.branch = MechBranch.m1;
        this.totalFees =  branch.addBranch();
    }
    void setBranchAsCivil(){
        this.branch =  CivilBranch.c1 ;
        this.totalFees = branch.addBranch();
    }
    void setBranchAsIt(){
        this.branch =  ItBranch.i1;
        this.totalFees = branch.addBranch();
    }
    void setBranchAsCSE(){
        this.branch =  CseBranch.c2 ;
        this.totalFees = branch.addBranch();
    }

}
