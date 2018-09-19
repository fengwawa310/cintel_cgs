package com.controller.sys;

import java.lang.reflect.Method;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.entity.sys.SysUser;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import com.service.communal.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
public class LogAopAction {
    //注入service,用来将日志信息保存在数据库
    @Resource
    private SysLogService syslogservice;

    //配置接入点,如果不知道怎么配置,可以百度一下规则
    @Pointcut("execution(* com.controller..*.*(..))")
    private void controllerAspect(){}//定义一个切入点

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                String module = systemlog.module();
                String methods = systemlog.methods();
                try {
                    object = pjp.proceed();
                    Map<String, Object> keyAndValue = getKeyAndValue(object);
                    if (keyAndValue.containsKey("recordsTotal")){
                        if(Integer.parseInt(keyAndValue.get("recordsTotal").toString())>0){
                            //获取登录用户账户
                            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                            SysUser user = (SysUser) request.getAttribute("user");
                            //保存进数据库
                            syslogservice.insertSysLog(user,request,module+"-"+methods);
                        }
                    }else{
                        //获取登录用户账户
                        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                        SysUser user = (SysUser) request.getAttribute("user");
                        //保存进数据库
                        syslogservice.insertSysLog(user,request,module+"-"+methods);
                    }
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }

    /**
     * 单个对象的所有键值
     *
     * @param obj
     *            单个对象
     *
     * @return Map<String, Object> map 所有 String键 Object值 ex：{pjzyfy=0.00,
     *         xh=01, zzyl=0.00, mc=住院患者压疮发生率, pjypfy=0.00, rs=0, pjzyts=0.00,
     *         czydm=0037, lx=921, zssl=0.00}
     */
    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            /*
             * String type = f.getType().toString();//得到此属性的类型 if
             * (type.endsWith("String")) {
             * System.out.println(f.getType()+"\t是String"); f.set(obj,"12") ;
             * //给属性设值 }else if(type.endsWith("int") ||
             * type.endsWith("Integer")){
             * System.out.println(f.getType()+"\t是int"); f.set(obj,12) ; //给属性设值
             * }else{ System.out.println(f.getType()+"\t"); }
             */

        }
        System.out.println("单个对象的所有键值==反射==" + map.toString());
        return map;
    }
}
