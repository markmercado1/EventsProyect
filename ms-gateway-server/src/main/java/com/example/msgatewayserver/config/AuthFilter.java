package com.example.msgatewayserver.config;


import com.example.msgatewayserver.dto.TokenDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            // Verifica que venga el header Authorization
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, HttpStatus.UNAUTHORIZED);

            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] chunks = tokenHeader.split(" ");

            if (chunks.length != 2 || !chunks[0].equals("Bearer"))
                return onError(exchange, HttpStatus.UNAUTHORIZED);

            String token = chunks[1];

            // Valida el token con el auth-service
            return webClient.build()
                    .post()
                    .uri("http://ms-auth-service/auth/validate?token=" + token)
                    .retrieve()
                    .bodyToMono(TokenDto.class)
                    .flatMap(tokenDto -> {
                        // Aquí validas los roles según la ruta
                        String path = exchange.getRequest().getURI().getPath();
                        String method = exchange.getRequest().getMethod().toString();

                        // Validación de roles según endpoint
                        if (!hasRequiredRole(path, method, tokenDto)) {
                            return onError(exchange, HttpStatus.FORBIDDEN);
                        }

                        // Agrega headers con info del usuario para los MS
                        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                                .header("X-User-Name", tokenDto.getUserName())
                                .header("X-User-Roles", String.join(",", tokenDto.getRoles()))
                                .build();

                        return chain.filter(exchange.mutate().request(modifiedRequest).build());
                    })
                    .onErrorResume(e -> onError(exchange, HttpStatus.UNAUTHORIZED));
        });
    }

    // Método que valida los roles según la ruta y método HTTP
    private boolean hasRequiredRole(String path, String method, TokenDto tokenDto) {

        // ===== RUTAS DE EVENTOS =====

        // Crear eventos - Solo ORGANIZER y ADMIN
        if (path.startsWith("/events") && method.equals("POST")) {
            return hasAnyRole(tokenDto, "ROLE_ORGANIZER", "ROLE_ADMIN");
        }

        // Editar/Eliminar eventos - Solo ORGANIZER y ADMIN
        // La validación de ownership se hace en el MS de eventos
        if (path.matches("/events/\\d+") && (method.equals("PUT") || method.equals("DELETE"))) {
            return hasAnyRole(tokenDto, "ROLE_ORGANIZER", "ROLE_ADMIN");
        }

        // Ver eventos - TODOS pueden
        if (path.startsWith("/events") && method.equals("GET")) {
            return true; // Cualquier usuario autenticado puede ver
        }

        // ===== RUTAS DE ADMIN =====

        // Cualquier ruta /admin/* - Solo ADMIN
        if (path.startsWith("/admin")) {
            return hasRole(tokenDto, "ROLE_ADMIN");
        }

        // ===== RUTAS DE USUARIOS =====

        // Ver perfil propio - Todos los autenticados
        if (path.startsWith("/users/me")) {
            return true;
        }

        // Gestionar otros usuarios - Solo ADMIN
        if (path.startsWith("/users") && !method.equals("GET")) {
            return hasRole(tokenDto, "ROLE_ADMIN");
        }

        // Por defecto, si llegó hasta aquí y tiene token válido, permite
        return true;
    }

    // Verifica si tiene un rol específico
    private boolean hasRole(TokenDto tokenDto, String role) {
        return tokenDto.getRoles() != null && tokenDto.getRoles().contains(role);
    }

    // Verifica si tiene alguno de los roles
    private boolean hasAnyRole(TokenDto tokenDto, String... roles) {
        if (tokenDto.getRoles() == null) return false;
        for (String role : roles) {
            if (tokenDto.getRoles().contains(role)) {
                return true;
            }
        }
        return false;
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public static class Config {
        // Aquí puedes agregar configuraciones personalizadas si las necesitas
        private String headerName;
        private String headerValue;

        // Getters y setters si los necesitas
    }
}