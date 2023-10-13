package HomeWorkPa;
import java.util.Arrays;
import java.util.Objects;
import  java.util.Scanner;

abstract class Massage {
    abstract int number(Car[] cars,parkSpot[] Spots);

    public static void main(String[] args) {

        Car[] cars=new Car[parkSpot.rexnum];
        cars[0]=new Car();
        parkSpot[] spots=new parkSpot[parkSpot.rexnum];

        for(int i=0;i<parkSpot.rexnum;i++)
        {
            spots[i]=new parkSpot();
            spots[i].isParting=false;
        }

        while(true)
        {
            System.out.println("欢迎进入停车场管理系统,请问您是车主还是管理员？\n车主 >> 0 管理员 >> 1");

            Scanner sc=new Scanner(System.in);
            int choose=sc.nextInt();
            if(choose==1)
            {
                System.out.println("请输入管理员密码：");
                for(int j=0;j<3;j++)
                {
                    String pw=sc.nextLine();
                    if(Objects.equals(pw, "114514"))
                    {
                        System.out.println("管理员您好");
                        break;
                    }
                    else if(!Objects.equals(pw, "114514") && j<2)
                    {
                        System.out.println("密码不正确，请重新输入：");
                    }
                    else
                    {
                        System.out.println("密码错误，程序结束");
                        System.exit(0);
                    }
                }
                Manager manager=new Manager();
                while(true)
                {
                    System.out.println("请选择操作：\n车辆进入>>0 车辆离开>>1 车辆总况>>2 停车位总况>>3 剩余停车位总数>>4");
                    int choosee= sc.nextInt();
                    switch (choosee) {
                        case 0 -> Manager.carIn(cars, spots);
                        case 1 -> Manager.carOut(cars, spots);
                        case 2 -> cars[0].number(cars, spots);
                        case 3 -> spots[0].number(cars, spots);
                        default -> System.out.println(manager.carOrSpotNumber());
                    }
                    System.out.println("本次操作结束，可继续。如果希望结束，请输入数字10");
                    if(sc.nextInt()==10) break;
                }
            }
            else
            {
                Driver driver=new Driver();
                System.out.println("欢迎来到车主缴费窗口，本停车场每小时2元，不足1小时按一小时计算");
                driver.payMoney(cars);
            }
        }
    }

}
class Car extends Massage{
    public static int num=0;
    String license_plate_number;
    String color;
    String driver;
    long start,end;
    public int number(Car[] cars,parkSpot[] Spots)
    {
        for(int i=0;i<num;i++)
        {
            System.out.println(cars[i].license_plate_number);
            System.out.println(cars[i].color);
            System.out.println(cars[i].driver);
            System.out.println(cars[i].start+","+cars[i].end);
        }
        return num;
    }
}
class parkSpot extends Massage{
    static final int MAXN=100;
    public static int rexnum=MAXN;
    boolean isParting=false;
    public int number(Car[] cars,parkSpot[] Spots)
    {
        for(int i=0;i<MAXN;i++)
        {
            System.out.print(i+"号车位状态：");
            if(Spots[i].isParting)  System.out.println("有车");
            else                    System.out.println("无车");
        }
        return rexnum;
    }
}
interface ManageSystem {
    public long money(Car a);
    public int carOrSpotNumber();
}
class Driver implements ManageSystem{
    public long money(Car a)
    {
        long k=180000;
        long total=(a.end-a.start)/k;
        if((a.end-a.start)%k!=0)
            total+=2;
        return total;
    }
    public int carOrSpotNumber()
    {
        return Car.num;
    }

    public void payMoney(Car[] cars)
    {
        System.out.println("离开的车辆编号");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
        cars[i].end=System.currentTimeMillis();
        System.out.println("你应当交款为："+money(cars[i]));
    }
}
class Manager implements ManageSystem{
    public long money(Car a)
    {
        long k=180000;
        long total=(a.end-a.start)/k;
        if((a.end-a.start)%k!=0)
            total+=2;
        return total;
    }
    public int carOrSpotNumber() { return parkSpot.rexnum; }
    public static void carIn(Car[] cars,parkSpot[] spots) {
        int i;
        for(i=0;i<parkSpot.rexnum;i++)
        {
            if(!spots[i].isParting)
            {
                spots[i].isParting=true;
                break;
            }
        }
        cars[i]=new Car();
        cars[i].start=cars[i].end=System.currentTimeMillis();
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入车身颜色：");
        cars[i].color=sc.nextLine();

        System.out.println("请输入车牌号：");
        cars[i].license_plate_number=sc.nextLine();

        System.out.println("请输入驾驶员姓名：");
        cars[i].driver=sc.nextLine();

        System.out.println("您的编号为："+i);
        
        Car.num++;
        parkSpot.rexnum--;
    }
    public static void carOut(Car[] cars,parkSpot[] spots) {
        System.out.println("要离开的汽车编号：");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
        spots[i].isParting=false;

        Car.num--;
        parkSpot.rexnum++;
    }

}