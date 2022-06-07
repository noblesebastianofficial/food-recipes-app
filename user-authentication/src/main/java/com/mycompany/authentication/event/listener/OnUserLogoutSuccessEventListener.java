package com.mycompany.authentication.event.listener;

import com.mycompany.authentication.cache.LoggedOutJwtTokenCache;
import com.mycompany.authentication.event.OnUserLogoutSuccessEvent;
import com.mycompany.authentication.model.payload.DeviceInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * * @author Noble Sebastian.
 *
 * @version 1.0.1.0
 */
@Component
public class OnUserLogoutSuccessEventListener implements ApplicationListener<OnUserLogoutSuccessEvent> {

    private static final Logger logger = Logger.getLogger(OnUserLogoutSuccessEventListener.class);
    private final LoggedOutJwtTokenCache tokenCache;

    @Autowired
    public OnUserLogoutSuccessEventListener(LoggedOutJwtTokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    public void onApplicationEvent(OnUserLogoutSuccessEvent event) {
        if (null != event) {
            DeviceInfo deviceInfo = event.getLogOutRequest().getDeviceInfo();
            logger.info(String
                .format("Log out success event received for user [%s] for device [%s]", event.getUserEmail(),
                    deviceInfo));
            tokenCache.markLogoutEventForToken(event);
        }
    }
}
