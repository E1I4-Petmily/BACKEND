package e1i4.petmily.users.repository;

import e1i4.petmily.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findbyUsername(String username);
}
