package med.voll.medapi.controller;


import jakarta.validation.Valid;
import med.voll.medapi.domains.user.CreateUserRequest;
import med.voll.medapi.domains.user.CreateUsersResponse;
import med.voll.medapi.domains.user.User;
import med.voll.medapi.domains.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateUserRequest data, UriComponentsBuilder uriBuilder) {
        User user = new User(data.setPassword(BCrypt.hashpw(data.password(), BCrypt.gensalt())));
        usersRepository.save(user);

        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new CreateUsersResponse(user));
    }

}
