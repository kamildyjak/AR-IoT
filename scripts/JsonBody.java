package project;

public class JsonBody {

    private StringBuilder stringBuilder;
    private boolean first;

    public JsonBody(){
        stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        first = true;
    }

    public void addParameter(String name, String val){
        if(!first){
            stringBuilder.append(",\n");
        }else{
            first = false;
        }
        stringBuilder.append("\t\"" + name  + "\" : \"" + val + "\"");
    }

    public void addParameter(String name, int val){
        if(!first){
            stringBuilder.append(",\n");
        }else{
            first = false;
        }
        stringBuilder.append("\t\"" + name  + "\" : " + val);
    }

    public String getBody(){
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

}
