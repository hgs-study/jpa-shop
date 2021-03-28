package domain;

import Common.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
public class Category extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM"
            , joinColumns = @JoinColumn(name="CATEGORY_ID")    //내가 조인해야하는 것
            , inverseJoinColumns = @JoinColumn(name="ITEM_ID") //반대쪽이 조인해야하는것
    ) //연관관계 주인만
    private List<Item> items = new ArrayList<>();
}
