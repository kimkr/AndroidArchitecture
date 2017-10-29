package io.github.kimkr.data.injection;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by kkr on 10/01/2017.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
