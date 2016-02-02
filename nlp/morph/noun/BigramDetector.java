
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

import nlp.tamil.IOLayer;
import nlp.tamil.TamilFontEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * its previous word
 */
public class BigramDetector {

    private static List<String> previousWordList;

    /**
     * Initialization of possible previous words before a noun
     */
    public static void init() {
        previousWordList = new ArrayList<>();
        previousWordList.add("நல்ல");
        previousWordList.add("புதிய");
        previousWordList.add("பழைய");
    }

    /**
     * To check whether the current word is noun based on the previous word
     *
     * @param previousWord Previous word to the current word in the sentecne
     * @return true if the current word is noun based on the previous word,
     * unless false
     */
    public static boolean isNoun(String previousWord) {
        if (previousWordList.contains(previousWord)) {
            return true;
        } else {
            List<TamilFontEntity> tamilWord = IOLayer.getTamil(previousWord);

            if (tamilWord.isEmpty()) {
                return false;
            }
            return tamilWord.get(tamilWord.size() - 1).getxLocation() == 0;
        }

    }
}
