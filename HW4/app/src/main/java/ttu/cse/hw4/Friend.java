package ttu.cse.hw4;

import java.io.Serializable;

public class Friend implements Serializable {
    String  ZodiacC;
    int icon;
    String date;
    String  ZodiacE;

    public Friend(String zodiacC, int icon, String date, String zodiacE) {
        ZodiacC = zodiacC;
        this.icon = icon;
        this.date = date;
        ZodiacE = zodiacE;
    }

    public String getZodiacC() {
        return ZodiacC;
    }

    public void setZodiacC(String zodiacC) {
        ZodiacC = zodiacC;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getZodiacE() {
        return ZodiacE;
    }

    public void setZodiacE(String zodiacE) {
        ZodiacE = zodiacE;
    }
}
