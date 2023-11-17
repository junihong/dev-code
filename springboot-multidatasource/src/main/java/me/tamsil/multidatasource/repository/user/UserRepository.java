package me.tamsil.multidatasource.repository.user;

import me.tamsil.multidatasource.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
