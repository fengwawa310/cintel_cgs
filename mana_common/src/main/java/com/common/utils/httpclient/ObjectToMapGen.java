package com.common.utils.httpclient;

import net.sf.json.JSONObject;

import java.util.Map;

public class ObjectToMapGen<T,V,W> {

    private T ob1;
    private V ob2;
    private W ob3;

    private Map map;

    public ObjectToMapGen() {
    }
    public ObjectToMapGen(T t) {
        JSONObject jsonObject1=null;
        if (t!=null) {
            String userS = JsonUtil.toJson(t);
            jsonObject1 = JSONObject.fromObject(userS);
        }
        JSONObject jsonThree = new JSONObject();
        if (jsonObject1!=null) {
            jsonThree.putAll(jsonObject1);
        }
        Map<String, Object> map = MapJavaBeanConvert.toMap(jsonThree);
        this.map=map;
    }
    /*三参构造*/
    public ObjectToMapGen(T t, V v, W w) {
        JSONObject jsonObject1=null;
        if (t!=null) {
            String userS = JsonUtil.toJson(t);
            jsonObject1 = JSONObject.fromObject(userS);
        }
        JSONObject jsonObject2=null;
        if (v!=null) {
            String pageVOS = JsonUtil.toJson(v);
            jsonObject2 = JSONObject.fromObject(pageVOS);
        }
        JSONObject jsonObject3=null;
        if (v!=null) {
            String pageVOS = JsonUtil.toJson(w);
            jsonObject3 = JSONObject.fromObject(pageVOS);
        }
        JSONObject jsonThree = new JSONObject();
        if (jsonObject1!=null) {
            jsonThree.putAll(jsonObject1);
        }
        if (jsonObject2!=null) {
            jsonThree.putAll(jsonObject2);
        }
        if (jsonObject3!=null) {
            jsonThree.putAll(jsonObject3);
        }
        Map<String, Object> map = MapJavaBeanConvert.toMap(jsonThree);
        this.map=map;
    }
    /*双参构造*/
    public ObjectToMapGen(T t, V v) {
        JSONObject jsonObject1=null;
        if (t!=null) {
            String userS = JsonUtil.toJson(t);
            jsonObject1 = JSONObject.fromObject(userS);
        }
        JSONObject jsonObject2=null;
        if (v!=null) {
            String pageVOS = JsonUtil.toJson(v);
            jsonObject2 = JSONObject.fromObject(pageVOS);
        }

        JSONObject jsonThree = new JSONObject();
        if (jsonObject1!=null) {
            jsonThree.putAll(jsonObject1);
        }
        if (jsonObject2!=null) {
            jsonThree.putAll(jsonObject2);
        }
        Map<String, Object> map = MapJavaBeanConvert.toMap(jsonThree);
        this.map=map;
    }

    public T getOb1() {
        return ob1;
    }

    public void setOb1(T ob1) {
        this.ob1 = ob1;
    }

    public V getOb2() {
        return ob2;
    }

    public void setOb2(V ob2) {
        this.ob2 = ob2;
    }

    public W getOb3() {
        return ob3;
    }

    public void setOb3(W ob3) {
        this.ob3 = ob3;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}