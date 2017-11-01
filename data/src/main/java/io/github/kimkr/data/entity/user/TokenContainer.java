package io.github.kimkr.data.entity.user;

import java.util.Date;

import lombok.Data;

/**
 * Created by kkr on 09/01/2017.
 */

@Data
public class TokenContainer {

    CognitoTokens cognitoTokens;
    ApiToken apiToken;
    FirebaseToken firebaseToken;
    CloudFrontTokens cloudFrontTokens;
    Date tokenExp;
}
