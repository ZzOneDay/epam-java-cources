package com.epam.university.java.project.core.cdi.bean;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class BeanFactoryImpl implements BeanFactory {
    private HashMap<String, Object> singletonObjects = new HashMap<>();
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public BeanFactoryImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        //Bad idea, but it works.
        if (beanClass.isInterface()) {
            return (T) getBeanByInterface(beanClass);
        }

        String id = getIdOfNameClass(beanClass.getSimpleName());
        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(id);
        Object object;
        try {
            object = getBean(beanDefinition);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error in getBeanByClass");
        }
        return (T) object;
    }

    @Override
    public Object getBean(String beanName) {
        String id = getIdOfNameClass(beanName);
        Object object;
        try {
            object = getBean(beanDefinitionRegistry.getBeanDefinition(id));
        } catch (Exception e) {
            throw new IllegalArgumentException("Error in getBeanByName");
        }
        return object;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        Object object1 = getBean(beanName);
        //TODO BeanClass that, why?
        return (T) object1;
    }


    /**
     * Get Bean by BeanDefinition.
     * <p>
     * Main method of this Factory. Other method have link for this method.
     * This method create object of necessary class,
     * and match object params this beanDefinition params,
     * in finally we have object create by beanDefinition information.
     * </p>
     * @param beanDefinition information about bean from XML file.
     * @param <T> Generic because we can return any class object.
     * @return new Object necessary class from beanDefinition.
     * @throws Exception about Read beanDefinition or work with singleton bean.
     */
    public <T> T getBean(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition == null) {
            throw new IllegalArgumentException("BeanDefinition is Null!");
        }
        String id = beanDefinition.getId();
        String className = beanDefinition.getClassName();
        String init = beanDefinition.getPostConstruct();
        //TODO what param init do?
        String scope = beanDefinition.getScope();
        Collection<BeanPropertyDefinition> properties = beanDefinition.getProperties();
        Class<T> someClass = (Class<T>) Class.forName(className);
        T bean = someClass.getDeclaredConstructor().newInstance();
        Field[] fields = bean.getClass().getDeclaredFields();

        if ("singleton".equals(scope)) {
            if (singletonObjects.containsKey(id)) {
                return (T) singletonObjects.get(id);
            }
        }

        if (properties != null) {
            for (BeanPropertyDefinition property : properties) {
                Field field = getFieldOfName(property.getName(), fields);
                String typeOfField = field.getGenericType().getTypeName();
                field.setAccessible(true);

                if (property.getRef() != null) {
                    BeanDefinition other = beanDefinitionRegistry
                            .getBeanDefinition(property.getRef());
                    Object object = getBean(other);
                    field.set(bean, object);
                }

                if (typeOfField.equals(String.class.getTypeName())) {
                    field.set(bean, property.getValue());
                    continue;
                }

                if (typeOfField.equals(int.class.getTypeName())
                        || typeOfField.equals(Integer.class.getTypeName())) {
                    Integer value = Integer.parseInt(property.getValue());
                    field.set(bean, value);
                    continue;
                }

                //TODO Обработка list and Map.

            }
        }

        if ("singleton".equals(scope)) {
            if (!singletonObjects.containsKey(id)) {
                singletonObjects.put(id, bean);
            } else {
                throw new IllegalArgumentException("Object is singleton, "
                        + "but we can find it in singletonList");
            }
        }

        return bean;
    }


    private Field getFieldOfName(String propertyName, Field[] fields) {
        for (Field field : fields) {
            if (propertyName.equals(field.getName())) {
                return field;
            }
        }
        throw new IllegalArgumentException(
                "Field doesn't find in class of propertyName: " + propertyName);
    }


    private String getIdOfNameClass(String unCorrectName) {
        String firstPart = unCorrectName.substring(0, 1);
        String secondPart = unCorrectName.substring(1);
        return firstPart.toLowerCase() + secondPart;
    }

    private Object getBeanByInterface(Class interfaceClass) {
        //It's very bad idea, but it works)
        String name = interfaceClass.getSimpleName().replaceAll("Interface", "");
        return getBean(getIdOfNameClass(name));
    }
}
