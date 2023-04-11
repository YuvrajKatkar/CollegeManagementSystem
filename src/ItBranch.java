public class ItBranch implements Branch{
    String bName = "Information Technology";
    double fees = 102123.5;
    public String toString(){
        return bName;
    }

    @Override
    public double addBranch() {
        //Add logic to assign that branch to student
        System.out.println("Branch selected as "+bName);
        return fees;
    }
    static ItBranch i1 = new ItBranch();
}
