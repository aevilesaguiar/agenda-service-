package com.aeviles.agenda.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthorizationFilterConfig extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //toda vez que uma request entrar na nossa aplicação ela vai passar por aqui
        String authorizationHeader = request.getHeader("Authorization");//espero receber um header de authozation que é a forma como o postman solicita , é uma string


        log.info("request sendo feito na aplicacao");

        //SE não for empty e começar com bear ele faz o if
        if (Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            try {

                //ELE ESTÁ VERIFICANDO SE O TOKEN E A DATA DE EXPIRAÇÃO ESTÁ CORRETA

                String token = authorizationHeader.substring("Bearer ".length());//substring quer dizer que eu posso pegar uma string de 0 a 5 ou o length de Bearer, ou seja de 7 até o final dela
                //vou usar omesmo o qual gerei o token da Classe CustomAthenticationFilterConfig
                Algorithm algorithm = Algorithm.HMAC256("minha-palavra-secreta");

                //ele vai verificar o JWT
                JWTVerifier verifier = JWT.require(algorithm).build();
                //ELE VAI VERIFICAR O TOKEN
                DecodedJWT decodedJWT = verifier.verify(token);
                //SUBJECT É O NOSSO USUARIO
                String usuario = decodedJWT.getSubject();
                //AUTENTICA O TOKEN
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);

            } catch (Exception ex) {
                log.error("Mensagem de erro de autorizacao: {}", ex.getMessage());
                response.setHeader("error", ex.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}