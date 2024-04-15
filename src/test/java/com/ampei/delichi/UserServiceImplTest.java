package com.ampei.delichi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.ampei.delichi.persistance.models.User;
import com.ampei.delichi.persistance.repositories.IUserRepository;
import com.ampei.delichi.services.impls.UserServiceImpl;
import com.ampei.delichi.web.dtos.requests.GetUserRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class UserServiceImplTest {

    private IUserRepository userRepository;
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userRepository = mock(IUserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testGetUser() {
        GetUserRequest request = new GetUserRequest();
        request.setEmail("test@example.com");

        User user = new User();
        user.setId(1L);
        user.setName("Test");
        user.setLastName("User");

        when(userRepository.findByEmail(anyString())).thenReturn(java.util.Optional.of(user));

        BaseResponse response = userService.get(request);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("User Obtained", response.getDetail());

    }
}
