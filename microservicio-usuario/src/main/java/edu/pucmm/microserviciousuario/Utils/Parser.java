package edu.pucmm.microserviciousuario.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

@Component
public class Parser {

    public Parser() {}

    public boolean comparePassword(String password, String hashToCompare) {
        return password.equals(DigestUtils.md5Hex(hashToCompare));
    }
}