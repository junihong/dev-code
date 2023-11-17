package com.tamsil.init.repository.mysql;

import com.tamsil.init.entity.mysql.MyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyMemberRepository extends JpaRepository<MyMember, Long> {
}
