package com.gsm.platfra.system.security.util.uid;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 현재 second 기준으로 UID를 생성하는 Utility.
 * <pre>
 * </pre>
 * @author 권익찬 
 * @since 1.0
 */
public class TimeBaseUID implements UID {
	
	protected Logger logger = LoggerFactory.getLogger(TimeBaseUID.class);
	
	private static Map<String,SimpleDateFormat> map = new HashMap<String,SimpleDateFormat>();
	
	
	private String timeStr = "0";
	private long meanTime;
	private AtomicLong count = new AtomicLong();
	private String prefix;
	private Object lock = new Object();
	
	public TimeBaseUID(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

	public synchronized String getUID() {
		long times = (System.currentTimeMillis() / 1000) * 1000;
		
		String countTemp;
		
		synchronized(lock) {
			if(meanTime != times){
				meanTime = times;
				count.set(0);
				timeStr = getYyyyMMddHHmmss(meanTime);
			}
			countTemp = timeStr + this.leftPadding(Long.toString(count.incrementAndGet()),4,'0');
		} 

//		logger.info("{}{}", prefix, countTemp);
		
		return prefix + countTemp;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() +"[" + prefix + "]";
	}
	
	
	/**
	 * 주어진 문자열의 길이(getBytes() 결과 길이)가 주어진 크기보다 작으면 padding char 를 부족한 길이만큼 왼쪽에
	 * 추가합니다.<BR>
	 * 단, 주어진 문자열의 길이가 주어진 크기보다 클 경우, 문자열의 맨 앞 부분부터 주어진 길이만큼만 잘려진 문자열이 리턴됩니다.<BR>
	 * <BR>
	 * 
	 * 사용예) leftPadding("A", 3, '0')<BR>
	 * 결 과 ) "00A"<BR>
	 * <BR>
	 * 사용예) leftPadding("ABCD", 3, '0')<BR>
	 * 결 과 ) "ABC"<BR>
	 * <BR>
	 * 
	 * @param src
	 *            padding 하기 위한 문자열
	 * @param size
	 *            padding 하기 위한 길이
	 * @param paddingChar
	 *            padding 할 문자
	 * @return padding 이 완료된 문자열
	 */
	public static String leftPadding(String src, int size, char paddingChar) {
        // 성능 개선을 위하여, 아래와 같은 공통 호출과 관련된 부분을,
        // 동일하게 복사하였음.

        int srcLength = 0;
        byte [] srcBytes;
        if (src == null) {
    		StringBuffer result = new StringBuffer();
            for (int i = 0; i < size; i++) {
                result.append(paddingChar);
            }//end for
            return result.toString();
        } else {
            srcBytes = src.getBytes();
            srcLength = srcBytes.length;
        }//end if else
        if (size == srcLength) {
            return src;
        } else if (size < srcLength) {
            return new String(srcBytes, 0, size);
        }//end if else
        int paddingCount = size - srcLength;
		StringBuffer result = new StringBuffer();
        for (int i = 0; i < paddingCount; i++) {
            result.append(paddingChar);
        }//end for
        result.append(src);
        return result.toString();
	}
	
	public static String getYyyyMMddHHmmss(long time){
		return getDateString(time, "yyyyMMddHHmmss");
	}
	
	private static SimpleDateFormat getFormat(String format){
		SimpleDateFormat dateFormat;
		synchronized (map) {
			dateFormat = map.get(format);
			if (dateFormat == null) {
				dateFormat = new SimpleDateFormat(format);
				map.put(format, dateFormat);
			}
		}
		return dateFormat;
	}
	
	
	public static String getDateString(long date, String format){
		SimpleDateFormat dateFormat = getFormat(format);
		return dateFormat.format(date);
	}
	
}