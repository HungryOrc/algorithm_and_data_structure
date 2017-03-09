/* Given a log file, such as:

(12/04/2015-04:00:00) - START
(12/04/2015-04:00:11) - CONNECTED
(12/04/2015-04:30:00) - DISCONNECTED
(12/04/2015-04:35:10) - BUG_DETECTED_AS.................
(12/04/2015-04:45:00) - CONNECTED
(12/04/2015-05:00:05) - SHUTDOWN

Return the proportion of connected time compared to the whole time between "start" and "shutdown".
The expected answer for the log above should be: "74%". */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException; 

public class LogParser {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        String filename = "E:\\My_Java\\LogParser\\bin\\input.txt";
        if (args.length > 0) {
        	filename = args[0];
        }

        String answer = parseFile(filename);
        System.out.println(answer);
    }

    static String parseFile(String filename) throws FileNotFoundException, IOException, ParseException {

        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
            allLines.add(line);
        }
        input.close();

		return parseLines(allLines.toArray(new String[allLines.size()]));

    }

    static String parseLines(String[] lines) throws ParseException{
        				
		long startTime = 0;
		long shutdownTime = 0;
		long connectTime = 0;
		long disconnectTime = 0;
		long connectingTime = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("(MM/dd/yyyy-HH:mm:ss)");
		boolean connecting = false;

		try {
			for (int i = 0; i < lines.length; i++) {
				String[] curLine = lines[i].split(" ");
				String date = curLine[0];
				String action = curLine[2];
				
				System.out.println(action);
				
				if (action.equals("START")) {
					startTime = format.parse(date).getTime();
				}
				
				else if (action.equals("CONNECTED")) {
					connectTime = format.parse(date).getTime();
					connecting = true;
				}
				
				else if (action.equals("DISCONNECTED")) {
					disconnectTime = format.parse(date).getTime();
					connectingTime = connectingTime + disconnectTime - connectTime;
					connecting = false;
				}
				
				else if (action.equals("SHUTDOWN")) {
					shutdownTime = format.parse(date).getTime();
					if (connecting == true) {
						connectingTime = connectingTime + shutdownTime - connectTime;
					}
					connecting = false;
				}
			}
		} catch (ParseException e) {
			throw new ParseException("Parse error!", e.getErrorOffset());
		}
		
		long wholeTime = shutdownTime - startTime;
		System.out.println(wholeTime);
		System.out.println(connectingTime);
		
		double ratio = (double)connectingTime / (double)wholeTime * (double)100;
		int ratioInt = (int)ratio;
		String result = ratioInt + "%";
		
        return result;
    }
}
