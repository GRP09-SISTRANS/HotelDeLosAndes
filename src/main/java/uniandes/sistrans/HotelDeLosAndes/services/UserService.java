package uniandes.sistrans.HotelDeLosAndes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uniandes.sistrans.HotelDeLosAndes.models.Role;
import uniandes.sistrans.HotelDeLosAndes.models.Usuario;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("Ethan")) throw new UsernameNotFoundException("Algo");

        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role(1, "ADMIN"));

        return new Usuario(
            1,
            "CC",
            "ah@gmail.com",
            "calle x # x - x",
            "Ethan",
            "Apellido",
            passwordEncoder.encode("123"),
            roles,
            'A'
        );
    }
    
}
