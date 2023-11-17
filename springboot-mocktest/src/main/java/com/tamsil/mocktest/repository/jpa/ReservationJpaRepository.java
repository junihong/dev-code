package com.tamsil.mocktest.repository.jpa;

import com.tamsil.mocktest.entity.Member;
import com.tamsil.mocktest.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {

    Optional<List<Reservation>> findByMember(Member member);
}
