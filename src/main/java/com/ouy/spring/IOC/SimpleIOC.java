package com.ouy.spring.IOC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SimpleIOC {

    private Map<String,Object> beanMap = new HashMap<>();

    public SimpleIOC(String location) throws Exception{
        loadBean(location);
    }

    public Object getBean(String name) {
        Object bean = beanMap.get(name);
        if(bean == null){
            throw new IllegalArgumentException("there is no bean with name "+name);
        }

        return bean;
    }

    private void loadBean(String location) throws Exception{
        FileInputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");
                
                Class beanClass = null;

                try {
                    beanClass = Class.forName(className);
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                    return;
                }

                Object bean = beanClass.newInstance();

                NodeList property = ele.getElementsByTagName("property");
                for (int j = 0; j < property.getLength(); j++) {
                    Node propertyNode = property.item(j);
                    if(propertyNode instanceof Element){
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");

                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);
                        if(value != null && value.length() > 0){
                            declaredField.set(bean,value);
                        }else {
                            String ref = propertyElement.getAttribute("ref");
                            if(ref == null || ref.length() == 0){
                                throw  new IllegalArgumentException("ref config error");
                            }

                            declaredField.set(bean,getBean(ref));
                        }
                        registerBean(id,bean);
                    }
                }
            }
        }
    }

    private void registerBean(String id,Object bean){
        beanMap.put(id,bean);
    }

}
