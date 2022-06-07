
package com.mycompany.authentication.model.payload;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JwtAuthenticationResponse implements Serializable {

    private String token;

    private String refreshToken;

    private String tokenType;

    private Long expiryDuration;
    private Long id;
    private String username;
    private String email;

    public JwtAuthenticationResponse(String token, String refreshToken, Long expiryDuration) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiryDuration = expiryDuration;
        tokenType = "Bearer ";
    }

    public JwtAuthenticationResponse(String token, String refreshToken, Long expiryDuration, Long id, String username, String email) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiryDuration = expiryDuration;
        this.tokenType = "Bearer ";
        this.id = id;
        this.username = username;
        this.email = email;
    }

}
