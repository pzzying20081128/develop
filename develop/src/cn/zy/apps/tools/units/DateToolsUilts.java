package cn.zy.apps.tools.units ;

import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.util.Calendar ;
import java.util.Date ;
import java.util.GregorianCalendar ;

public class DateToolsUilts {
    
    

    public static enum DateType {
        yyyy, MM, dd, HH, mm, ss, ZZZ
    }

    private static String[] days = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" } ;

    private static String[] weeks = new String[] { "第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第日周" } ;
    
    
    
    /**
     * "yyyy-MM-dd_hh_mm"
     * 
     * @param date
     * @param type
     * @return
     * @throws ParseException
     */
    public static Date paserDate(String date, String type) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(type);
        return f.parse(date);
    }

    

    public static GregorianCalendar instance(Date date) {
        GregorianCalendar gc = new GregorianCalendar() ;
        gc.setTime(date) ;
        return gc ;
    }

    /**
     * 增/减  天数
     * @param date
     * @param i
     * @return
     * @throws ParseException
     */
    public static Date addDay(Date date, int i) throws ParseException {
        GregorianCalendar gc = new GregorianCalendar() ;
        gc.setTime(date) ;
        gc.add(GregorianCalendar.DATE, i) ;
        return gc.getTime() ;
    }

    /**
     *  增/减  月
     * @param date
     * @param i
     * @return
     */
    public static Date addMonth(Date date, int i) {
        GregorianCalendar gc = new GregorianCalendar() ;
        gc.setTime(date) ;
        gc.add(GregorianCalendar.MONTH, i) ;
        return gc.getTime() ;
    }

    /**
     *  增/减  周
     * @param date
     * @param i
     * @return
     */
    public static Date addWeek(Date date, int i) {
        GregorianCalendar gc = new GregorianCalendar() ;
        gc.setTime(date) ;
        gc.add(GregorianCalendar.WEEK_OF_YEAR, i) ;
        return gc.getTime() ;
    }

    /**
     * 获得指定时间当月的周数
     * 
     * @param date
     * @return
     */
    public static int getweek(Date date) {
        GregorianCalendar gc = instance(date) ;
        int months = gc.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH) ;
        return months ;

    }

   
    /**
     * 获得指定时间在一周的位置
     * 
     * @param date
     * @return
     */
    public static int getPositionInWeek(Date date) {
        GregorianCalendar gc = instance(date) ;
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK) ;
        return dayOfWeek ;
    }

    /**
     * 获得指定时间在这个月的位置
     * 
     * @param date
     * @return
     */
    public static int getPositionInMonth(Date date) {
        GregorianCalendar gc = instance(date) ;
        int dayOfWeek = gc.get(Calendar.DAY_OF_MONTH) ;
        return dayOfWeek ;
    }

    /**
     * 获得指定时间在一周的位置(中国表示法) 我们的 常规 想法
     * 
     * @param date
     * @return
     */
    public static int getPositionInWeekZH(Date date) {
        int dayOfWeek = getPositionInWeek(date) ;
        if (dayOfWeek == 1) return 7 ;
        else
            return dayOfWeek - 1 ;

    }

    /**
     * 获得指定时间的在一周的星期号
     * 
     * @param date
     * @return
     */
    public static String day2Week(Date date) {

        int day = getPositionInWeek(date) ;

        return days[day - 1] ;

    }

    /**
     * 把指定时间转换成"yyyy-MM-dd"形式
     * 
     * 
     * 
     * @param inDate
     * @return
     */

    public static String dateToString(Date inDate, String type) {

        SimpleDateFormat f = new SimpleDateFormat(type) ;

        return f.format(inDate) ;

    }

 

    /**
     * 转换指定的 Year month day 为日期形式
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date switch2Date(int year, int month, int day) {

        GregorianCalendar gc = new GregorianCalendar(year, month - 1, day) ;

        return gc.getTime() ;

    }

    /**
     * 转换指定的 Year month day housr minute 为日期形式
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date switch2Date(int year, int month, int day, int housr, int minute) {

        GregorianCalendar gc = new GregorianCalendar(year, month - 1, day, housr, minute) ;

        return gc.getTime() ;

    }

    /**
     * 获得现在的时间
     * 
     * @return
     */
    public static Date getnowDate() {

        return new Date() ;

    }

    /**
     * 获得指定时间当月的天数
     * 
     * @param date
     * @return
     */

    public static int getNumberOfDaysInMonth(Date date) {
        GregorianCalendar gc = instance(date) ;
        return gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) ;

    }

    /**
     * 获得指定时间的月数
     * 
     * @param date
     * @return
     */
    public static int getNumberOfMonthInYear(Date date) {
        GregorianCalendar gc = instance(date) ;

        return gc.get(GregorianCalendar.MONTH) + 1 ;

    }

    public static int getNumberOfYear(Date date) {
        GregorianCalendar gc = instance(date) ;
        return gc.get(GregorianCalendar.YEAR) ;
    }

    /**
     * 获得指定时间的号数
     * 
     * @param date
     * @return
     */
    public static int getNumberOfDay(Date date) {
        GregorianCalendar gc = instance(date) ;
        return gc.get(GregorianCalendar.DATE) ;
    }

    public static long switchDate2Long(Date date) {
        long day = date.getTime() ;
        return day ;

    }

   

    public static void main(String[] args) {
        //        Date xx = DateToolsUilts.addWeek(DateToolsUilts.getnowDate(), 1) ;
        //        System.out.println("==>  " + DateToolsUilts.dateToSimpleType_yyyy_MM_dd(DateToolsUilts.getnowDate())) ;
        //        System.out.println("==>  " + DateToolsUilts.dateToSimpleType_yyyy_MM_dd(xx)) ;
    }

}
