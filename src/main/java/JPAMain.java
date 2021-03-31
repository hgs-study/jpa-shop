import Cascade.Child;
import Cascade.Parent;
import Embeded.Address;
import Embeded.Period;
import Embeded.User;
import domain_test.Member;
import domain_test.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JPAMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            //값 타입 저장
            User user = new User();
            user.setName("현건수");
            user.setHomeAddress(new Address("homeCity","street","zipcode"));

            user.getFavoriteFoods().add("치킨"); //insert문 나감
            user.getFavoriteFoods().add("족발"); //insert문 나감
            user.getFavoriteFoods().add("피자"); //insert문 나감 -> insert 총 3개 나감

            user.getAddressHistory().add(new Address("old1","street1","zipcode1"));
            user.getAddressHistory().add(new Address("old2","street2","zipcode2"));

            em.persist(user); //user를 persist 하면 컬렉션은 다른 테이블인데도 불구하고 같은 라이프 사이클을 가짐

            em.flush();
            em.clear();

            //값 타입 조회
            System.out.println("=======================");
            User findUser = em.find(User.class, user.getId());
            System.out.println("조회 :"+findUser);

            List<Address> addressHistory = findUser.getAddressHistory();
            addressHistory.forEach(i -> System.out.println(i.getCity())); //지연로딩 확인 가능

            //값 타입 수정 (setCity,setStreet,setZipCode로 변경하면 전체 참조하는 게 다 바뀜) 해결: 새로운 객체를 넣어야한다.
            System.out.println("=======================");
            Address homeAddress = findUser.getHomeAddress();
            findUser.setHomeAddress(new Address("newCity",homeAddress.getStreet(), homeAddress.getZipCode()));

            findUser.getFavoriteFoods().remove("족발");
            findUser.getFavoriteFoods().add("한식");

            //Address entity에서 equals를 제대로 override해야한다. 안그럼 망한다!!!! 중요!!!!★★★
            findUser.getAddressHistory().remove(new Address("old1","street1","zipcode1"));
            findUser.getAddressHistory().add(new Address("newCity1","street","zipcode"));

           tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }



}