package com.yf.object;

/**
 * object对象操作类
 * @author ouyangyufeng
 * @date 2019/2/19
 */
public class ObjectUtils {

    /**
     * 对象实例方法
     * @param c
     * @return
     */
    public static Object convertObj(Class c){
        Object obj = null;
        try {
            obj = c.newInstance();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return obj;
    }

}
