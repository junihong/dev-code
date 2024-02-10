package com.tamsil.init.repository.postgresql;

import com.tamsil.init.entity.postgresql.PostMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMemberRepository extends JpaRepository<PostMember, Long> {
}
