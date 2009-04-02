package hotel.service;

public class CommandService {
	
	private static final CommandService instance = new CommandService();
	
	private static final ThreadLocal context = new ThreadLocal();

	private CommandService() {
	}
	
	public static CommandService getInstance() {
		return instance;
	}
	
	public CommandService open() {
		ExecutionContext executionContext = new ExecutionContext();
		context.set(executionContext);
		return instance;
	}
	
	public Object execute(Command command) {
		open();
		try {
			return command.execute(getCurrentExecutionContext());
		} catch (Exception e) {
			getCurrentExecutionContext().setRollbackOnly();
			throw new RuntimeException(e);
		} finally {
			close();
		}
	}
	
	public void close() {
		ExecutionContext executionContext = getCurrentExecutionContext();
		executionContext.close();
		context.remove();
	}
	
	public static ExecutionContext getCurrentExecutionContext() {
		return (ExecutionContext) context.get();
	}
	
	public Object assignId(Object obj) {
		open();
		try {
			if (getCurrentExecutionContext() != null) {
				return getCurrentExecutionContext().getSession().save(obj);
			}
			return null;
		} catch (Exception e) {
			getCurrentExecutionContext().setRollbackOnly();
			throw new RuntimeException(e);
		} finally {
			close();
		}
	}
}
