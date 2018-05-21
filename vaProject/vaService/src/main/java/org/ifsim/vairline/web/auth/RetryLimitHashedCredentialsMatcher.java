/**
 * Copyright (c) 2017 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun. You shall not disclose such 
 * Confidential Information and shall use it only in accordance with the terms of the license agreement 
 * you entered into with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS 
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package org.ifsim.vairline.web.auth;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * <功能描述>
 * 
 * @author qsh9553
 * @version 1.2.0
 * @date 2018年5月12日 下午11:07:12
 * @since JDK 1.8
 */
public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // String username = (String) token.getPrincipal();
        UsernamePasswordToken autoken = (UsernamePasswordToken) token;
        SimpleAuthenticationInfo sinfo = (SimpleAuthenticationInfo) info;
        String pwdhash = new String(sinfo.getCredentialsSalt().getBytes());
        // 这个CipherUtil.generatePassword是自定义的static方法，用于生成加密后的密码
        // String inputCredential =
        // CipherUtil.generatePassword(String.valueOf(autoken.getPassword()) +
        // pwdhash);
        String inputCredential = CipherUtil.generatePassword(String.valueOf(autoken.getPassword()));
        // 生成的加密是大写，但mysql不区分大小写，对比会失败
        String accountCredentials = (String.valueOf(getCredentials(info))).toLowerCase();
//                info.getCredentials().toString().toLowerCase();
        boolean match = equals(inputCredential, accountCredentials);
        if (match) {
            // passwordRetryCache.remove(username);
        }
        return match;
    }
}
