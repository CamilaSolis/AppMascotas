package proyecto.egg.AppMascota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import proyecto.egg.AppMascota.Servicios.ClienteServicio;
import proyecto.egg.AppMascota.Servicios.VeterinarioServicio;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Seguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("clienteServicio")
    public ClienteServicio clienteServicio;

    @Autowired
    @Qualifier("veterinarioServicio")
    public VeterinarioServicio veterinarioServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clienteServicio).passwordEncoder(new BCryptPasswordEncoder())
                .and().userDetailsService(veterinarioServicio).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/css/*", "/js/*", "/img/*", "/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email")
                .passwordParameter("clave1")
                .defaultSuccessUrl("/panelUsuario")
                .failureUrl("/login?error=error")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/vetlogin")
                .loginProcessingUrl("/vetlogincheck")
                .usernameParameter("matricula")
                .passwordParameter("password1")
                .defaultSuccessUrl("/panelVeterinario")
                .failureUrl("/vetlogin?error=error")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll().and().csrf().disable();

    }

}
