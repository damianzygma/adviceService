package pl.com.mtd.adviceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.com.mtd.adviceservice.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

//    @Test
//    public void testCreateUser(){
//        User user = new User();
//        user.setEmail("jannowak@gmail.com");
//        user.setPassword("jan2022");
//        user.setFirstName("Jan");
//        user.setLastName("Nowak");
//        user.setQuestion("");
//        user.setQuestionAnswear("");
//
//        User savedUser = repo.save(user);
//
//        User existUser = entityManager.find(User.class,savedUser.getId());
//
//        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
//    }
}