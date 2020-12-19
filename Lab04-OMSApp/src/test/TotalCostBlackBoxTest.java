package test;
import org.junit.Test;

import com.oms.bean.Order;
import com.oms.bean.OrderLine;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class TotalCostBlackBoxTest {
	@Test
	public void testOrder() {
		Order order = new Order();
		order.setCode("asd");
		order.setCustomerAddress("HN");
		order.setCustomerName("xinh");
		order.setCustomerPhoneNumber("0987654343");
		order.setId("id");
		ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
		orderLines.add(new OrderLine("123", "tes", 1, 120000, (float)3.0));
		orderLines.add(new OrderLine("123", "tes", 1, 130000, (float)1.3));
//		orderLines.add(new OrderLine("123", "tes", 1, 150000, (float)1.3));
		order.setOrderLines(orderLines);
		
//		assertEquals("Error in getBooks API!", order.getTotalCost(), 172000);
		assertTrue("kjhhfsajk", order.getTotalCost()==272000);
	}
	
}