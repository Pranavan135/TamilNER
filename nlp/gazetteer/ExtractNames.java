
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                             * 
 * Named Entity Recognition system is used to identify the names of person,    *
 * names of location, names of organization, time expressions and numeric      *
 * expression in Tamil text.                                                   *
 *                                                                             *
 *  Copyright (C) 2015-2016  University of Moratuwa                            *
 *                                                                             *
 * This program is free software: you can redistribute it and/or modify        *
 * it under the terms of the GNU General Public License as published by        *
 * the Free Software Foundation, either version 3 of the License, or           *
 * (at your option) any later version.                                         *
 *                                                                             *
 * This program is distributed in the hope that it will be useful,             *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of              *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               *
 * GNU General Public License for more details.                                *
 *                                                                             *
 * You should have received a copy of the GNU General Public License           *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.       *
 *                                                                             * 
 *                                                                             * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package nlp.gazetteer;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class ExtractNames {
    private static final Hashtable<String,String> firstName = new Hashtable<>();
    private static final Hashtable<String,String> lastName = new Hashtable<>();


    /**
     * get Name patterns
     * @throws IOException 
     */
    public static void extract() throws IOException{
        try (Scanner scanner = new Scanner(new File("resources/NameList.txt"))) {
            String line;
            
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                
                if(line != null && !line.trim().isEmpty()){
                    String[] nameParts = line.trim().split("\\s+");
                    
                    firstName.put(nameParts[0],line.trim());
                    
                    if(nameParts.length > 0){
                        lastName.put(nameParts[nameParts.length-1],line.trim());
                    }
                }
            }
        }
    }

    /**
     * check whether name is in first name table or last name table
     * @param name
     * @return 
     */
    public static boolean person(String name){
        return firstName.get(name) != null || lastName.get(name) != null;
    }

}
