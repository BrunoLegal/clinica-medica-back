package br.edu.imepac.util;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static Date convertStringToSqlDate(String dateStr) throws ParseException {
        java.util.Date utilDate = DATE_FORMAT.parse(dateStr);
        return new Date(utilDate.getTime());
    }

    public static String convertSqlDateToString(Date dateSql){
        return DATE_FORMAT.format(dateSql);
    }
}
