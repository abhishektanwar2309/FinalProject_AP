import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private String name;
    private int id;
    Category Inventory;
    public Warehouse(String name){
        //Inventory=new Category("mainWHCategory");
        this.Inventory=new Category("root");
        this.name=name;
    }
    public void displayCategories(){
        displayCategoriesHelper(Inventory);
    }
    public static void displayCategoriesHelper(Category root){
        Queue<Category> pending=new LinkedList<>();
        ((LinkedList<Category>) pending).add(root);
        while (!pending.isEmpty()){
            Category currentCategory=pending.poll();
            //printing the name of the main class first
            System.out.print(currentCategory.getName()+" : ");
            //now printing its sublists and also adding them in the queue
            //iterating over the sublist of the currentlist
            for (Category i:currentCategory.getSubCategory()){
                System.out.print(i.getName()+" , ");
                pending.add(i);
            }
            System.out.println();
        }
    }
    //Function to search a category and return it if found otherwise null is returned
    public Category searchCategory(String nameCategory){
        return  searchCategoryHelper(Inventory,nameCategory);
    }
    public static Category searchCategoryHelper(Category root,String nameCategory){
        if (root.getName().equals(nameCategory)){
            return root;
        }
        //else starting earch it in every child subtree using recursion
        Category check=null;
        for (Category i:root.getSubCategory()){
            check=searchCategoryHelper(i,nameCategory);
            if (check!=null){
                break;
            }
        }
        return check;
    }
    public void addCategory(String path,String newCategory) throws AlreadyPresentException{
        String pathArray[]=path.split(">");
        Category newcat=new Category(newCategory);
        if (searchCategory(newCategory)==null){
            addCategoryHelper(Inventory,pathArray,newCategory,0);
//            Category parentToNew=searchCategory(pathArray[pathArray.length-1]);
//            parentToNew.getSubCategory().add(newcat);
        }
        else{
            //throw an exception here please
            //System.out.println("Already present you dickface");
            throw new AlreadyPresentException();
        }
    }
    public void addCategoryHelper(Category root,String path[],String newCategory,int i){
        if (i==path.length){
            //adding it to the children of the root
            Category newcat=new Category(newCategory);
            root.getSubCategory().add(newcat);
            return;
        }
        //now otherwise we have to first get the categpry at i
        Category newroot=null;
        if (searchCategory(path[i])==null){
            Category present=new Category(path[i]);
            root.getSubCategory().add(present);
            newroot=present;
        }
        else{
            newroot=searchCategory(path[i]);
        }
        addCategoryHelper(newroot,path,newCategory,i+1);
    }
    //function to delete a category
    public void deleteCategory(String path){
        String inputPath[]=path.split(">");
        try {
            this.deleteCategoryHelper(this.Inventory, inputPath, 0);
        }catch (NotPresentException e){
            System.out.println("CategoryNotPresentException");
        }
    }
    private void deleteCategoryHelper(Category mainRoot,String[] inputPath,int i) throws NotPresentException{
        if (i==inputPath.length-1){
            if (this.searchCategory(inputPath[i])!=null){
                //getting the index of inputPath[i] in subcategory list of mainRoot
                int index=0;
                for (int j=0;j<mainRoot.getSubCategory().size();j++){
                    if ((mainRoot.getSubCategory().get(j).getName()).equals(inputPath[i])) {
                        index=j;
                        break;
                    }
                }
                //removing the element at index "index" from the subcategory list of mainRoot
                mainRoot.getSubCategory().remove(index);
            }
            else{
                System.out.println("Category not present");
            }
            return;
        }
        if (this.searchCategory(inputPath[i])!=null){
            System.out.println("Invalid Path");
            return;
        }
        Category newRoot=searchCategory(inputPath[i]);
        deleteCategoryHelper(newRoot,inputPath,i+1);
    }
    public static void main(String args[]){
        //making a stupid tree
        /*Category root=new Category("men");
        Category clothing=new Category("clothing");
        Category accessories=new Category("accessories");
        Category jeans=new Category("jeans");
        Category shirts=new Category("shirts");
        Category whiteShirt=new Category("whiteShirt");
        Category wallet=new Category("wallet");
        Category wearables=new Category("wearables");
        Category bands=new Category("band");
        Category watch=new Category("watch");
        root.getSubCategory().add(clothing);
        root.getSubCategory().add(accessories);
        clothing.getSubCategory().add(jeans);
        clothing.getSubCategory().add(shirts);
        shirts.getSubCategory().add(whiteShirt);
        accessories.getSubCategory().add(wallet);
        accessories.getSubCategory().add(wearables);
        wearables.getSubCategory().add(bands);
        wearables.getSubCategory().add(watch);
        Warehouse obj=new Warehouse("hello");
        obj.displayCategories();
        Category ans=obj.searchCategory("bangles");
        if (ans!=null){
            System.out.println("Found it");
        }
        else {
            System.out.println("Not here bitch");
        }*/
        /*
        clothing>shirts,purpleShirt
         */
        Warehouse obj=new Warehouse("hello");
        try {
            obj.addCategory("clothing>shirts", "purpleshirt");
            obj.addCategory("watches>rado", "copperBlack");
            obj.displayCategories();
            System.out.println("------------------------");
            obj.addCategory("clothing>shirts", "RedOnes");
            obj.displayCategories();
            System.out.println("------------------------");
            obj.addCategory("electronics>trimmers>philips", "razorsharp");
            obj.displayCategories();
            System.out.println("------------------------lllllllllllllllllll");
            obj.deleteCategory("watches");
            obj.deleteCategory("socks");
            obj.displayCategories();
            System.out.println("------------------------");
            obj.addCategory("watches", "rado");
            obj.displayCategories();
        }catch(AlreadyPresentException e){
            System.out.println("AlreadyPresentException");
        }
    }
}
