//Modify this class. Use a better data structure. Current one has many issues(Only works for correct inputs).

public class ResellersNameIdPairs {
    String name[];
    String id[];
    int top;

    ResellersNameIdPairs(int size){
        name=new String[size];
        id=new String[size];
    }

    public String getName(int index){
        return name[index];
    }

    public void setName(int index,String name){
        this.name[index]=name;
    }

    public String getId(int index){
        return id[index];
    }

    public void setId(int index,Long id){
        top=index;
        this.id[index]= String.valueOf(id);
    }

    public int getTopIndex(){
        return top;
    }
}