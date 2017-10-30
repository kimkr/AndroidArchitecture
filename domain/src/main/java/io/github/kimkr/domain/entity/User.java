package io.github.kimkr.domain.entity;

import lombok.Data;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Data
public class User {

    private String id;
    private String nickname;
    private String image;
    private String apiToken;
    private String firebaseToken;
    private String cognitoIdToken;
    private String cognitoAccessToken;
    private String cloudFrontPolicyKey;
    private String cloudFrontSignature;
    private String cloudFrontKeyPairId;
}
