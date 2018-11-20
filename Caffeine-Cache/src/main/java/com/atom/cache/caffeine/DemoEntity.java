package com.atom.cache.caffeine;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: cache-collection-demo
 * @description:
 * @author: Maxxx.Yg
 * @create: 2018-11-19 17:03
 **/
public class DemoEntity implements Serializable {

    private static final long serialVersionUID = 1439838916882753920L;

    private Integer id;

    private String name;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
