package com.ampei.delichi.services.impls;

import com.ampei.delichi.persistance.models.Reservation;
import com.ampei.delichi.persistance.repositories.IReservationRepository;
import com.ampei.delichi.services.interfaces.IReservationService;
import com.ampei.delichi.services.interfaces.IRestaurantService;
import com.ampei.delichi.services.interfaces.IUserService;
import com.ampei.delichi.web.dtos.requests.CreateReservationRequest;
import com.ampei.delichi.web.dtos.requests.UpdateReservationRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.ReservationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository repository;

    private final IRestaurantService iRestaurantService;

    private final IUserService iUserService;


    public ReservationServiceImpl(IReservationRepository repository, IRestaurantService iRestaurantService, IUserService iUserService) {
        this.repository = repository;
        this.iRestaurantService = iRestaurantService;
        this.iUserService = iUserService;
    }

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from(findAndEnsureExist(id)))
                .detail("Reservation Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse list(Long id) {
        return BaseResponse.builder()
                .data(listReservations(id))
                .detail("List Reservations Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse create(CreateReservationRequest request, Long idUser, Long idRestaurant) {
        return BaseResponse.builder()
                .data(from(from(request, idUser, idRestaurant)))
                .detail("Reservation Created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse update(UpdateReservationRequest request, Long id) {
        return BaseResponse.builder()
                .data(from(from(request, id)))
                .detail("Reservation Updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExist(id));
        return BaseResponse.builder()
                .detail("Restaurant Deleted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private Reservation findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    private List<ReservationResponse> listReservations(Long id){
        List<Reservation> reservations = repository.findAllByUser_Id(id).orElseThrow(RuntimeException::new);
        return reservations.stream()
                .map(this::from)
                .toList();
    }

    private ReservationResponse from(Reservation reservation){
        return ReservationResponse.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .people(reservation.getPeople())
                .userId(reservation.getUser().getId())
                .userName(reservation.getUser().getName())
                .restaurantId(reservation.getRestaurant().getId())
                .restaurantName(reservation.getRestaurant().getName())
                .build();
    }

    private Reservation from(CreateReservationRequest request, Long idUser, Long idRestaurant){
        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setPeople(request.getPeople());
        reservation.setUser(iUserService.findAndEnsureExist(idUser));
        reservation.setRestaurant(iRestaurantService.findAndEnsureExist(idRestaurant));
        return repository.save(reservation);
    }

    private Reservation from(UpdateReservationRequest request, Long id){
        Reservation reservation = findAndEnsureExist(id);
        if (!reservation.getDate().isEqual(request.getDate())) {
            reservation.setDate(request.getDate());
        }
        if (!reservation.getPeople().equals(request.getPeople())) {
            reservation.setPeople(request.getPeople());
        }
        if (!reservation.getDate().isEqual(request.getDate()) || !reservation.getPeople().equals(request.getPeople())) {
            return repository.save(reservation);
        }else {
            return reservation;
        }
    }

}