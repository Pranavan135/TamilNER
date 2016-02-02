
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

package nlp.morph.noun;

import nlp.tamil.IOLayer;
import nlp.tamil.TamilFontEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * To detect the numbers
 */
public class NumberDetectorLayer {
    private static List<NounEndingRule> numberRules;
    private static final Logger log = Logger.getLogger(NumberDetectorLayer.class.getName());

    public static void init() {
        try {
            numberRules = new ArrayList<>();

            Scanner inFile = new Scanner(new File(new File("resources/num.txt").getCanonicalPath()));
            inFile.nextLine();

            while(inFile.hasNextInt()){

                int y = inFile.nextInt();
                String pattern = inFile.next();

                int patternSize = IOLayer.getTamil(pattern).size();
                numberRules.add(new NounEndingRule(patternSize+1,pattern,-1,y,patternSize));

            }
        }
        catch(IOException e){
            log.log(Level.WARNING, "ERROR : {0}", e.getMessage());
        }


    }

    /**
     * To check whether a given word is number
     * @param entities List of tamil characters of a word
     * @return true, if the word is a noun. false if a word do not satisfy the rules
     */
    public static boolean isNumber(List<TamilFontEntity> entities){
        return numberRules.stream().anyMatch((numberRule) -> (satisfies(numberRule,entities)));
    }

    /**
     * To check whether a word satisfies a number rule
     * @param nounEndingRule Number rule to be checked
     * @param entities Word as a list of tamil characters
     * @return true if the particular word satisfies particular number rule
     */
    private static boolean satisfies(NounEndingRule nounEndingRule, List<TamilFontEntity> entities) {
        if(entities.size() >= nounEndingRule.getMinimumLength()) {

            ArrayList<TamilFontEntity> fPart = new ArrayList<>(entities);

            int min = nounEndingRule.getNumberOfLastCharactersToBeChecked();
            List<TamilFontEntity> temp = fPart.subList(entities.size() - min, entities.size());
            return nounEndingRule.checkPattern(temp) && (!nounEndingRule.isbeforePositionToBeChecked() || nounEndingRule.isXCoordinateMatches(fPart.get(fPart.size() - min - 1)) && nounEndingRule.isYCoordinateMatches(fPart.get(fPart.size() - min - 1)));
        }
        else
            return false;
    }


}
