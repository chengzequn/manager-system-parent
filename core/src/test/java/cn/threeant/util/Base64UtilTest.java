package cn.threeant.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class Base64UtilTest {

    @Test
    void decode() {

        String decode = Base64Util.decode("eyJ1c2VySWQiOiAxMjM0NTYsImFjY291bnROYW1lIjogIlhYWFhYWFgiLCJyb2xlIjogImFkbWluIn0=");
        Assertions.assertEquals("{\"userId\": 123456,\"accountName\": \"XXXXXXX\",\"role\": \"admin\"}",decode);

        String decode2 = Base64Util.decode("eyJ1c2VySWQiOiAxMjM0NTYsImFjY291bnROYW1lIjogIlhYWFhYWFgiLCJyb2xlIjogInVzZXIifQ==");
        Assertions.assertEquals("{\"userId\": 123456,\"accountName\": \"XXXXXXX\",\"role\": \"user\"}",decode2);
    }
}