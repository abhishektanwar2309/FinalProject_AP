public class User {
    String name;
    static int id=0;
    public User(){
        //makes sure each user has a unique id
        id++;
    }
    public User(String name){
        this.name=name;
    }
    @Override
    public boolean equals(Object o1){
        if (o1!=null&&getClass()==o1.getClass()){
            return true;
        }
        else{
            return false;
        }
    }
}
