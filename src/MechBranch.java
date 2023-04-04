public class MechBranch implements Branch{
    String bName = "Mechanical";
    double fees = 94434.35;
    public String toString(){
        return bName;
    }

    @Override
    public double addBranch() {
        //Add logic to assign that branch to student
        System.out.println("Branch selected as "+bName);
        return fees;
    }
    static MechBranch m1 = new MechBranch();
}
