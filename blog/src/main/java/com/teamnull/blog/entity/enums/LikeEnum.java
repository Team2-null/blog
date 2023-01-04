package com.teamnull.blog.entity.enums;

public enum LikeEnum {
    POST(LikeType.POST),
    COMMENT(LikeType.COMMENT);

    private final Integer type;

    LikeEnum(Integer type){
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public static class LikeType{
        public static final Integer POST = 1;
        public static final Integer COMMENT = 2;
    }

}
