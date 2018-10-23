package zz.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateConverter implements Converter<String, Date> {
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public CustomDateConverter(String pattern) {
        if(df!=null) this.df = new SimpleDateFormat(pattern);
    }
    @Override
    public Date convert(String text) {
        Date value = null;
        if(text!=null && text.trim().length()>0){
            try {
                value = df.parse(text);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
