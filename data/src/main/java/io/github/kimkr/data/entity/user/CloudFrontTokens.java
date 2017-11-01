package io.github.kimkr.data.entity.user;

import lombok.Data;

/**
 * Created by kkr on 02/01/2017.
 */

@Data
public class CloudFrontTokens {

    private static final String COOKIE_POLICY_KEY = "CloudFront-Policy";
    private static final String COOKIE_SIGNATURE = "CloudFront-Signature";
    private static final String COOKIE_KEY_PAIR_ID = "CloudFront-Key-Pair-Id";
    private String policyKey;
    private String signature;
    private String keyPairId;

    public String asCookieValue() {
        return COOKIE_POLICY_KEY + "=" + policyKey + ";" +
                COOKIE_SIGNATURE + "=" + signature + ";" +
                COOKIE_KEY_PAIR_ID + "=" + keyPairId;
    }
}
