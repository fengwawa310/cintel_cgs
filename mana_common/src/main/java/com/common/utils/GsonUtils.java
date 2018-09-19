package com.common.utils;

/**
 * Created by admin on 2017/10/24.
 */

import com.google.gson.*;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by linjizong on 15/7/20.
 */
public class GsonUtils {

    private static final Logger log = Logger.getLogger(GsonUtils.class);

    public static Gson gson;


    /**
     * 自定义TypeAdapter ,null对象将被解析成空字符串
     */
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        public String read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;//原先是返回Null，这里改为返回空字符串
                }
                String nextString = reader.nextString();
                if(!nextString.equals("")){
                    return nextString;
                }
                if(nextString.equals("")){
                    return "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("字符串转换失败了！！！！！！！！！");
            }
            return null;
        }

        public void write(JsonWriter writer, String value) {
            try {
                if (value == null) {
                    /* 原来工具类
                    writer.nullValue();
                    return;
                    */
                    writer.value("");
                }else{
                    writer.value(value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

  /*  *//**
     * 自定义adapter，解决由于数据类型为Int,实际传过来的值为Float，导致解析出错的问题
     * 目前的解决方案为将所有Int类型当成Double解析，再强制转换为Int
     *//*
    public static final TypeAdapter<Number> INTEGER = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0;
            }
            try {
                double i = in.nextDouble();
                return (int) i;
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    };*/
    /**
     * 自定义adapter，解决由于数据类型为Int,实际传过来的值为Float，导致解析出错的问题
     * 目前的解决方案为将所有Int类型当成Double解析，再强制转换为Int
     */
    public static final TypeAdapter<Number> STRINGTOINTEGER = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
//                double i = in.nextDouble();
                String s = in.nextString();
                if(StringUtils.isNotBlank(s)){
                    return Integer.parseInt(s);
                }
                return null;
            } catch (NumberFormatException e) {
                log.error("字符串转换为Integer失败了！！！！！！！！！");
                throw new JsonSyntaxException(e);

            }
        }

        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    };
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new TypeAdapter<BigDecimal>() {
        @Override public BigDecimal read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String string = in.nextString();
                if (StringUtils.isNotBlank(string)) {
                    return new BigDecimal(string);
                }
                return null;
            } catch (NumberFormatException e){
                log.error("字符串转换为BigDecimal失败了！！！！！！！！！");
                throw new JsonSyntaxException(e);
            }
        }

        @Override public void write(JsonWriter out, BigDecimal value) throws IOException {
            out.value(value);
        }
    };

    public static final TypeAdapter<JsonElement> JSON_ELEMENT = new TypeAdapter<JsonElement>() {
        @Override public JsonElement read(JsonReader in) throws IOException {
            switch (in.peek()) {
                case STRING:
                    return new JsonPrimitive(in.nextString());
                case NUMBER:
                    String number = in.nextString();
                    return new JsonPrimitive(new LazilyParsedNumber(number));
                case BOOLEAN:
                    return new JsonPrimitive(in.nextBoolean());
                case NULL:
                    in.nextNull();
                    return JsonNull.INSTANCE;
                case BEGIN_ARRAY:
                    JsonArray array = new JsonArray();
                    in.beginArray();
                    while (in.hasNext()) {
                        array.add(read(in));
                    }
                    in.endArray();
                    return array;
                case BEGIN_OBJECT:
                    JsonObject object = new JsonObject();
                    in.beginObject();
                    while (in.hasNext()) {
                        object.add(in.nextName(), read(in));
                    }
                    in.endObject();
                    return object;
                case END_DOCUMENT:
                case NAME:
                case END_OBJECT:
                case END_ARRAY:
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override public void write(JsonWriter out, JsonElement value) throws IOException {
            if (value == null || value.isJsonNull()) {
                out.nullValue();
            } else if (value.isJsonPrimitive()) {
                JsonPrimitive primitive = value.getAsJsonPrimitive();
                if (primitive.isNumber()) {
                    out.value(primitive.getAsNumber());
                } else if (primitive.isBoolean()) {
                    out.value(primitive.getAsBoolean());
                } else {
                    out.value(primitive.getAsString());
                }

            } else if (value.isJsonArray()) {
                out.beginArray();
                for (JsonElement e : value.getAsJsonArray()) {
                    write(out, e);
                }
                out.endArray();

            } else if (value.isJsonObject()) {
                out.beginObject();
                for (Map.Entry<String, JsonElement> e : value.getAsJsonObject().entrySet()) {
                    out.name(e.getKey());
                    write(out, e.getValue());
                }
                out.endObject();

            } else {
                throw new IllegalArgumentException("Couldn't write " + value.getClass());
            }
        }
    };

    static {
        GsonBuilder gsonBulder = new GsonBuilder();
        gsonBulder.registerTypeAdapter(String.class, STRING);   //所有String类型null替换为字符串“”
        gsonBulder.registerTypeAdapter(Integer.class, STRINGTOINTEGER); //int类型对float做兼容
        gsonBulder.registerTypeAdapter(BigDecimal.class, BIG_DECIMAL); //int类型对float做兼容
        gsonBulder.registerTypeAdapter(Collection.class, JSON_ELEMENT); //int类型对float做兼容
        gsonBulder.registerTypeAdapter(List.class, JSON_ELEMENT); //int类型对float做兼容
        gsonBulder.registerTypeAdapter(Date.class, new LongDateTypeAdapter());
//        gsonBulder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
       /* //通过反射获取instanceCreators属性
        try {
            Class builder = (Class) gsonBulder.getClass();
            Field f = builder.getDeclaredField("instanceCreators");
            f.setAccessible(true);
            Map<Type, InstanceCreator<?>> val = (Map<Type, InstanceCreator<?>>) f.get(gsonBulder);//得到此属性的值
            //注册数组的处理器
            gsonBulder.registerTypeAdapterFactory(new CollectionTypeAdapterFactory(new ConstructorConstructor(val)));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/

        gson = gsonBulder.create();
    }

    /**
     * Json字符串 转为指定对象
     *
     * @param json json字符串
     * @param type 对象类型
     * @param <T>  对象类型
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T toBean(String json, Class<T> type) throws JsonSyntaxException {
        T obj = gson.fromJson(json, type);
        return obj;
    }

    /**
     * Json字符串 转为指定对象
     *
     * @return
     * @throws JsonSyntaxException
     */
    public static Gson getGson() throws JsonSyntaxException {
        return gson;
    }

}
