package io.github.kimkr.data.entity.user;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kkr on 2017. 10. 30..
 */

@Entity
public class UserEntity {

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
    @Generated(hash = 205749490)
    public UserEntity(String id, String nickname, String image, String apiToken,
            String firebaseToken, String cognitoIdToken, String cognitoAccessToken,
            String cloudFrontPolicyKey, String cloudFrontSignature,
            String cloudFrontKeyPairId) {
        this.id = id;
        this.nickname = nickname;
        this.image = image;
        this.apiToken = apiToken;
        this.firebaseToken = firebaseToken;
        this.cognitoIdToken = cognitoIdToken;
        this.cognitoAccessToken = cognitoAccessToken;
        this.cloudFrontPolicyKey = cloudFrontPolicyKey;
        this.cloudFrontSignature = cloudFrontSignature;
        this.cloudFrontKeyPairId = cloudFrontKeyPairId;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getApiToken() {
        return this.apiToken;
    }
    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
    public String getFirebaseToken() {
        return this.firebaseToken;
    }
    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
    public String getCognitoIdToken() {
        return this.cognitoIdToken;
    }
    public void setCognitoIdToken(String cognitoIdToken) {
        this.cognitoIdToken = cognitoIdToken;
    }
    public String getCognitoAccessToken() {
        return this.cognitoAccessToken;
    }
    public void setCognitoAccessToken(String cognitoAccessToken) {
        this.cognitoAccessToken = cognitoAccessToken;
    }
    public String getCloudFrontPolicyKey() {
        return this.cloudFrontPolicyKey;
    }
    public void setCloudFrontPolicyKey(String cloudFrontPolicyKey) {
        this.cloudFrontPolicyKey = cloudFrontPolicyKey;
    }
    public String getCloudFrontSignature() {
        return this.cloudFrontSignature;
    }
    public void setCloudFrontSignature(String cloudFrontSignature) {
        this.cloudFrontSignature = cloudFrontSignature;
    }
    public String getCloudFrontKeyPairId() {
        return this.cloudFrontKeyPairId;
    }
    public void setCloudFrontKeyPairId(String cloudFrontKeyPairId) {
        this.cloudFrontKeyPairId = cloudFrontKeyPairId;
    }
}