package vn.edu.hust.pthtwat.pthtwat.common;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class CommonService {
    public String getSha256hex(String originalString) {
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

    public String genStringRandom() {
        String generatedString = RandomStringUtils.randomAlphabetic(200);

        return generatedString;
    }

    public String genCodeRandom() {
        String generatedString = RandomStringUtils.randomAlphabetic(6);

        return generatedString;
    }

    public String genStringRandomWithLength(Integer length) {
        String generatedString = RandomStringUtils.randomAlphabetic(length);

        return generatedString;
    }
}
