package io.github.kimkr.data.sqldelight;

import android.database.Cursor;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.Date;

import io.github.kimkr.UsersModel;
import io.github.kimkr.domain.model.user.ApiToken;
import io.github.kimkr.domain.model.user.CloudFrontTokens;
import io.github.kimkr.domain.model.user.CognitoTokens;
import io.github.kimkr.domain.model.user.FirebaseToken;
import io.github.kimkr.domain.model.user.Profile;
import io.github.kimkr.domain.model.user.TokenContainer;
import io.github.kimkr.domain.model.user.User;

/**
 * Created by kkr on 2017. 10. 21..
 */

@AutoValue
public abstract class Users implements UsersModel {

    public static final Factory<Users> FACTORY = new Factory<>(AutoValue_Users::new);

    public static final RowMapper<CognitoTokens> COGNITO_TOKENS_MAPPER =
            cursor -> {
                CognitoTokens cognitoTokens = new CognitoTokens();
                int cursorPosition = getBaseCursorPosition(cursor);
                cognitoTokens.setIdToken(cursor.getString(cursorPosition + 0));
                cognitoTokens.setAccessToken(cursor.getString(cursorPosition + 1));
                cursor.move(2);
                return cognitoTokens;
            };

    public static final RowMapper<ApiToken> API_TOKEN_MAPPER =
            cursor -> {
                ApiToken apiToken = new ApiToken();
                int cursorPosition = getBaseCursorPosition(cursor);
                apiToken.setToken(cursor.getString(cursorPosition + 0));
                cursor.move(1);
                return apiToken;
            };

    public static final RowMapper<FirebaseToken> FIREBASE_TOKEN_MAPPER =
            cursor -> {
                FirebaseToken firebaseToken = new FirebaseToken();
                int cursorPosition = getBaseCursorPosition(cursor);
                firebaseToken.setToken(cursor.getString(cursorPosition + 0));
                cursor.move(1);
                return firebaseToken;
            };

    public static final RowMapper<CloudFrontTokens> CLOUDFRONT_TOKENS_MAPPER =
            cursor -> {
                CloudFrontTokens cloudFrontTokens = new CloudFrontTokens();
                int cursorPosition = getBaseCursorPosition(cursor);
                cloudFrontTokens.setPolicyKey(cursor.getString(cursorPosition + 0));
                cloudFrontTokens.setSignature(cursor.getString(cursorPosition + 1));
                cloudFrontTokens.setKeyPairId(cursor.getString(cursorPosition + 2));
                cursor.move(3);
                return cloudFrontTokens;
            };

    public static final RowMapper<TokenContainer> TOKEN_CONTAINER_MAPPER =
            cursor -> {
                TokenContainer tokenContainer = new TokenContainer();
                CognitoTokens cognitoTokens = COGNITO_TOKENS_MAPPER.map(cursor);
                ApiToken apiToken = API_TOKEN_MAPPER.map(cursor);
                FirebaseToken firebaseToken = FIREBASE_TOKEN_MAPPER.map(cursor);
                CloudFrontTokens cloudFrontTokens = CLOUDFRONT_TOKENS_MAPPER.map(cursor);
                Date expire = new Date(cursor.getLong(getBaseCursorPosition(cursor)));
                tokenContainer.setApiToken(apiToken);
                tokenContainer.setFirebaseToken(firebaseToken);
                tokenContainer.setCognitoTokens(cognitoTokens);
                tokenContainer.setCloudFrontTokens(cloudFrontTokens);
                tokenContainer.setTokenExp(expire);
                return tokenContainer;
            };

    public static final RowMapper<Profile> PROFILE_MAPPER =
            cursor -> {
                Profile profile = new Profile();
                int cursorPosition = getBaseCursorPosition(cursor);
                profile.setId(cursor.getString(cursorPosition + 0));
                profile.setNickname(cursor.getString(cursorPosition + 1));
                profile.setProfile(cursor.getString(cursorPosition + 2));
                cursor.move(3);
                return profile;
            };

    public static final RowMapper<User> USER_MAPPER = cursor -> {
        User user = new User();
        Profile profile = PROFILE_MAPPER.map(cursor);
        TokenContainer tokenContainer = TOKEN_CONTAINER_MAPPER.map(cursor);
        user.setProfile(profile);
        user.setTokenContainer(tokenContainer);
        return user;
    };

    private static int getBaseCursorPosition(Cursor cursor) {
        int cursorPosition = cursor.getPosition();
        if (cursorPosition < 0) {
            cursor.moveToNext();
            cursorPosition = 0;
        }
        return cursorPosition;
    }
}
