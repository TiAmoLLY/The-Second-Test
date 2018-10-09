import com.oraclesdp.common.SystemIn;

public class Market {
	static int[] bianhao = new int[20];// 存储所有编号的数组
	static String[] mingzi = new String[20];// 商品名字
	static float[] jiage = new float[20];// 价格
	static int[] shuliang = new int[20];// 数量
	static int weizhi = 0;
	static String userName = "lly";
	static String passWord = "2016117314";

	public static void main(String[] args) {
		huanying();
	}

	public static void huanying() {
		System.out.println("********************");
		System.out.println("***欢迎来到小骆驼便利店***");
		System.out.println("********************");
		denglu();
	}

	public static void denglu() {
		System.out.println("主页面");
		System.out.println("1.登陆");
		System.out.println("2.退出");
		System.out.print("请输入您的选择：");
		int yourChoice = SystemIn.nextInt();
		System.out.println(yourChoice);
		if (yourChoice == 1) {
			int count = 0;// 执行三次判断，输入错误达到三次结束
			while (true) {
				System.out.println("请输入您的用户名：");
				String username = SystemIn.nextLine();
				System.out.println("请输入您的密码：");
				String password = SystemIn.nextLine();
				count++;
				if (username.equals(userName) && password.equals(passWord)) {
					System.out.println("登陆成功");// 登录成功之后应该进入主菜单
					mainMenu();
					break;
				} else {
					System.err.println("您输入的用户名或密码错误");
					if (count == 3) {
						break;
					} else {
						continue;
					}
				}

			}
		} else if (yourChoice == 2) {
			System.exit(0);// 退出程序
		}

	}

	public static void mainMenu() {
		System.out.println("1.商品管理");
		System.out.println("2.用户管理");
		int i = SystemIn.nextInt();
		switch (i) {
		case 1: {
			shangPinGuanLi();
		}
		case 2: {
			// 用户管理
		}
		}

	}

	public static void shangPinGuanLi() {
		System.out.println("操作主页面菜单");
		System.out.println("1.添加商品");
		System.out.println("2.删除商品");
		System.out.println("3.修改商品");
		System.out.println("4.查看商品信息");
		System.out.println("5.返回上一层");
		System.out.println("6.退出");
		System.out.println("请输入您的选择：");
		int choice = SystemIn.nextInt();
		switch (choice) {
		case 1: {
			addSomething();
			break;
		}
		case 2: {
			delSomething();
			break;
		}
		case 3: {
			xiuSomething();
			break;
		}
		case 4: {
			seeSomething();
			break;
		}
		case 5: {
			goBack();
			break;
		}
		case 6: {
			tuiChu();
			break;
		}
		default:
			break;
		}

	}

	public static void addSomething() {
		System.out.println("请输入要添加的商品的编号:");
		int bh = SystemIn.nextInt();// 获取输入的编号了
		bianhao[weizhi] = bh;
		System.out.println("请输入要添加的商品的名字:");
		String name = SystemIn.nextLine();
		mingzi[weizhi] = name;
		System.out.println("请输入要添加的商品的价格:");
		float price = SystemIn.nextFloat();
		jiage[weizhi] = price;
		System.out.println("请输入要添加的商品的数量:");
		int count = SystemIn.nextInt();
		shuliang[weizhi] = count;

		// 每添加一个商品后将位置变量++，相当于是下一次从下一个位置放置数据
		weizhi++;
		// System.out.println(bianhao[0]+"---"+mingzi[0]+"---"+jiage[0]+"---"+shuliang[0]);
		// System.out.println("add success");
		// shangPinGuanLi();
		System.out.println("该商品信息已成功录入，请进行下一步操作！");
		System.out.println("继续输入请按1、返回上一层请按2、退出系统请按3！");
		int count2 = SystemIn.nextInt();
		boolean flag = true;
		while (flag == true) {
			if (count2 == 1) {
				addSomething();
				flag = false;
			} else if (count2 == 2) {
				shangPinGuanLi();
				flag = false;
			} else if (count2 == 3) {
				tuiChu();
				flag = false;
			} else {
				System.err.println("您输入的信息有误，请重新输入！");
				count2 = SystemIn.nextInt();
			}
		}

	}

	public static void delSomething() {
		System.out.println("请输入您想要删除的商品的名称：");
		String name = SystemIn.nextLine();
		for (int i = 0; i < mingzi.length; i++) {
			if (name.equals(mingzi[i])) {
				System.out.println("您要删除的商品信息为：");
				System.out.println(
						"商品名称:"+mingzi[i]   +"商品编号:"+bianhao[i]   +"商品价格："+ jiage[i]   +"商品数量："+shuliang[i]);
				mingzi[i] = null;
				bianhao[i] = 0;
				jiage[i] = 0;
				shuliang[i] = 0;

			}

		}
		System.out.println("该商品信息已成功删除，请进行下一步操作！");
		System.out.println("继续删除请按1、返回上一层请按2、退出系统请按3！");
		int count2 = SystemIn.nextInt();
		boolean flag = true;
		while (flag == true) {
			if (count2 == 1) {
				delSomething();
				flag = false;
			} else if (count2 == 2) {
				shangPinGuanLi();
				flag = false;
			} else if (count2 == 3) {
				tuiChu();
				flag = false;
			} else {
				System.err.println("您输入的信息有误，请重新输入！");
				count2 = SystemIn.nextInt();
			}
		}

	}

	public static void xiuSomething() {
		System.out.println("请选择您想要修改的商品名称：");
		String name=SystemIn.nextLine();
		for(int i=0;i<mingzi.length;i++) {
			if(name.equals(mingzi[i])) {
				System.out.println("您想要修改的商品的所有信息为：");
				System.out.println(
						"商品名称：" + mingzi[i] + "\t"+"商品编号：" + bianhao[i] +"\t" +"商品价格：" + jiage[i] +"\t"+"商品数量：" + shuliang[i]);
				System.out.println("修改商品信息");
				System.out.println("1.修改商品名字");
				System.out.println("2.修改商品编号");
				System.out.println("3.修改商品价格");
				System.out.println("4.修改商品数量");
				System.out.println("5.返回上一层");
				System.out.println("6.退出系统");
				System.out.println("请输入您的选择：");
				int count=SystemIn.nextInt();
				switch(count) {
				case 1:{
					System.out.println("请输入修改后的商品名字：");
					String mz=SystemIn.nextLine();
					mingzi[i]=mz;
					break;
				}
				case 2:{
					System.out.println("请输入修改后的商品编号：");
					int bh=SystemIn.nextInt();
					bianhao[i]=bh;
					break;
				}
				case 3:{
					System.out.println("请输入修改后的价格：");
					float jg=SystemIn.nextFloat();
					jiage[i]=jg;
					break;
				}
				case 4:{
					System.out.println("请输入修改后的数量：");
					int sl=SystemIn.nextInt();
					shuliang[i]=sl;
					break;
				}
				case 5:{
					goBack();
					
				}
				case 6:{
					tuiChu();
				}
				default:
					break;
				
				}
			} 
		}
		System.out.println("您已经完成相关部分修改，请选择后续操作");
		System.out.println("1.继续修改");
		System.out.println("2.返回上一层");
		System.out.println("3.退出系统");		
		int i=SystemIn.nextInt();
		switch(i) {
		case 1:{
			xiuSomething();
			break;
		}
		case 2:{
			shangPinGuanLi();
			break;
		}
		case 3:{
			tuiChu();
			break;
		}
		default:{
			System.err.println("您输入的选择有误！！！");
			break;
		}
			
		}
		
		
	}

	public static void seeSomething() {
		for (int i = 0; i < weizhi; i++) {
			if(mingzi[i]!=null) {
			System.out.println(
					"商品名称：" + mingzi[i] + "\t"+"商品编号：" + bianhao[i] + "\t"+"商品价格：" + jiage[i] + "\t"+"商品数量：" + shuliang[i]);
		}
		System.out.println("该商品信息已成功显示，请进行下一步操作！");
		System.out.println("返回上一层请按1、退出系统请按2！");
		int count2 = SystemIn.nextInt();
		boolean flag = true;
		while (flag == true) {
			if (count2 == 1) {
				shangPinGuanLi();
				flag = false;
			} else if (count2 == 2) {
				tuiChu();
				flag = false;
			} 
			  else {
				System.err.println("您输入的信息有误，请重新输入！");
				count2 = SystemIn.nextInt();
			}
		}
	  }

	}

	public static void goBack() {
		mainMenu();

	}

	public static void tuiChu() {
		System.out.println("您已成功退出系统！！！");
		System.exit(0);

	}
}
