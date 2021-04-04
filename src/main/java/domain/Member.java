package domain;

import Common.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    //김영한님은 거의 이걸 잘못된 코드라고 생각한다. 테스트이기 때문에 이렇게 사용함
    //Member에 굳이 orders 양방향으로 없어도 되기 때문에 (Members에서 Orders를 꺼낼 일이 거의 없기 때문에)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}