package hotel.test.service;

import hotel.service.Command;
import hotel.service.CommandService;
import hotel.service.ExecutionContext;

import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.setName("大黄");
		cat.setAge(5);
		CatServiceCommand csc = new CatServiceCommand(cat);
		CommandService.getInstance().execute(csc);
		Object cats = CommandService.getInstance().execute(new CatQueryCommand("count"));
		System.out.println(cats);
		List allCats = (List) CommandService.getInstance().execute(new CatQueryCommand("getAll"));
		for (Iterator iter = allCats.iterator(); iter.hasNext(); ) {
			Cat item = (Cat) iter.next();
			System.out.println("Name:" + item.getName() + ", Age:" + item.getAge());
		}
	}
}

class CatQueryCommand implements Command {
	private String command;
	
	public CatQueryCommand(String command) {
		this.command = command;
	}

	public Object execute(ExecutionContext executionContext) {
		if (this.command.equalsIgnoreCase("count")) {
			return executionContext.getSession().createQuery("select count(*) from hotel.test.service.Cat cat").uniqueResult();	
		} else if (this.command.equalsIgnoreCase("getAll")) {
			return executionContext.getSession().createQuery("select cat from hotel.test.service.Cat cat").list();
		} else {
			return null;
		}		
	}
}

class CatServiceCommand implements Command {

	private Cat cat;

	public CatServiceCommand(Cat cat) {
		this.cat = cat;
	}

	public Object execute(ExecutionContext executionContext) {
		return executionContext.getSession().save(cat);
	}

}