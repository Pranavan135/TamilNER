
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
public class ExtractNounStemUdaiyaLayer {

    /**
     * noun + இடம்
     *
     * @param word Word to be checked
     * @return the stem of the noun
     */
    public static List<TamilFontEntity> ExtractNounStemUdaiya(String word) {
        List<TamilFontEntity> tamilLetters = IOLayer.getTamil(word);
        ArrayList<TamilFontEntity> letters;
        letters = new ArrayList<>(tamilLetters);
        List<TamilFontEntity> stem;

        if (tamilLetters.size() > 4) {
            List<TamilFontEntity> lastPart = letters.subList(tamilLetters.size() - 4, tamilLetters.size());

            if (word.substring(tamilLetters.size() - 4).equals("த்துடைய")) {
                stem = tamilLetters.subList(0, tamilLetters.size() - (lastPart.size()));
                stem.add(IOLayer.getTamil("ம்").get(0));
                return stem;
            } else if (tamilLetters.subList(tamilLetters.size() - 4, tamilLetters.size()).equals(IOLayer.getTamil("ற்றுடன்")) || tamilLetters.subList(tamilLetters.size() - 4, tamilLetters.size()).equals(IOLayer.getTamil("ட்டுடன்"))) {
                if (tamilLetters.subList(tamilLetters.size() - 5, tamilLetters.size()).equals(IOLayer.getTamil("காற்றுடன்"))) {
                    stem = tamilLetters.subList(0, tamilLetters.size() - 3);
                    stem.add(TamilLayout.getEntity(tamilLetters.get(tamilLetters.size() - 3).getxLocation(), 4));
                    return stem;
                } else {
                    stem = tamilLetters.subList(0, tamilLetters.size() - 4);
                    stem.add(TamilLayout.getEntity(tamilLetters.get(tamilLetters.size() - 3).getxLocation(), 4));
                    return stem;
                }
            }
        }
        if (tamilLetters.size() > 3) {
            if (letters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("வுடன்"))) {
                stem = letters.subList(0, tamilLetters.size() - 3);
                return stem;
            }
        }
        if (tamilLetters.size() > 2) {
            if (letters.subList(tamilLetters.size() - 2, tamilLetters.size()).equals(IOLayer.getTamil("டன்"))) {
                if (tamilLetters.size() >= 4) {
                    if (tamilLetters.get(tamilLetters.size() - 4).equals(IOLayer.getTamil("ண்").get(0)) || tamilLetters.get(tamilLetters.size() - 4).equals(IOLayer.getTamil("ல்").get(0)) || tamilLetters.get(tamilLetters.size() - 4).equals(IOLayer.getTamil("ன்").get(0)) || tamilLetters.get(tamilLetters.size() - 4).equals(IOLayer.getTamil("ய்").get(0))) {
                        stem = letters.subList(0, tamilLetters.size() - 3);
                        return stem;
                    } else if (tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("குடன்")) || tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("புடன்")) || tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("துடன்")) || tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("சுடன்"))) {
                        stem = letters.subList(0, tamilLetters.size() - 2);
                        return stem;
                    } else if (tamilLetters.get(tamilLetters.size() - 4).getyLocation() == 2) {
                        stem = letters.subList(0, tamilLetters.size() - 3);
                        return stem;
                    } else {
                        int x = tamilLetters.get(tamilLetters.size() - 3).getxLocation();
                        stem = letters.subList(0, tamilLetters.size() - 3);
                        stem.add(TamilLayout.bodies[x]);
                        return stem;
                    }
                }
            }
        }
        return null;

    }

}
