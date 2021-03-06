
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

package nlp.morph.noun;

import nlp.tamil.TamilFontEntity;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * identify the noun based on its Ending
 */
public class NounEndingPatternRules {

    // List of noun rules that used to find the nouns

    private static ArrayList<NounEndingRule> nounEndingRuleList;
    private static final Logger log = Logger.getLogger(NounEndingPatternRules.class.getName());

    /**
     * Initialize the static variables
     */
    public static void init() {
        try {
            Scanner fileInput = new Scanner(new File(new File("resources/nounpattern.format").getCanonicalPath()));
            nounEndingRuleList = new ArrayList<>();
            fileInput.nextLine();

            while (fileInput.hasNext()) {
                int minLength = fileInput.nextInt();
                int lCharacters = fileInput.nextInt();
                String pattern = fileInput.next();

                int bCharacters = fileInput.nextInt();
                int x = -1;
                int y = -1;
                if (bCharacters > 0) {
                    x = fileInput.nextInt();
                    y = fileInput.nextInt();
                }
                nounEndingRuleList.add(new NounEndingRule(minLength, pattern, x, y, lCharacters));
            }
        } catch (IOException e) {
            log.log(Level.WARNING, "ERROR : {0}", e.getMessage());
        }
    }

    /**
     * To check whether a given word is noun
     *
     * @param entities List of tamil characters of a word
     * @return true, if the word is a noun. false if a word do not satisfy the
     * rules
     */
    public static boolean isNoun(List<TamilFontEntity> entities) {
        for (NounEndingRule nounEndingRule : nounEndingRuleList) {
            if (satisfies(nounEndingRule, entities)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To check whethera word satisfies
     *
     * @param nounEndingRule Noun rule to be checked
     * @param entities Word as a list of tamil characters
     * @return true if the particular word satisfies particular noun rule
     */
    private static boolean satisfies(NounEndingRule nounEndingRule, List<TamilFontEntity> entities) {
        if (entities.size() >= nounEndingRule.getMinimumLength()) {
            ArrayList<TamilFontEntity> fPart = new ArrayList<>(entities);

            int min = nounEndingRule.getNumberOfLastCharactersToBeChecked();
            List<TamilFontEntity> temp = fPart.subList(entities.size() - min, entities.size());
            return nounEndingRule.checkPattern(temp) && (!nounEndingRule.isbeforePositionToBeChecked() || nounEndingRule.isXCoordinateMatches(fPart.get(fPart.size() - min - 1)) && nounEndingRule.isYCoordinateMatches(fPart.get(fPart.size() - min - 1)));
        } else {
            return false;
        }
    }

}
