
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

package nlp.morph.noun.stemExtraction;

import nlp.tamil.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class ExtractNounStemMLayer {
    private static List<TamilFontEntity> strenthLetters;
    private static List<String> joiningWords;

    public static void init() {
        strenthLetters = new ArrayList<>();

        strenthLetters.add(IOLayer.getTamil("கு").get(0));
        strenthLetters.add(IOLayer.getTamil("சு").get(0));
        strenthLetters.add(IOLayer.getTamil("டு").get(0));
        strenthLetters.add(IOLayer.getTamil("து").get(0));
        strenthLetters.add(IOLayer.getTamil("பு").get(0));
        strenthLetters.add(IOLayer.getTamil("று").get(0));

        joiningWords = new ArrayList<>();

        joiningWords.add("மற்றும்");
        joiningWords.add("எப்போதும்");
    }

    /**
     * Extract the noun from the 4 th proposition
     * *noun + ம்
     * @param word Word to check
     * @return stem of the noun as a list
     */
    public static List<TamilFontEntity> extractStemNounM(String word) {
        List<TamilFontEntity> tamilLetters = IOLayer.getTamil(word);
        ArrayList<TamilFontEntity> letters;
        letters = new ArrayList<>(tamilLetters);
        List<TamilFontEntity> stem = new ArrayList<>();


        if(joiningWords.contains(word)){
            return IOLayer.getTamil(word);
        }
        if(tamilLetters.subList(tamilLetters.size()-1,tamilLetters.size()).get(0) == IOLayer.getTamil("ம்").get(0)) {

            if (tamilLetters.size() > 2) {
                int y = tamilLetters.get(tamilLetters.size() - 3).getyLocation();

                if (strenthLetters.contains(tamilLetters.subList(tamilLetters.size() - 2, tamilLetters.size()).get(0))) {
                    stem = tamilLetters.subList(0, tamilLetters.size() - 1);

                } else if (tamilLetters.get(tamilLetters.size() - 3).equals(IOLayer.getTamil("ண்").get(0)) || tamilLetters.get(tamilLetters.size() - 3).equals(IOLayer.getTamil("ல்").get(0)) || tamilLetters.get(tamilLetters.size() - 3).equals(IOLayer.getTamil("ய்").get(0))) {
                    stem = letters.subList(0, tamilLetters.size() - 2);

                } else if (tamilLetters.subList(tamilLetters.size() - 2, tamilLetters.size() - 1).get(0).equals(IOLayer.getTamil("வு").get(0))) {
                    if (y == 1 || y == 10 || y == 0) {
                    stem = letters.subList(0, tamilLetters.size() - 2);
                } else {
                    stem = letters.subList(0, tamilLetters.size() - 1);
                }
                } else if (tamilLetters.get(tamilLetters.size() - 2).equals(IOLayer.getTamil("யு").get(0))) {
                    if (y == 1) {
                    int x = tamilLetters.get(tamilLetters.size() - 3).getxLocation();
                    stem = letters.subList(0, tamilLetters.size() - 2);
                    stem.add(TamilLayout.bodies[x]);
                } else {
                    stem = letters.subList(0, tamilLetters.size() - 2);
                }

                } else if (tamilLetters.get(tamilLetters.size() - 2).getyLocation() == 4) {
                    int x = tamilLetters.get(tamilLetters.size() - 2).getxLocation();
                    stem = letters.subList(0, tamilLetters.size() - 2);
                    stem.add(TamilLayout.bodies[x]);
                }
                else {
                    stem = null;
                }

            }

        }
        else return null;
        return stem;
    }


}
