package io.github.kimkr.data.entity.user;

import lombok.Data;

/**
 * Created by kkr on 02/01/2017.
 */

@Data
public class CognitoTokens {

    private String idToken;
    private String accessToken;
}
