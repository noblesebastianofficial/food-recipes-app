
package com.mycompany.authentication.model.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@ApiModel(value = "Token refresh Request", description = "The jwt token refresh request payload")
public class TokenRefreshRequest  implements Serializable {

    @NotBlank(message = "Refresh token cannot be blank")
    @ApiModelProperty(value = "Valid refresh token passed during earlier successful authentications", required = true,
            allowableValues = "NonEmpty String")
    private String refreshToken;

    public TokenRefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenRefreshRequest() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
