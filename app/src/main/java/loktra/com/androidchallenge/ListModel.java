package loktra.com.androidchallenge;

/**
 * Created by kbsan on 06-Apr-16.
 */
public class ListModel {

    private String name;
    private String commit;
    private String date;
    private String message;

    public ListModel(String name, String commit, String date, String message) {
        this.name = name;
        this.commit = commit;
        this.date = date;
        this.message = message;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return "["+date+","+name+","+commit+","+message+","+"]";
    }

}
