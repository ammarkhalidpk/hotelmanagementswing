package hotel.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

public class HibernateProxyUtil {

	/**
	 * 得到到hibernate代理对象所代理的实际对象<p/> 该方法的执行成功必须在session打开的的时候 ytr 2008-10-9
	 * 
	 * @param o
	 * @return
	 */
	public static Object getImplementation(Object o) {
		if (o instanceof HibernateProxy || o instanceof PersistentCollection) {
			Hibernate.initialize(o);
			return ((HibernateProxy) o).getHibernateLazyInitializer()
					.getImplementation();
		}
		return o;
	}
}
