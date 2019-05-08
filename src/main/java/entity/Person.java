package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import util.InjectRandomInt;

/**
 * This class contains fields for MySQL table,
 *  also it contains data for
 *  MySQL table and toString method
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;

    @InjectRandomInt
    private Integer value;

    @Override
    public String toString() {
        return "Person Id:- " + getId() + " First Name:- " + getFirstName() + " Last Name:- " +
                getLastName() + " Age:- " + getAge() + " Value:- " + getValue();
    }
}
