package com.liu.test.must;

import java.io.Serializable;

/**
 * @author shidacaizi
 * @date 2020/5/16 23:06
 */
public class LeftNext implements Serializable {

    private static final long serialVersionUID = 1L;

    private String right;

    private String left;

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public LeftNext() {
    }

    public LeftNext(String left, String right) {
        this.left = left;
        this.right = right;
    }
}