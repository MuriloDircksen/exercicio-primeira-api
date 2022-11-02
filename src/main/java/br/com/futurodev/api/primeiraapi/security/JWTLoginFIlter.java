package br.com.futurodev.api.primeiraapi.security;

import br.com.futurodev.api.primeiraapi.model.UsuarioModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*essa classe estabelece o gerenciador de tokens*/

public class JWTLoginFIlter extends AbstractAuthenticationProcessingFilter {

    //Configurando o nosso gerenciador de autentificação
    protected JWTLoginFIlter(String url, AuthenticationManager authenticationManager){
        //aqui nós obrigamos a autenticar a url
        super(new AntPathRequestMatcher(url));

        //gerenciador de autenticação
        setAuthenticationManager(authenticationManager);

    }

    @Override
    public Authentication attemptAuthentication
            (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {

       //pegamos o token do usuario para validar
        UsuarioModel user = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), UsuarioModel.class);

        //retorna o usuario login, senha e acessos
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getSenha()));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        new JWTTokenAutentificacaoService().addAuthentication(response, authResult.getName());

    }
}
