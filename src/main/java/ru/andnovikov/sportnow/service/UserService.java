package ru.andnovikov.sportnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> activateRegistration(String key);

    Optional<User> completePasswordReset(String newPassword, String key);

    Optional<User> requestPasswordReset(String mail);

    User registerUser(UserDTO userDTO, String password);

    User createUser(UserDTO userDTO);

    void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl);

    Optional<UserDTO> updateUser(UserDTO userDTO);

    void deleteUser(String login);

    void changePassword(String currentClearTextPassword, String newPassword);

    Page<UserDTO> getAllManagedUsers(Pageable pageable);

    List<UserDTO> getAllUsers();

    Optional<User> getUserWithAuthoritiesByLogin(String login);

    Optional<User> getUserWithAuthorities(String id);

    Optional<User> getUserWithAuthorities();

    @Scheduled(cron = "0 0 1 * * ?")
    void removeNotActivatedUsers();

    List<String> getAuthorities();

    User save(User user);
}
