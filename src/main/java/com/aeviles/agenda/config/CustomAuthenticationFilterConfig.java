package com.aeviles.agenda.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j //classe para inclusão de logs
@RequiredArgsConstructor
public class CustomAuthenticationFilterConfig extends UsernamePasswordAuthenticationFilter {


    //o metodo da Classe SecurityConfig
    private final AuthenticationManager authenticationManager;

    //parte de autenticação dos metodos da classe UsernamePasswordAuthenticationFilter
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String usuario = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);//essas contantes são da proria classe UsernamePasswordAuthenticationFilter
        String senha = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);//essas contantes são da proria classe UsernamePasswordAuthenticationFilter

        log.info("Usuario: {}, e senha: {}", usuario, senha);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, senha);
        return authenticationManager.authenticate(authenticationToken);

    }

    //se der certo ele retorna um token para o usuario (adicionaremos uma nova biblioteca jwt ele que gera o token)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {

        //palavra de segurança
        Algorithm algorithm = Algorithm.HMAC256("minha-palavra-secreta");

        User user = (User) auth.getPrincipal();//ele é o nosso  User DO SPRINGsECURITY

        //ele vai ter um username , a data de expiração e a url do request de onde está vindo
        String access_token = JWT.create()
                .withSubject(user.getUsername())//usrname do usuario no springSecurity
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))//peguei a hora em milisegundos ex: vendo jwt  "iat": 1516239022 - esse valor 10*60*1000 é 10 minutos
                .withIssuer(request.getRequestURL().toString())//saber de onde veio a request
                .sign(algorithm);

        //uma chave, e o valor que tem a chave
        Map<String, String> token = new HashMap<>(); //lista com duas posições
        token.put("access_token", access_token);
        response.setContentType(APPLICATION_JSON_VALUE);//quero dizer que o retorno dele é um aplication/json
        new ObjectMapper().writeValue(response.getOutputStream(), token);



    }




}
