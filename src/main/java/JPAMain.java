import Embeded.AddressEM;
import Embeded.AddressEntity;
import Embeded.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            user.setHomeAddress(new AddressEM("homeCity","street","zipcode"));

            user.getFavoriteFoods().add("치킨"); //insert문 나감
            user.getFavoriteFoods().add("족발"); //insert문 나감
            user.getFavoriteFoods().add("피자"); //insert문 나감 -> insert 총 3개 나감

//            user.getAddressHistory().add(new Address("old1","street1","zipcode1"));
//            user.getAddressHistory().add(new Address("old2","street2","zipcode2"));

            em.persist(user); //user를 persist 하면 컬렉션은 다른 테이블인데도 불구하고 같은 라이프 사이클을 가짐

            em.flush();
            em.clear();

            //값 타입 조회
            System.out.println("=======================");
            User findUser = em.find(User.class, user.getId());
            System.out.println("조회 :"+findUser);

            //값 타입을 oneToMany로 엔테테로 한 번 래핑해서 값타입을 엔티티로 승급해서 사용
            //실제로 실무에서는 이 방법을 많이 사용함 (영한님)
            findUser.getAddressHistory().add(new AddressEntity("newCity1","street1","zipcode1"));
            findUser.getAddressHistory().add(new AddressEntity("newCity2","street","zipcode"));

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