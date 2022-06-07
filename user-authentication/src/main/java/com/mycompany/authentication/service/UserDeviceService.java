package com.mycompany.authentication.service;

import com.mycompany.authentication.model.UserDevice;
import com.mycompany.authentication.model.payload.DeviceInfo;
import com.mycompany.authentication.model.token.RefreshToken;

import java.util.Optional;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface UserDeviceService {
    /**
     * Find the user device info by user id
     */
    public Optional<UserDevice> findByUserId(Long userId);

    /**
     * Find the user device info by refresh token
     */
    public Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);
    /**
     * Creates a new user device and set the user to the current device
     */
    public UserDevice createUserDevice(DeviceInfo deviceInfo) ;

    /**
     * Check whether the user device corresponding to the token has refresh enabled and
     * throw appropriate errors to the client
     */
    public void verifyRefreshAvailability(RefreshToken refreshToken) ;
}
