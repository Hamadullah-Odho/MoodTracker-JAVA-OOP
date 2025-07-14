
public class Mood {
    private String name;
    private String note;
    String date;
    String time;

    //setters for storing data
    public void setName(String name){this.name = name;}
    public void setNote(String note){this.note = note;}
    public void setDate(String date){this.date = date;}
    public void setTime(String time){this.time = time;}

    // setters for getting data
    public String getName(){return name;}
    public String getNote(){return note;}
    public String getDate(){return date;}
    public String getTime(){return time;}

}
