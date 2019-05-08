package dao;

public class Paper {
    private String title;
    private String answer;
    private String icon;
    public Paper(String title, String answer) {
        this.title = title;
        this.answer = answer;
    }
    public Paper(String title, String answer, String icon) {
        this.title = title;
        this.answer = answer;
        this.icon = icon;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public boolean equals(Object obj){
        if (this==obj){
            return true;
        }
        if (obj instanceof  Paper){
            Paper anotherPaper=(Paper)obj;
         if(this.title.substring(0,this.title.indexOf("<br>")).equals(anotherPaper.title.substring(0,anotherPaper.title.indexOf("<br>"))))return true;
        }
        return false;

    }
    public int hashCode(){
     return  this.title.substring(0,this.title.indexOf("<br>")).hashCode();

    }

}
