package Embeded;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private AddressEM homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "USER_ID") //USER_ID를 외래키로 갖게됨
    )
    @Column(name="FOOD_NAME") //String은 값이 하나고 내가 정의한게 아니기 때문에 얘는 매핑하게 허용하게 해줌 , Address인 경우는 값이 있고, attribute 사용 가능
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "ADDRESS",
//        joinColumns = @JoinColumn(name="USER_ID") //USER_ID를 외래키로 갖게됨
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city", column =@Column(name="WORK_CITY")),
//            @AttributeOverride(name = "street", column =@Column(name="WORK_STREET")),
//            @AttributeOverride(name = "zipCode", column =@Column(name="WORK_ZIPCODE"))
//    })
//    private Address workAddress;


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

    public AddressEM getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(AddressEM homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
