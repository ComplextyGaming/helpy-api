package com.helpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//Primera Clase
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //prePostEnabled da soporte para usar en el proyecto las otras anotaciones como PreAuthorize , PostAuthorize .
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    //Extrae los valores de las propiedades definidas en application.properties
    @Value("${security.signing-key}")
    private String signingKey;

    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    @Value("${security.security-realm}")
    private String securityRealm;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    //para recuperar un nombre de usuario, contraseña y otros atributos para autenticarse con un nombre de usuario y contraseña.
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEnconder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    //manejo del control de las sesiones
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }

    //configu
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//steless sin estado donde el backend y frontend estan separado por lo tanto el backend no tiene el control del estado de las vistas del front
                .and()
                .httpBasic()
                .realmName(securityRealm)//nombre de la configuracion definido en applicationproperties
                .and()
                .csrf()//por defecto spring security tiene unos tokes habilitados csrf para evitar ataques desde un front pero como el front esta en otro propecto  esto ya no tiene sentido
                .disable();//por eso de deshabilita
    }

    //generando instancia de tokenb
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);//firma con la cual se generara el token
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {// indico donde se almacenara el token
        return new JwtTokenStore(accessTokenConverter()); //EN MEMORIA
        //return new JdbcTokenStore(this.dataSource); //EN BASE DE DATOS
    }

    //la creacion el proveedor de los token
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());//se alnacenara en el token store
        defaultTokenServices.setSupportRefreshToken(true);//es el que me permite genera un token de acceso
        defaultTokenServices.setReuseRefreshToken(false);
        return defaultTokenServices;
    }


}
