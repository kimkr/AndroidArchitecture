package io.github.kimkr.domain.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import lombok.Data;

/**
 * Created by kkr on 2017. 10. 21..
 */

@Entity
@Data
public class Test {

    @Id
    private Long id;
    @NotNull
    private String text;
}
