package com.qiancijun.ctwing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cheryl 769303522@qq.com
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
}
