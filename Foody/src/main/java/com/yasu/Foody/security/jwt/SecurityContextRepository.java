package com.yasu.Foody.security.jwt;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * <span style='color:white'>Step 6: Implement the {@link ServerSecurityContextRepository}.</span>
 * <p>An implementation of the {@link ServerSecurityContextRepository} interface that retrieves the {@link SecurityContext} object for the current request based on a JWT token in the request header.</p>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final AuthenticationManager authenticationManager;

    /**
     * Not implemented. Throws an {@link UnsupportedOperationException}.
     *
     * @param exchange the {@link ServerWebExchange} object
     * @param context  the {@link SecurityContext} object to save
     * @return a {@link Mono} object
     * @throws UnsupportedOperationException always
     */
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        log.warn("Not supported yet.");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retrieves the {@link SecurityContext} object for the current request based on a JWT token in the request header.
     *
     * @param exchange the {@link ServerWebExchange} object
     * @return a {@link Mono} object containing the {@link SecurityContext} object
     */
    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);
        return Mono.justOrEmpty(authHeader)
                .filter(header -> header.startsWith("Bearer "))
                .flatMap(header -> {
                            String token = header.substring(7);
                            log.info("token from `header.substring(7)` : {}", token);
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(token, token);
                            return authenticationManager.authenticate(authentication)
                                    .map(SecurityContextImpl::new);
                        }
                );
    }
}