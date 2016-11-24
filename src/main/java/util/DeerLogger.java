package util;

import java.util.LinkedHashMap;

/**
 * Created by white on 2016-11-06.
 */
public class DeerLogger {
    public final static String WRITE_TO_DB="write object to db: ";
    private static LinkedHashMap<Class,String> errors,warnings,trace,info;
    private static DeerLogger mDeerLogger;

    /**
     * make this class singleton
     */
    private DeerLogger(){
        errors=new LinkedHashMap<Class, String>();
        warnings=new LinkedHashMap<Class, String>();
        trace=new LinkedHashMap<Class, String>();
        info=new LinkedHashMap<Class, String>();
    }

    public static DeerLogger getInstance(){
        if(mDeerLogger==null){
            mDeerLogger=new DeerLogger();
        }
        return mDeerLogger;
    }

    public void onError(Class aClass,String errorText){

    }

    public void onInfo(Class aClass,String text){

    }
}
