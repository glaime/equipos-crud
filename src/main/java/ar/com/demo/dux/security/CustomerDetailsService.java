package ar.com.demo.dux.security;

import ar.com.demo.dux.model.UsuarioModel;
import ar.com.demo.dux.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;
    private UsuarioModel userDetail;

    public CustomerDetailsService(UsuarioRepository repository){
        super();
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Entrando a función loadUserByUsername");
        userDetail = this.repository.findByUsuario(username);
        if(!Objects.isNull(userDetail)){
            return new org.springframework.security.core.userdetails.User(userDetail.getUsuario(), userDetail.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    public UsuarioModel getUserDetail(){
        return userDetail;
    }
}
