package com.chenjw.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * @Description: 验证注解所用的验证类
 * @date:   2020-03-01
 * ConstraintValidator: 后边的2个泛型意义：第一个表示注解是谁，第二个表示要验证的类型时什么，如果写的String,表示当前注解只能放在String上
 * 这个类实现了ConstraintValidator接口，可以作为Spring的一个bean使用，不需要添加@Component
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		// TODO 校验器的初始化
		System.out.println("MyConstraint 的 初始化");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO 验证方式（返回true表示校验成功，否则表示校验失败）
		boolean validSucessed = false;
		if (value != null) {
			validSucessed = true;
		}
		return validSucessed;
	}
}
