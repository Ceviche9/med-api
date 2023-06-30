package med.voll.medapi.controller;


import jakarta.validation.Valid;
import med.voll.medapi.domains.user.CreateUserRequest;
import med.voll.medapi.domains.user.UpdatedUsersResponse;
import med.voll.medapi.domains.user.User;
import med.voll.medapi.domains.user.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("users")
public class UsersController {

    private UsersRepository usersRepository;

    public ResponseEntity create(@RequestBody @Valid CreateUserRequest data, UriComponentsBuilder uriBuilder) {
        var user = new User(data);
        usersRepository.save(user);

        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UpdatedUsersResponse(user));
    }

}
