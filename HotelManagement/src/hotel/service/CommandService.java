package hotel.service;

public class CommandService {
	
	private static final CommandService instance = new CommandService();

	private CommandService() {
	}
	
	public static CommandService getInstance() {
		return instance;
	}
	
	public Object execute(Command command) {
		ExecutionContext executionContext = new ExecutionContext();
		try {
			return command.execute(executionContext);
		} catch (Exception e) {
			executionContext.setRollbackOnly();
			throw new RuntimeException(e);
		} finally {
			executionContext.close();
		}
	}
}
