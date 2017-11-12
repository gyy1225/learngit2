public class Boy extends Thread {

    public String name;
    public Boy(String name){
        this.name=name;
    }
    Brick brick;
    public void run(){
        while (brick.brick>1){
        brick.brick-=2;
        System.out.println("我是"+this.name+"号程序猿，我挖了2块砖，还剩"+brick.brick+"块砖");
        }
        if(brick.brick==0){
            System.out.println("我是"+this.name+"号程序猿，砖挖完了，停下来看妹子");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
