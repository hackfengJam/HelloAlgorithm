package hashcode_test;

import java.util.HashMap;
import java.util.HashSet;

public class HashCodeTest {
    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer) a).hashCode());
        int b = -42;
        System.out.println(((Integer) b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double) c).hashCode());

        String d = "hackfun";
        System.out.println(d.hashCode());

        Student student = new Student(3, 2, "Hackfun", "jiang");
        System.out.println(student.hashCode());


        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);
    }
}

