package com.youngzeu.mplus.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneraIdUtil {

    private static final String REPLACE_FLAG = "0";

    public static String generaUUID() {
        return UUID.randomUUID().toString().replaceAll("-", REPLACE_FLAG);
    }

    public static String generaRoleCacheId(String roleId) {
        return "ROLE_PERMS_CACHE^" + roleId;
    }

}
