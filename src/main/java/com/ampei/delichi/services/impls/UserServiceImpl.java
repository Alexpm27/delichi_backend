package com.ampei.delichi.services.impls;

import com.ampei.delichi.persistance.models.User;
import com.ampei.delichi.persistance.repositories.IUserRepository;
import com.ampei.delichi.services.interfaces.IUserService;
import com.ampei.delichi.web.dtos.requests.CreateUserRequest;
import com.ampei.delichi.web.dtos.requests.GetUserRequest;
import com.ampei.delichi.web.dtos.requests.LoginUserRequest;
import com.ampei.delichi.web.dtos.requests.UpdateUserRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;

    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseResponse get(GetUserRequest request) {
        return BaseResponse.builder()
                .data(from(findUserByEmail(request.getEmail())))
                .detail("User Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse login(LoginUserRequest request) {
        User userByEmail = findUserByEmail(request.getEmail());
        if (userByEmail.getPassword().equals(request.getPassword())){
            return BaseResponse.builder()
                    .data(from(userByEmail))
                    .detail("User Logged")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .status(HttpStatus.OK.value()).build();
        }
        throw new RuntimeException("Not logged");
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        return BaseResponse.builder()
                .data(from(validateExists(request)))
                .detail("User Created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse update(UpdateUserRequest request, Long id) {
        return BaseResponse.builder()
                .data(from(updateUser(request, id)))
                .detail("User Updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExist(id));
        return BaseResponse.builder()
                .detail("User Deleted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    private User findUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    @Override
    public User findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    private UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }

    private User validateExists(CreateUserRequest request) {
        if (repository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber()).isPresent()){
            throw new RuntimeException();
        }
        return repository.save(from(request));
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        return user;
    }

    private User updateUser(UpdateUserRequest request, Long id) {
        User user = findAndEnsureExist(id);
        if (!user.getPhoneNumber().equals(request.getPhoneNumber())){
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (!user.getName().equals(request.getName())) {
            user.setName(request.getName());
        }
        if (!user.getEmail().equals(request.getEmail())) {
            user.setEmail(request.getEmail());
        }
        if (!user.getPassword().equals(request.getPassword())) {
            user.setPassword(request.getPassword());
        }
        if (!user.getLastName().equals(request.getLastName())) {
            user.setLastName(request.getLastName());
        }
        if (!user.getPhoneNumber().equals(request.getPhoneNumber()) || !user.getName().equals(request.getName()) || !user.getEmail().equals(request.getEmail()) || !user.getPassword().equals(request.getPassword()) || !user.getLastName().equals(request.getLastName())) {
            return repository.save(user);
        }else {
            return user;
        }
    }

}