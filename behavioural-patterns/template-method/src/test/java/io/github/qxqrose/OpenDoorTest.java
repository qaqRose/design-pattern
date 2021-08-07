package io.github.qxqrose;

import io.github.qxqrose.impl.AppOpenDoor;
import io.github.qxqrose.impl.FaceOpenDoor;
import io.github.qxqrose.impl.FingerprintOpenDoor;
import io.github.qxqrose.impl.VoiceOpenDoor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: qiu
 * 2021/8/7
 */
public class OpenDoorTest {

    @Test
    public void testVoice() {
        // 构造开门信息
        Verification verification = new Verification("1", "123", "voice");
        AbstractOpenDoor openDoor = getOpenDoorByType(verification.getType());
        Assert.assertEquals(true, openDoor.open(verification));
    }

    @Test
    public void testApp() {
        // 构造开门信息
        Verification verification = new Verification("2", "123", "app");
        AbstractOpenDoor openDoor = getOpenDoorByType(verification.getType());
        Assert.assertEquals(true, openDoor.open(verification));
    }

    @Test
    public void testFingerprint() {
        // 构造开门信息
        Verification verification = new Verification("3", "123", "fingerprint");
        AbstractOpenDoor openDoor = getOpenDoorByType(verification.getType());
        Assert.assertEquals(true, openDoor.open(verification));
    }

    @Test
    public void testFace() {
        // 构造开门信息
        Verification verification = new Verification("4", "123", "face");
        AbstractOpenDoor openDoor = getOpenDoorByType(verification.getType());
        Assert.assertEquals(true, openDoor.open(verification));
    }

    private AbstractOpenDoor getOpenDoorByType(String type) {
        AbstractOpenDoor openDoor = null;
        switch (type) {
            case "app":
                openDoor = new AppOpenDoor();
                break;
            case "fingerprint":
                openDoor = new FingerprintOpenDoor();
                break;
            case "face":
                openDoor = new FaceOpenDoor();
                break;
            case "voice":
                openDoor = new VoiceOpenDoor();
                break;
        }
        return openDoor;
    }

}
