package com.example.book.respository;
import com.example.book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByEmail(String email);

  /* u, @Query("SELECT  r.roleName FROM User u INNER JOIN UserRole ur ON u.userid = ur.userid INNER JOIN Role r ON ur.roleid = r.roleid")
    List<Object[]> findAllUsersWithRole();*/

    /* User findByPhone(String phoneNumber);*/

}

