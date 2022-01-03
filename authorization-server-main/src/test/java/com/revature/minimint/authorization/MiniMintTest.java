package com.revature.minimint.authorization;

import com.revature.minimint.authorization.AuthorizationApplication;
import com.revature.minimint.authorization.entity.User.Password;
import com.revature.minimint.authorization.repository.UserRepository;
import com.revature.minimint.authorization.service.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.minimint.authorization.entity.User.User;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AuthorizationApplication.class)
@ActiveProfiles("test")
public class MiniMintTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    private User user;
    private User noPassUser;
    private User savedUser;
    private long userId;
    @Mock
    private User mockUser;
    @Mock
    private Password mockUserPassword;

    /* This will run before each @Test */
    @Before
    public void setup() {
        // Test user 1, all valid fields
        user = new User();
        user.setUserEmail("test@mail.com");
        user.setUsername("test");
        Password password = new Password();
        password.setPassword("test password");
        user.setUserPassword(password);
        savedUser = userService.addUser(user);
        userId = savedUser.getUserId();
        // Test user 2, no password
        noPassUser = new User();
        noPassUser.setUserEmail("test2@mail.com");
        noPassUser.setUsername("test2");
        Password password1 = new Password();
        password1.setPassword("");
        noPassUser.setUserPassword(password1);
        userService.addUser(noPassUser);
        // Mock user
        mockUser = Mockito.mock(User.class);
        mockUserPassword = Mockito.mock(Password.class);
        Mockito.when(mockUser.getUserId()).thenReturn(0L);
        Mockito.when(mockUser.getUsername()).thenReturn("test");
        Mockito.when(mockUser.getUserEmail()).thenReturn("test@mail.com");
        Mockito.when(mockUserPassword.getPassword()).thenReturn("test password");
        Mockito.when(mockUser.getUserPassword()).thenReturn(mockUserPassword);
    }

    /* This will run after each @Test */
    @After
    public void tearDown() {
        userRepository.deleteAll();
        //userRepository.delete(savedUser);
        //userRepository.delete(noPassUser);
        user = null;
        noPassUser = null;
        savedUser = null;
    }

    /* Save user testing */
    @Test
    public void addUserTest() {
        // Case 0: all valid
        assertTrue("saveUserTest failed",savedUser.toString().equals(user.toString()));
    }

    @Test
    public void addUserTest1() {
        // Case 1: duplicate email
        try {
            User u = userService.addUser(mockUser);
        } catch (Exception e) {
            assertTrue(e.getMessage(), e.getMessage().equals("Email is already in use"));
        }
    }

    @Test
    public void addUserTest2() {
        // Case 2: duplicate username
        Mockito.when(mockUser.getUserEmail()).thenReturn("uniqueemail@email.com");
        try {
            User u = userService.addUser(mockUser);
        } catch (Exception e) {
            assertTrue(e.getMessage(), e.getMessage().equals("Username is already in use"));
        }
    }

    /* get User by Id tests */
    @Test
    public void getUserByIdTest() {
        // Case 0: all valid
        User u = userService.getUserById(userId);
        assertTrue("getUserByIdTest failed", u.toString().equals(savedUser.toString()));
    }

    @Test
    public void getUserByIdTest2() {
        // Case 1: non-existent
        try {
            User u = userService.getUserById(-1L);
        } catch (Exception e) {
            assertTrue(e.getMessage(), e.getMessage().equals("User not found"));
        }
    }

    /* Delete user test */
    @Test
    public void deleteUserTest() {
        userRepository.delete(user);
        assertFalse("deleteUserTest failed", userRepository.existsById(userId));
    }

    /* Update user test repository based */
    @Test
    public void updateUserTestRepository() {
        User updateUser = userService.getUserById(userId);
        updateUser.setUsername("new name");
        userRepository.save(updateUser);
        assertTrue("updateUserTestRepository failed", userService.getUserById(userId).getUsername().equals("new name"));
    }

    /* Update user tests service based */
    @Test
    public void updateUserTestService1() {
        // Case 0: all valid
        User updateUser = userService.getUserById(userId);
        //update username
        updateUser.setUsername("new name");
        userService.updateUser(userId, updateUser);
        assertTrue("updateUserTestService update username failed", userService.getUserById(userId).getUsername().equals("new name"));
        //update email
        updateUser.setUserEmail("newemail@emailserver.com");
        userService.updateUser(userId, updateUser);
        assertTrue("updateUserTestService update email failed", userService.getUserById(userId).getUserEmail().equals("newemail@emailserver.com"));
        //update password
        updateUser.setUserPassword(Password.builder().password("newpassword").build());
        userService.updateUser(userId, updateUser);
        assertTrue("updateUserTestService update password failed", userService.getUserById(userId).getUserPassword().getPassword().equals("newpassword"));
    }

    @Test
    public void updateUserTestService2() {
        // Case 1: id == 0
        Mockito.when(mockUser.getUserId()).thenReturn(0L);
        try {
            User updateUser = userService.updateUser(0, mockUser);
        } catch(Exception e) {
            assertTrue(e.getMessage().equals("User not found"));
        }
    }

    @Test
    public void updateUserTestService3() {
        // Case 2: duplicate email
        Mockito.when(mockUser.getUserId()).thenReturn(3L);
        try {
            User updateUser = userService.updateUser(3, mockUser);
        } catch(Exception e) {
            assertTrue(e.getMessage().equals("Email is already in use"));
        }
    }

    @Test
    public void updateUserTestService4() {
        // Case 3: duplicate username
        Mockito.when(mockUser.getUserId()).thenReturn(3L);
        Mockito.when(mockUser.getUserEmail()).thenReturn("uniqueemail@email.com");
        try {
            User updateUser = userService.updateUser(mockUser.getUserId(), mockUser);
        } catch(Exception e) {
            assertTrue(e.getMessage().equals("Username is already in use"));
        }
    }

    @Test
    public void updateUserTestService5() {
        // Case 4: non-existent user
        Mockito.when(mockUser.getUserId()).thenReturn(-1L);
        Mockito.when(mockUser.getUserEmail()).thenReturn("uniqueemail@email.com");
        Mockito.when(mockUser.getUsername()).thenReturn("uniqueusername");
        try {
            User updateUser = userService.updateUser(mockUser.getUserId(), mockUser);
        } catch(Exception e) {
            assertTrue(e.getMessage().equals("User not found"));
        }
    }

    /* get user by email tests */
    @Test
    public void getUserByEmailTest() {
        // Case 0: all valid
        User getEmail = userService.getUserByEmail("test@mail.com");
        assertTrue("getUserByEmailTest failed", getEmail.toString().equals(user.toString()) );
    }

    @Test
    public void getUserByEmailTest2() {
        // Case 1: non-existent
        Mockito.when(mockUser.getUserEmail()).thenReturn("doesntexist@mail.com");
        try {
            User u = userService.getUserByEmail(mockUser.getUserEmail());
        } catch (Exception e) {
            assertTrue(e.getMessage(), e.getMessage().equals("User not found"));
        }
    }

    @Test
    public void getUserByUsernameTest1() {
        // Case 0: valid user
        User getUsername = userService.getUserByUsername("test");
        assertTrue("getUserByUsernameTest failed", getUsername.toString().equals(user.toString()));
    }

    @Test
    public void getUserByUsernameTest2() {
        // Case 1: non-existent user
        try {
            User getUsername = userService.getUserByUsername("michaelmcDoesntexist");
        } catch(Exception e) {
            assertTrue(e.getMessage().equals("User not found"));
        }
    }

    @Test
    public void loginTest() {
        // Test case 0: valid user
        User loginUser = userService.login(user);
        assertTrue("loginTest failed", loginUser.toString().equals(user.toString()));
    }

    @Test
    public void loginTest1() {
        // case 1: no password
        try {
            User noPassLoginUser = userService.login(noPassUser);
        } catch(Exception e) {
            assertTrue(e.getMessage().equals("No Password provided"));
        }
    }

    @Test
    public void loginTest2() {
        // case 2: id == 0, non-empty username
        User noIdUser = userService.login(mockUser);
        assertTrue(noIdUser.toString().equals(user.toString()));
    }

    @Test
    public void loginTest3() {
        // case 3: id == 0, empty username, non-empty email
        Mockito.when(mockUser.getUsername()).thenReturn("");
        User noUsernameUser = userService.login(mockUser);
        assertTrue(noUsernameUser.toString().equals(user.toString()));
    }

    @Test
    public void loginTest4() {
        // case 4: incorrect password
        Mockito.when(mockUserPassword.getPassword()).thenReturn("wrong password");
        try {
            User badPasswordUser = userService.login(mockUser);
        } catch (Exception e) {
            assertTrue(e.getMessage(), e.getMessage().equals("Email or password is incorrect"));
        }
    }

    @Test
    public void loginTest5() {
        // case 5: non-existent user
        Mockito.when(mockUser.getUserId()).thenReturn(-1L);
        try {
            User nonExistentUser = userService.login(mockUser);
        } catch (Exception e) {
            assertTrue(e.getMessage(), e.getMessage().equals("User not found"));
        }
    }


}