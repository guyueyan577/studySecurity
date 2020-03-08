package com.chenjw.security.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @Description: 自定义验证注解
 * @date: 2020-03-01
 */
@Target({ElementType.METHOD, ElementType.FIELD})  //注解的标注位置，这里取方法上和字段上
@Retention(RetentionPolicy.RUNTIME)  //表明时运行时的一个注解
@Constraint(validatedBy = MyConstraintValidator.class)    //表明校验注解:validatedBy 指明校验的类，判断由谁来执行
public @interface MyConstraint {

    /**
     * @Description: 一个注解不能为空，校验类注解必须由以下3个属性
     * @date: 2020-03-01
     */
    String message() default "{校验失败。}";   //校验不通过时发的信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
