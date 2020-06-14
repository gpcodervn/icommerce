package com.icommerce.account.model.response;

import com.icommerce.core.constant.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthenticationToken {

    private String accessToken;

    private String refreshToken;

    private String tokenType = Auth.BEARER_PREFIX;
}
