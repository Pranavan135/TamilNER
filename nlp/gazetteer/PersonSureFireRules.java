
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                             * 
 * Named Entity Recognition system is used to identify the names of person,    *
 * names of location, names of organization, names of times and numeric        *
 * expression in Tamil text.                                                   *
 *                                                                             *
 *  Copyright (C) 2015-2016  University of Moratuwa                            *
 *                                                                             *
 * This program is free software: you can redistribute it and/or modify        *
 * it under the terms of the GNU General Public License as published by        *
 * the Free Software Foundation, either version 3 of the License, or           *
 * (at your option) any later version.                                         *
 *                                                                             *
 *                                                                             *
 * This program is free software: you can redistribute it and/or modify        *
 * it under the terms of the GNU General Public License as published by        *
 * the Free Software Foundation, either version 3 of the License, or           *
 * (at your option) any later version.                                         *
 *                                                                             * 
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class PersonSureFireRules {
    private final List<String> personPatterns;
    private static final String file = "resources/PersonSureFireRules";
    private static PersonSureFireRules personSureFireRules;

    /**
     * singleton
     */
    private PersonSureFireRules(){
        personPatterns = new ArrayList<>();
        addPersonPatterns();
    }

    public static PersonSureFireRules getPersonSureFireRules(){
        if(personSureFireRules == null){
            personSureFireRules = new PersonSureFireRules();
            return personSureFireRules;
        } else {
            return personSureFireRules;
        }
    }

    private void addPersonPatterns(){

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file)));

            String word = bufferedReader.readLine().trim();

            while(word != null){
                personPatterns.add(word);
                word = bufferedReader.readLine();
            }


        }catch(IOException ex){
            System.err.println("IO Error" + ex.getMessage());
        }
    }

    public int getMatch(String word){
        for(int i = 0 ; i <= 3 ; i++){
            if (word.equalsIgnoreCase(personPatterns.get(i)))
                return 1;
        }

        return -1;
    }

}
