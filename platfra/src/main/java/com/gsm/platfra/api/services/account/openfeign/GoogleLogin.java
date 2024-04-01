package com.gsm.platfra.api.services.account.openfeign;

import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.config.oAuth2.GoogleConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "google", url = "https://www.googleapis.com", configuration = GoogleConfig.class)
public interface GoogleLogin {

    @GetMapping("/userinfo/v2/me")
    GoogleLoginDto googleLogin(@RequestParam("access_token") String accessToken);
}

