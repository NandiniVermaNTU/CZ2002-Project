package restuarantapp;
import java.util.ArrayList;
//import restuarantapp.MenuItem.Type;

public class Testing {

	
	public static void zixingTest() {
		/*MenuItem m1 = new MenuItem(1,"Fish and chips", (float) 5.20, OrderItemType.MainCourse,"good food");
		MenuItem m2 = new MenuItem(2,"Nuggets", (float) 2.20, OrderItemType.Sides,"good nuggets");
		OrderItem o1 = (OrderItem)m1;
		OrderItem o2 = (OrderItem)m2;
		MenuItem m3 = (MenuItem)o1;
		o1.printAttributes();*/
		/*ArrayList<MenuItem> mSet=new ArrayList<MenuItem>();
		mSet.add(m1);
		mSet.add(m2);
		PromotionalSetPackage p1 = new PromotionalSetPackage(4,"Awesome Set" , 7.00f,"desc",mSet);
		OrderItem oSet = (OrderItem)p1;
		oSet.printAttributes();
		//m1.printAttributes();
		MenuItem m3 = new MenuItem(3,"Orange Juice", (float) 1.20, OrderItemType.Drinks,"good juice");
		PromotionalSetPackage pSet = (PromotionalSetPackage)oSet;
		pSet.printAttributes();
		pSet.addPackageItem(m3);
		System.out.println("item 3 price: "+pSet.getPackageItems().get(2).getPrice());*/
		OrderItemController.init();
		//OrderItemController.printMenuItems(OrderItemType.MainCourse);
		/*OrderItemController.printMenuItems(OrderItemType.Drinks);*/
		//OrderItemController.printMenuItems(OrderItemType.Sides);
		//OrderItemController.printMenuItems(OrderItemType.Desserts);
		/*PromotionalSetPackage p = (PromotionalSetPackage) OrderItemController.getMenu("Set").get(0);
		for(int i = 0 ;i < p.getPackageItems().size();i++) {
			p.getPackageItems().get(i).printAttributes();
		}*/
		//System.out.println("Max id: "+OrderItemController.maxID);
		//MenuItem m2 = new MenuItem(2,OrderItemType.Sides,"Nuggets", (float) 2.20, "good nuggets");
		//m2.delete();
		//OrderItemController.addMenuItem(m2);
		((PromotionalSetPackage)OrderItemController.getMenu("Set").get(1)).removePackageItem(14);
	}
	
	
	public static void main(String args[]) {
		zixingTest();
	}

}
