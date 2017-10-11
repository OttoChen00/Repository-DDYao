package com.itheima.bos.utils;

import java.util.UUID;

/**  
 * ClassName:UUIDUtils <br/>  
 * Function:  <br/>  
 * Date:     2017年9月20日 下午4:14:10 <br/>       
 */
public class UUIDUtils {
  public static String getId() {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase();
  }
}
  
