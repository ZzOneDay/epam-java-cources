package com.epam.university.java.project.core.cdi.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class BeanFactoryImpl implements BeanFactory {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public BeanFactoryImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        try {
            Object o = getBean(beanDefinitionRegistry.getBeanDefinition(beanName));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return null;
    }

//    <beans>
//    <bean id="parentBean" class="com.epam.university.java.project.core.cdi.context.ParentBean"
//    init="init" scope="singleton">
//
//        <property name="property1" value="value1" />
//        <property name="property2" value="10" />
//        <property name="childBean" ref="childBean" />
//    </bean>

    @SuppressWarnings("unchecked")
    public <T> T getBean(BeanDefinition beanDefinition) throws Exception {
        String id = beanDefinition.getId();
        String className = beanDefinition.getClassName();
        String init = beanDefinition.getPostConstruct();
        String scope = beanDefinition.getScope();
        Collection<BeanPropertyDefinition> properties = beanDefinition.getProperties();

        Class<T> someClass = (Class<T>) Class.forName(className);
        T bean = someClass.newInstance();
        Field[] fields = bean.getClass().getDeclaredFields();

        for (BeanPropertyDefinition property : properties) {
            Field field = getFieldOfName(property.getName(), fields);
            String typeOfField = field.getGenericType().getTypeName();
            field.setAccessible(true);

            if (property.getRef() != null) {
                System.out.println("Стопе");
                BeanDefinition other = beanDefinitionRegistry.getBeanDefinition(property.getRef());
                Object object = getBean(other);
                field.set(bean, object);
                //Тут остановился


            }

            if (typeOfField.equals(String.class.getTypeName())) {
                field.set(bean, property.getValue());
                continue;
            }

            if (typeOfField.equals(int.class.getTypeName()) ||
                    typeOfField.equals(Integer.class.getTypeName())) {
                Integer value = Integer.parseInt(property.getValue());
                field.set(bean, value);
                continue;
            }

            //TODO Обработка list and Map. ДОбираться до проверок только через Дебагер


            System.out.println(234);
        }
        return bean;
    }


    private Field getFieldOfName(String propertyName, Field[] fields) {
        //Property name
        for (Field field : fields) {
            if (propertyName.equals(field.getName())) {
                return field;
            }
        }
        throw new IllegalArgumentException("Field doesn't find in class of propertyName: " + propertyName);
    }
}
