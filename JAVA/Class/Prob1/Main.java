import java.util.*;
import java.io.*;

class TV {
    private String manufacturer;
    private int year;
    private int inch;
    
    public TV(String manufacturer, int year, int inch) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.inch = inch;
    }
    
    @Override
    public String toString() {
        return manufacturer + "에서 만든 " + year + "형 " + inch + "인치 TV";
    }
}

public class Main {
    public static void main(String[] args) {
        TV tv = new TV("LG", 2017, 27);
        System.out.println(tv);
    }
}