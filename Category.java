import java.util.ArrayList;

public class Category {
    private String name;
    static int id=0;
    int quantity;
    private ArrayList<Category> subCategory;
    //parameterized constructor
    public Category(String name){
        this.name=name;
        subCategory=new ArrayList<>();
        this.quantity=0;
        this.id++;
    }
    public Category(String name,int quantity){
        this.name=name;
        subCategory=new ArrayList<>();
        this.quantity=quantity;
        this.id++;
    }
    @Override
    public boolean equals(Object o1){
        if (o1!=null&&getClass()==o1.getClass()){
            Category o=(Category)o1;
            return this.getName().equals(o.getName());
        }
        else{
            return false;
        }
    }
    //--------------------------------getters and setters -------------------------------------------//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Category> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ArrayList<Category> subCategory) {
        this.subCategory = subCategory;
    }


}
