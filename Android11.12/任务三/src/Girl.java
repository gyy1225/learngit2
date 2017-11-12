 class Girl extends Thread {
    public String name;
    public Girl(String name){
        this.name=name;
    }
    Brick brick;

    public void run(){
        while (brick.brick>=0&&brick.brick<200){
            brick.brick+=1;
            System.out.println("我是"+this.name+"号妹子，我补了1块砖，还剩"+brick.brick+"块砖");
        }
       if(brick.brick==200){
            System.out.println("我是"+this.name+"号妹子，墙补完了，停下来化妆");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
