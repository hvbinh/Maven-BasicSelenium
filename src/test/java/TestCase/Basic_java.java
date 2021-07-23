package TestCase;

public class Basic_java {
    String name;
    protected void setName(String name)
    {
        this.name = name;
    }
    protected String getName()
    {
        return this.name;
    }
    public static void main(String[] args)
    {
        Basic_java nv1 = new Basic_java();
        nv1.setName("buoi sang");

        Basic_java nv2 = nv1;
        nv2.setName("buoi tối");

        System.out.println("nv1: "+nv1.getName());
        System.out.println("nv2: "+nv2.getName());

        String a = "today";
        String b = a;
        b = "tomorrow";
        System.out.println("a: "+a);
        System.out.println("b: "+b);

    }

}
