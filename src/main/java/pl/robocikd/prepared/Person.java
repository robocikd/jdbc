package pl.robocikd.prepared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Person {

    private int id;
    private String first;
    private String last;
    private String age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
