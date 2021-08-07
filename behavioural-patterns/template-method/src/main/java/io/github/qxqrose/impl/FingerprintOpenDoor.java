package io.github.qxqrose.impl;


import io.github.qxqrose.AbstractOpenDoor;
import io.github.qxqrose.Verification;

/**
 * @author: qiu
 * 2021/8/5
 */
public class FingerprintOpenDoor extends AbstractOpenDoor {

    @Override
    protected boolean isValidVerification(Verification verification) {
        System.out.println("指纹验证开门信息");
        return true;
    }
}
