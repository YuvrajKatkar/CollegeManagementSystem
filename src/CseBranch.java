public class CseBranch implements Branch{
    String bName = "Computer Science";
    double fees = 125342.5;
    public String toString(){
        return bName;
    }

    @Override
    public double addBranch() {
        //Add logic to assign that branch to student
        System.out.println("Branch selected as "+bName);
        return fees;
    }
    static CseBranch c2 = new CseBranch();
}
