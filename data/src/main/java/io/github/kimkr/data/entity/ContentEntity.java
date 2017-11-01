package io.github.kimkr.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kkr on 2017. 10. 28..
 */

@Entity
public class ContentEntity {

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
    @Generated(hash = 1918079)
    public ContentEntity(Long id, String path, String name, String mime,
            String location, Float latitude, Float longitude, Long width,
            Long height, Long timeStamp, Long added, Long taken, Long modified,
            Long size, Integer orientation) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.mime = mime;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.width = width;
        this.height = height;
        this.timeStamp = timeStamp;
        this.added = added;
        this.taken = taken;
        this.modified = modified;
        this.size = size;
        this.orientation = orientation;
    }
    @Generated(hash = 271396990)
    public ContentEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMime() {
        return this.mime;
    }
    public void setMime(String mime) {
        this.mime = mime;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Float getLatitude() {
        return this.latitude;
    }
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
    public Float getLongitude() {
        return this.longitude;
    }
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
    public Long getWidth() {
        return this.width;
    }
    public void setWidth(Long width) {
        this.width = width;
    }
    public Long getHeight() {
        return this.height;
    }
    public void setHeight(Long height) {
        this.height = height;
    }
    public Long getTimeStamp() {
        return this.timeStamp;
    }
    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public Long getAdded() {
        return this.added;
    }
    public void setAdded(Long added) {
        this.added = added;
    }
    public Long getTaken() {
        return this.taken;
    }
    public void setTaken(Long taken) {
        this.taken = taken;
    }
    public Long getModified() {
        return this.modified;
    }
    public void setModified(Long modified) {
        this.modified = modified;
    }
    public Long getSize() {
        return this.size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public Integer getOrientation() {
        return this.orientation;
    }
    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }
}

