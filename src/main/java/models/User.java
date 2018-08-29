package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class User extends Model {
    @Column (name="age")
    private int age;

    @Column (name = "firstname",length = 50)
    private String firstName;

    @Column (name = "lasttname",length = 50)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles=new HashSet<Role>();

    public User() {
        super();
    }
    public User(Long id){
        super(id);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
