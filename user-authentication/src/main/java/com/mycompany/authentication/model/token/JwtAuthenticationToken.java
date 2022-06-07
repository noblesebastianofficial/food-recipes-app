
package com.mycompany.authentication.model.token;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@Getter
@Setter
@ToString
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public JwtAuthenticationToken(Object principal, Object credentials, String token) {
        super(null, null);
        this.token = token;
    }

}
