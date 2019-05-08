package util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * This class realise BeanPostProcessor interface,
 * output information about bean
 * and set fields random value.
 */

@Slf4j
@Component
public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessBeforeInitialization: beanName = " + beanName + ", beanClass = " + bean.getClass().getSimpleName());
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectRandomInt.class)) {
                field.setAccessible(true);
                InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
                ReflectionUtils.setField(field, bean, getRandomIntInRange(annotation.min(), annotation.max()));
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private int getRandomIntInRange(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
