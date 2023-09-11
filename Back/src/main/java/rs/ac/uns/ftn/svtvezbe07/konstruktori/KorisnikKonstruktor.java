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


    public KorisnikKonstruktor(User createdUser) {
        this.userId = createdUser.getUserId();
        this.username = createdUser.getUsername();
        this.email = createdUser.getEmail();
        this.firstName = createdUser.getFirstName();
        this.lastName = createdUser.getLastName();
    }
}
