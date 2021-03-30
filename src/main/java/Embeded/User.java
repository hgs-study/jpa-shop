package Embeded;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long Id;

    @Column(name= "USERNAME")
    private String name;

    //기간
    @Embedded
    private Period workPeriod;
    //주소
    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column =@Column(name="WORK_CITY")),
            @AttributeOverride(name = "street", column =@Column(name="WORK_STREET")),
            @AttributeOverride(name = "zipCode", column =@Column(name="WORK_ZIPCODE"))
    })
    private Address workAddress;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
