package by.it_akademy.fitness.security.filter;

import by.it_akademy.fitness.security.costom.JwtAuthenticationException;
import by.it_akademy.fitness.storage.api.IUserStorage;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private final IUserStorage userStorage;
    @Autowired
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String email;
        final String jwtToken;


        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            log.info("doFilterChain");
            return;
        }


        try {
        jwtToken = authHeader.substring(7);
            email = jwtUtil.extractUsername(jwtToken);
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails myUser = userStorage.findByLogin(email);
                if (jwtUtil.validateToken(jwtToken, myUser)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    myUser,
                                    null,
                                    myUser.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        catch(JwtAuthenticationException e){
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value());
            throw new JwtAuthenticationException("JWT token is expired or invalid",HttpStatus.UNAUTHORIZED);
        }
        catch (MalformedJwtException e){
            SecurityContextHolder.clearContext();
            response.sendError(401,e.toString());
            throw new MalformedJwtException("fuck you ");
        }
        catch (SignatureException e){
            SecurityContextHolder.clearContext();
            response.sendError(401,e.toString());
            throw new SignatureException("fuck you ");
        }
        filterChain.doFilter(request, response);
    }
}
