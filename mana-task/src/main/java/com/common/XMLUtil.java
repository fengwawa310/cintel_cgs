package com.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class XMLUtil
{
    /**
     * 将file类型的xml转换成对象
     * @throws JAXBException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static <T> T convertXmlFileToObject(Class<T> clazz, String xmlPath) throws JAXBException, UnsupportedEncodingException, FileNotFoundException
    {
        T xmlObject = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StreamSource xml = new StreamSource(xmlPath);
        xmlObject = (T) unmarshaller.unmarshal(xml,clazz).getValue();
        return xmlObject;
    }

//    public static <T> List<T> parseXmlToList(Class<T> topLevelClass,
//            String xmlLocation) throws Exception
//    {
//        JAXBContext context = JAXBContext.newInstance(Wrapper.class, topLevelClass);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        List<T> list = unmarshal(unmarshaller, topLevelClass, xmlLocation);
//        return list;
//    }

//    private static <T> List<T> unmarshal(
//            javax.xml.bind.Unmarshaller unmarshaller, Class<T> clazz,
//            String xmlLocation) throws JAXBException
//    {
//        StreamSource xml = new StreamSource(xmlLocation);
//        Wrapper<?> wrapper = unmarshaller
//              .unmarshal(xml, Wrapper.class).getValue();
//        List<?> temps = wrapper.getItems();
//        List<T> results = new ArrayList<>();
//        for(Object obj : temps)
//        {
//            if(obj.getClass().getName().equals(clazz.getName()))
//            {
//                results.add((T) obj);
//            }
//        }
//        return results;
//    }

//    public static void main(String[] args)
//    {
//        // 创建SAXReader对象
//        SAXReader reader = new SAXReader();
//        // 读取文件 转换成Document
//        Document document;
//        try
//        {
//            document = reader.read(new File("cnf/CollectDataCnf.xml"));
//            Element root = document.getRootElement();
//            listNode(root);
//        }
//        catch (DocumentException e)
//        {
//            e.printStackTrace();
//        }
//        // 获取根节点元素对象
//
//    }

//    public static void listNode(Element node)
//    {
//        System.out.println("当前节点的名称：" + node.getName());
//        // 获取当前节点的所有属性节点
//        List<Attribute> list = node.attributes();
//        // 遍历属性节点
//        for (Attribute attribute : list)
//        {
//            System.out.println(
//                    "属性" + attribute.getName() + ":" + attribute.getValue());
//        }
//        // 如果当前节点内容不为空，则输出
//        if (!(node.getTextTrim().equals("")))
//        {
//            System.out.println(node.getName() + "：" + node.getText());
//        }
//
//        // 同时迭代当前节点下面的所有子节点
//        // 使用递归
//        Iterator<Element> iterator = node.elementIterator();
//        while (iterator.hasNext())
//        {
//            Element e = iterator.next();
//            listNode(e);
//        }
//    }

}
