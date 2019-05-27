package performance;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PerformanceChecker {
    public static Map<Double,String> logKeeper= new HashMap<>();
    public synchronized void timeLogger(Double threadKey) throws IOException {
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("hh:mm:ss.SSS");
        LocalDateTime localDateTime=LocalDateTime.now();
        String currentTime=dtf.format(localDateTime);
        String oldTime=logKeeper.get(threadKey);
        if(oldTime==null){
            logKeeper.put(threadKey,currentTime);
        }else{
            logKeeper.replace(threadKey,oldTime+" - "+currentTime);
        }
        if(logKeeper.size()>0){
            File file=new File("C:\\Users\\Auser\\Desktop\\performance.txt");
            BufferedWriter bf=new BufferedWriter(new FileWriter(file,true));
            for(Map.Entry<Double,String> a: logKeeper.entrySet()){
                bf.append(a.getKey()+" -> "+a.getValue()+"\n");
            }
            logKeeper.clear();
            bf.close();
        }
    }
}
