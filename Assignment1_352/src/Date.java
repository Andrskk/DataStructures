import java.util.Objects;
import java.util.StringTokenizer;

public class Date {

    private String date;
    private int age;
    private boolean isSenior;

    public Date(String str)
    {
        this.date=str;
        StringTokenizer tokenizer = new StringTokenizer(str, "-");

        int day=Integer.parseInt(tokenizer.nextToken());
        int month=Integer.parseInt(tokenizer.nextToken());
        int year=Integer.parseInt(tokenizer.nextToken());
        if (year<1965)isSenior=true;
        else isSenior=false;

        age=2021-year;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public boolean isSenior()
    {
        return isSenior;
    }

    public void setSenior(boolean senior)
    {
        isSenior = senior;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return age == date1.age && isSenior == date1.isSenior && Objects.equals(date, date1.date);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(date, age, isSenior);
    }

    @Override
    public String toString()
    {
        return date/*+ "\nage: " + age + "\nisSenior: " + isSenior*/;
    }

    public int compareTo(Object o)
    {
        Date date1 = (Date) o;


        StringTokenizer tokenizer1 = new StringTokenizer(date1.date, "-");

        int day1=Integer.parseInt(tokenizer1.nextToken());
        int month1=Integer.parseInt(tokenizer1.nextToken());
        int year1=Integer.parseInt(tokenizer1.nextToken());

        StringTokenizer tokenizer2 = new StringTokenizer(this.date, "-");

        int day2=Integer.parseInt(tokenizer2.nextToken());
        int month2=Integer.parseInt(tokenizer2.nextToken());
        int year2=Integer.parseInt(tokenizer2.nextToken());

        if (year2>year1) return 1;
        else if (year2<year1) return -1;
        else
        {
            if (month2>month1) return 1;
            else if (month2<month1) return -1;
            else
            {
                if (day2>day1) return 1;
                else if (day2<day1) return -1;
                else return 0;
            }
        }
    }
}
