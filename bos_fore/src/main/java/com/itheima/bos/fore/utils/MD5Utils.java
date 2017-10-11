package com.itheima.bos.fore.utils;

import org.springframework.util.DigestUtils;

/**  
 * ClassName:MD5Utils <br/>  
 * Function:  <br/>  
 * Date:     2017年9月25日 下午4:32:35 <br/>       
 */
public class MD5Utils {
  public static String md5(String password) {
    for (int i = 0; i < 5; i++) {
      password = DigestUtils.md5DigestAsHex(password.getBytes());
    }
    return password;
  }
}
  
