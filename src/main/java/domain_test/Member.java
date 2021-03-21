package domain_test;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Team getTeam() {
        return team;
    }
    // setTeam은 getter/setter 메서드 관례때문에 change로 사용
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
