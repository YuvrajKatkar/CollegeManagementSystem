public class CivilBranch implements Branch{
    String bName = "Civil";
    double fees = 97234.85;
    public String toString(){
        return bName;
    }

    @Override
    public double addBranch() {
        //Add logic to assign that branch to student
        System.out.println("Branch selected as "+bName);
        return fees;
    }
    static CivilBranch c1 = new CivilBranch();
}
