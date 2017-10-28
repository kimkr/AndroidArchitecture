package io.github.kimkr.domain.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import lombok.Data;

/**
 * Created by kkr on 2017. 10. 28..
 */

@Entity
@Data
public class Content {

    @Id
    private Long id;
    private String path;
    private String name;
    private String mime;
    private String location;
    private Float latitude;
    private Float longitude;
    private Long width;
    private Long height;
    private Long timeStamp;
    private Long added;
    private Long taken;
    private Long modified;
    private Long size;
    private Integer orientation;
}

