package io.github.kimkr.domain.model.user;

import org.greenrobot.greendao.annotation.Entity;

import lombok.Data;

/**
 * Created by kkr on 02/01/2017.
 */

@Entity
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
