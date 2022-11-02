package br.com.futurodev.api.primeiraapi.security;

import br.com.futurodev.api.primeiraapi.context.ApplicationContextLoad;
import br.com.futurodev.api.primeiraapi.model.UsuarioModel;
import br.com.futurodev.api.primeiraapi.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@Component
public class JWTTokenAutentificacaoService {
    /*validade do token em milissegundos*/
    private static final long EXPIRATION_TIME = 24*60*60*2;//2 dias em milissegundo

    /*uma senha unica para compor a autenticação e ajudar na segurança*/
    private static final String SECRET = "sadhsaudh123ijJHDSAip%$#haf%4j23jne3e";//senha aleatória extremamente forte para compor as senhas

    /*prefixo padrão do token*/
    private static final String TOKEN_PREFIX = "Bearer";

    /*cabeçalho do teken - Authorization*/
    private static final String HEADER_STRING= "Authorization";

    /*Gerando token de autentificação e adicionando ao cabeçalho e resposta http*/

    public void addAuthentication (HttpServletResponse response, String userName) throws IOException {
        /*montagem do token*/
        String JWT = Jwts.builder()//chama o gerador de token
                .setSubject(userName)//adicionamos o usuario
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))//definimos tempo de expiração do token para 2 dias após criação
                .signWith(SignatureAlgorithm.HS512, SECRET).compact(); //compactando e usando o algoritmo de criptografia

        String token = TOKEN_PREFIX + "  " + JWT; //construção do token

        /*adiciona no cabeçalho http */
        response.addHeader(HEADER_STRING, token);

        /*Escrever o token como resposta no corpo Http*/
        response.getWriter().write("{\"Authorization\": \""+token+"\"}");
    }

    /*Retorna o usuario validado com token ou caso seja invalido, retorna null*/
    public Authentication getAtuthentication (HttpServletRequest request){
        /*vamos pegar o token enviado no cabeçalho http*/
        String token = request.getHeader(HEADER_STRING);

        if(token!=null){
            String user = Jwts.parser().setSigningKey(SECRET) //retorna a senha secreta
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //tira o bearer da senha
                    .getBody().getSubject(); //recebe/captura o usuario
            if(user != null){
                UsuarioModel usuario = ApplicationContextLoad.getApplicationContext()
                        .getBean(UsuarioRepository.class).findUserByLogin(user);//pega o usuario que veio na requisição e verifica no banco de dados se ele é valido

                if (usuario != null){
                    return new UsernamePasswordAuthenticationToken(
                            usuario.getLogin(),
                            usuario.getPassword(),
                            usuario.getAuthorities());
                }
            }
        }
        return null; //somente se tudo for null, assim fica não autorizado
    }

}
