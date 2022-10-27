package br.com.futurodev.api.primeiraapi.security;

import br.com.futurodev.api.primeiraapi.Service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*Ativa a proteção contra usuarios não estão validados por token*/
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                /*Ativamos as permissões para acesso a pagina inicial do sistema EX: http://futurodev.com*/
                .disable().authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()

                /*url de logout, redireciona o usuario apos deslogar do sistema*/
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")

                /*mapeia a url de logout e invalida o usuario*/
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                /*filtrar as requisições de login para fazer a autentificação */
                .and().addFilterBefore(new JWTLoginFIlter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                /*filtrar demais requisições para verificar a preservação do token JWT no header HTTP*/

                .addFilterBefore(new JwtApiAuthenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /*service que irá  consultar o usuario no banco de dados*/
        auth.userDetailsService(cadastroUsuarioService)

                /*definimos o padrão de codificação de senha*/
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
