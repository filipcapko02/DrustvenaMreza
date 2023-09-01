package rs.ac.uns.ftn.svtvezbe07.konstruktori;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String ime;
    private String prezime;
    private String email;
    private String username;
    private String lozinka;
}
