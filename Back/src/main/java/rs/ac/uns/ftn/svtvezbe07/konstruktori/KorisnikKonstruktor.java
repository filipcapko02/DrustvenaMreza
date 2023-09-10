package rs.ac.uns.ftn.svtvezbe07.konstruktori;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikKonstruktor {
    private Long userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;


    public KorisnikKonstruktor(User nov_user) {
        this.userId = nov_user.getUserId();
        this.username = nov_user.getUsername();
        this.email = nov_user.getEmail();
        this.firstName = nov_user.getFirstName();
        this.lastName = nov_user.getLastName();
    }
}
