package io.github.qxqrose;

/**
 * @author: qiu
 * 2021/8/5
 * 抽象开门方法
 */
public abstract class AbstractOpenDoor {

    /**
     * 开门
     * @param verification
     */
    final public boolean open(Verification verification) {
        if(isValidVerification(verification)) {
            // 调用api开门
            return _open(verification.getDoorId());
        } else {
            //
            System.out.println("验证失败，无法开门");
        }
        return false;
    }

    /**
     * 验证开门信息
     * @param verification
     * @return
     */
    protected abstract boolean isValidVerification(Verification verification);

    /**
     * 实际开门
     * @param doorId
     */
    private boolean _open(String doorId) {
        System.out.println(doorId + "门打开成功");
        return true;
    }
}
