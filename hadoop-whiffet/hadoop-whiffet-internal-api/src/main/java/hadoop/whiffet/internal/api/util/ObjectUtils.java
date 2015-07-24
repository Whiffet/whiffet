

package hadoop.whiffet.internal.api.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/** 
 * @author  puppy 
 * @date 2015年7月3日 下午1:00:42 
 * 
 */

public class ObjectUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    public static Integer toInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        String str = obj.toString().trim();
        if (str.length() > 0) {
            try {
                return new Integer(str);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Integer toInteger(Object obj, Integer defaultValue) {
        Integer value = toInteger(obj);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public static Long toLong(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        String str = obj.toString().trim();
        if (str.length() > 0) {
            try {
                return new Long(str);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Long toLong(Object obj, Long defaultValue) {
        Long value = toLong(obj);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public static BigDecimal toBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        String str = obj.toString().trim();
        if (str.length() > 0) {
            try {
                return new BigDecimal(str);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static BigDecimal toBigDecimal(Object obj, BigDecimal defaultValue) {
        BigDecimal value = toBigDecimal(obj);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public static Boolean toBoolean(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        String str = obj.toString().trim();
        if (str.length() > 0) {
            try {
                return new Boolean(str);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }

    public static String toString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }

    public static String toStringTrim(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return ((String) obj).trim();
        }
        return obj.toString().trim();
    }

    public static String toStringTrim(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String) {
            return ((String) obj).trim();
        }
        return obj.toString().trim();
    }

    public static Map<String, Object> toHashMap(Object obj) {  
        Map<String, Object> map = new HashMap<String, Object>();  
        // 获取f对象对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();  
        for (int i = 0, len = fields.length; i < len; i++) {  
            String varName = fields[i].getName();  
            try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
                if (o != null){
                	logger.debug("{} value:{}",varName,o.toString());
                	map.put(varName, o.toString());  
                }else {
                	map.put(varName, null);
                }
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return map;  
    } 
}

