package com.yww.admin.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 照搬BCryptPasswordEncoder源码
 * </p>
 *
 * @author chenhao
 * @since 2022/11/24 15:28
 */
public class MyPasswordEncoder implements PasswordEncoder {

    private final Pattern BCRYPT_PATTERN;
    private final Log logger;
    private final int strength;
    private final BCryptPasswordEncoder.BCryptVersion version;
    private final SecureRandom random;

    public MyPasswordEncoder() {
        this(-1);
    }

    public MyPasswordEncoder(int strength) {
        this(strength, null);
    }

    public MyPasswordEncoder(int strength, SecureRandom random) {
        this(BCryptPasswordEncoder.BCryptVersion.$2A, strength, random);
    }

    @SuppressWarnings("all")
    public MyPasswordEncoder(BCryptPasswordEncoder.BCryptVersion version, int strength, SecureRandom random) {
        this.BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
        this.logger = LogFactory.getLog(this.getClass());
        if (strength == -1 || strength >= 4 && strength <= 31) {
            this.version = version;
            this.strength = strength == -1 ? 10 : strength;
            this.random = random;
        } else {
            throw new IllegalArgumentException("Bad strength");
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else {
            String salt = this.getSalt();
            return BCrypt.hashpw(rawPassword.toString(), salt);
        }
    }

    private String getSalt() {
        return this.random != null ? BCrypt.gensalt(this.version.getVersion(), this.strength, this.random) : BCrypt.gensalt(this.version.getVersion(), this.strength);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else if (encodedPassword != null && encodedPassword.length() != 0) {
            if (!this.BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
                this.logger.warn("Encoded password does not look like BCrypt");
                return false;
            } else {
                return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        if (encodedPassword != null && encodedPassword.length() != 0) {
            Matcher matcher = this.BCRYPT_PATTERN.matcher(encodedPassword);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Encoded password does not look like BCrypt: " + encodedPassword);
            } else {
                int strength = Integer.parseInt(matcher.group(2));
                return strength < this.strength;
            }
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }

}
