package edu.pucmm.usuariomicroservicio.Utils;

import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

@Component
public class Parser {

    public Parser() {}

    public boolean comparePassword(String password, String hashToCompare) {
        return password.equals(DigestUtils.md5Hex(hashToCompare));
    }
}