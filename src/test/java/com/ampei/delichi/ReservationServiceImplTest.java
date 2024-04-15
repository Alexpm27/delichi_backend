package com.ampei.delichi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.ampei.delichi.persistance.models.Reservation;
import com.ampei.delichi.persistance.repositories.IReservationRepository;
import com.ampei.delichi.services.impls.ReservationServiceImpl;
import com.ampei.delichi.services.interfaces.IRestaurantService;
import com.ampei.delichi.services.interfaces.IUserService;
import com.ampei.delichi.web.dtos.requests.CreateReservationRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.ReservationResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImplTest {

    private IReservationRepository repository;
    private IRestaurantService restaurantService;
    private IUserService userService;
    private ReservationServiceImpl reservationService;

    @Before
    public void setUp() {
        repository = mock(IReservationRepository.class);
        restaurantService = mock(IRestaurantService.class);
        userService = mock(IUserService.class);
        reservationService = new ReservationServiceImpl(repository, restaurantService, userService);
    }

    @Test
    public void testGetReservation() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        reservation.setDate(LocalDateTime.now());
        reservation.setPeople(4);

        when(repository.findById(reservationId)).thenReturn(Optional.of(reservation));

        BaseResponse response = reservationService.get(reservationId);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("Reservation Obtained", response.getDetail());

        ReservationResponse reservationResponse = (ReservationResponse) response.getData();
        assertEquals(reservationId, reservationResponse.getId());
    }

    @Test
    public void testListReservations() {
        Long userId = 1L;
        Reservation reservation1 = new Reservation();
        reservation1.setId(1L);
        reservation1.setDate(LocalDateTime.now());
        reservation1.setPeople(2);

        Reservation reservation2 = new Reservation();
        reservation2.setId(2L);
        reservation2.setDate(LocalDateTime.now());
        reservation2.setPeople(4);

        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation1);
        reservationList.add(reservation2);

        when(repository.findAllByUser_Id(userId)).thenReturn(Optional.of(reservationList));

        BaseResponse response = reservationService.list(userId);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("List Reservations Obtained", response.getDetail());

        List<ReservationResponse> reservationResponses = (List<ReservationResponse>) response.getData();
        assertEquals(2, reservationResponses.size());
    }
}
