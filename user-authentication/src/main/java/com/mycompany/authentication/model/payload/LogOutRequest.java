
package com.mycompany.authentication.model.payload;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "Logout request", description = "The logout request payload")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LogOutRequest  implements Serializable {

    @Valid
    @NotNull(message = "Device info cannot be null")
    @ApiModelProperty(value = "Device info", required = true, dataType = "object", allowableValues = "A valid " +
            "deviceInfo object")
    private DeviceInfo deviceInfo;

}
