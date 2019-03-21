package hashcode_test;

public class Student {

    int grade;
    int cls;
    String firstName;
    String lastName;

    Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int hashCode() {

        int B = 31;

        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode(); // 忽略大小写
        hash = hash * B + lastName.toLowerCase().hashCode(); // 忽略大小写
        // 可能产生溢出，但依然没有关系

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;


        if (!(o instanceof Student) || getClass() != o.getClass())
            return false;

        Student another = (Student) o;

        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }

}
