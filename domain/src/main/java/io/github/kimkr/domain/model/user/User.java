package io.github.kimkr.domain.model.user;

import io.github.kimkr.domain.model.Model;
import lombok.Data;

/**
 * Created by kkr on 02/01/2017.
 */

@Data
public class User implements Model {

    Profile profile;
    TokenContainer tokenContainer;

    @Override
    public String getId() {
        if (profile == null) {
            return null;
        }
        return profile.getId();
    }
}
