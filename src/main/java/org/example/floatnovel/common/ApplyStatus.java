package org.example.floatnovel.common;

public enum ApplyStatus {
    //创建枚举对象
    PENDING(0, "待审核"),
    APPROVED(1, "通过"),
    REJECTED(2, "拒绝");

    private final int code;
    private final String desc;

    //枚举的构造方法，只能枚举类自己调用
    ApplyStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ApplyStatus fromCode(int code) {
        for (ApplyStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知状态：" + code);
    }

}
