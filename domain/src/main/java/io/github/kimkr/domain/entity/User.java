package io.github.kimkr.domain.entity;

import lombok.Data;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Data
public class User {

    private String id;
    private String provider;
    private String name;
    private String photo;
    private String email;
    private String phone;
    private Boolean verified;
}
