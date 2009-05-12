package hotel;

import hotel.service.CommandService;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;

/**
 * 
 */
public class ContextInerceptor extends AbstractInterceptor implements
		InitializingBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1519585893721039728L;


	public String intercept(ActionInvocation arg0) throws Exception {
		CommandService commandService = CommandService.getInstance().open();
		String result = null;
		try {
			//设置AbstractJbpmAction中的jbpmContext，并验证登录
			Object action = arg0.getAction();
			if (action instanceof BaseAction) {
				try {
					((BaseAction)action).setCommandService(commandService);
				} catch (Exception e) {
					//发布异常到异常处理的action，实现与默认拦截器ExceptionMappingInterceptor相同的功能
					List exceptionMappings = arg0.getProxy().getConfig()
							.getExceptionMappings();
					String mappedResult = findResultFromExceptions(
							exceptionMappings, e);
					if (mappedResult != null) {
						publishException(arg0, new ExceptionHolder(e));
						return mappedResult;
					}
				}
			}
			result = arg0.invoke();
		} finally {
			commandService.close();
		}
		return result;
	}


	public void afterPropertiesSet() throws Exception {
		System.out.println();
	}

	
	
	/*/
	 测试
	//调用action的prepare，实现与默认的拦截器PrepareInterceptor相同的功能
	Object action = arg0.getAction();
	if (action instanceof Preparable) {
		try {
			PrefixMethodInvocationUtil.invokePrefixMethod(arg0,
					new String[] { "prepare", "prepareDo" });
		} catch (Exception e) {
			System.out.println("调用指定前最名称的方法出现异常");
		}
	}
	//*/
	private String findResultFromExceptions(List exceptionMappings, Throwable t) {
		String result = null;
		if (exceptionMappings != null) {
			int deepest = 2147483647;
			for (Iterator iter = exceptionMappings.iterator(); iter.hasNext();) {
				ExceptionMappingConfig exceptionMappingConfig = (ExceptionMappingConfig) iter
						.next();
				int depth = getDepth(exceptionMappingConfig
						.getExceptionClassName(), t);
				if ((depth >= 0) && (depth < deepest)) {
					deepest = depth;
					result = exceptionMappingConfig.getResult();
				}
			}
		}

		return result;
	}

	private int getDepth(String exceptionMapping, Throwable t) {
		return getDepth(exceptionMapping, t.getClass(), 0);
	}

	private int getDepth(String exceptionMapping, Class exceptionClass,
			int depth) {
		if (exceptionClass.getName().indexOf(exceptionMapping) != -1) {
			return depth;
		}

		if (exceptionClass.equals(Throwable.class))
			return -1;

		return getDepth(exceptionMapping, exceptionClass.getSuperclass(),
				depth + 1);
	}

	private void publishException(ActionInvocation invocation,
			ExceptionHolder exceptionHolder) {
		invocation.getStack().push(exceptionHolder);
	}
}
