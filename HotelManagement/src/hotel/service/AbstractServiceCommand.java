package hotel.service;

import hotel.model.BaseModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public abstract class AbstractServiceCommand implements Command {

	protected String command;
	
	protected Map condition;
	
	public AbstractServiceCommand(String command) {
		this.command = command;
	}
	
	public AbstractServiceCommand(String command, Map condition) {
		this.command = command;
		this.condition = condition;
	}
	
	public abstract Object execute(ExecutionContext executionContext);

	public Map getCondition() {
		return condition;
	}

	public void setCondition(Map condition) {
		this.condition = condition;
	}
	
	public void addCondition(String key, String value) {
		if (this.condition == null) {
			this.condition = new HashMap();
		}
		this.condition.put(key, value);
	}
	
	protected String[][] toArray(List list) {
		BaseModel model = null;
		if (list.size() > 0) {
			int count = 0;
			String[][] result = new String[list.size()][0];
			model = (BaseModel) list.get(0);
			//表头
			//result[count++] = model.getAttributeNames();
			String[] columns = model.getAttributeNames();
			//表体
			for (Iterator iter = list.iterator(); iter.hasNext(); ) {
				model = (BaseModel) iter.next();
				String[] row = new String[columns.length];
				for (int i = 0; i < columns.length; i++) {
					String attName = columns[i];
					Object attValue = model.getAttributeValue(attName);
					row[i] = attValue != null ? attValue.toString() : attValue + "";
				}
				result[count++] = row;
			}
			return result;
		} else {
			return null;
		}
	}

}
