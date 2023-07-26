package med.voll.medapi.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.medapi.domains.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.management.RuntimeErrorException;
import java.io.IOException;

// Esta classe é um componente generico.
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (request.getRequestURI().endsWith("/create") || request.getRequestURI().endsWith("/login")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        var jwtToken = getToken(request);

        if (jwtToken != null ) {
            var subject = tokenService.getSubject(jwtToken);
            var user = usersRepository.findByUsername(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // Para informar ao spring que o usuário está logado.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer", "").trim();
        }

        return null;
    }
}
