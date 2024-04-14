package com.ampei.delichi.persistance.repositories;

import com.ampei.delichi.persistance.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findAllByUser_Id(Long idUser);

}