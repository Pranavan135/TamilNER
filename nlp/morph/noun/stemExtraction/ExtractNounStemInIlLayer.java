
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
 *
 * This class is used to analysis the nouns end with இன் , இல்
 */

public class ExtractNounStemInIlLayer {

    /**
     * Extract the stem noun from the word which affix with 5 th proposition இன் , இல்
     * noun + இன் , noun + இல்
     * @param word Word to be checked
     * @return stem of the noun as a TamilFontEntity list
     */

    public static List<TamilFontEntity> extractNounStemInIl(String word) {
        List<TamilFontEntity> tamilLetters = IOLayer.getTamil(word);
        ArrayList<TamilFontEntity> letters;
        letters = new ArrayList<>(tamilLetters);
        List<TamilFontEntity> stem;

        if (tamilLetters.size() > 3) {
            List<TamilFontEntity> lastPart = letters.subList(tamilLetters.size() - 3, tamilLetters.size());
            if (lastPart.equals(IOLayer.getTamil("த்தில்")) ||lastPart.equals(IOLayer.getTamil("த்தின்")) ) {
                stem = tamilLetters.subList(0, tamilLetters.size() - (lastPart.size()));
                stem.add(IOLayer.getTamil("ம்").get(0));
                return stem;
            }
            else if (tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("ற்றின்")) || tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("ட்டின்"))||tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("ற்றில்")) || tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size()).equals(IOLayer.getTamil("ட்டில்")) ) {
                if (tamilLetters.subList(tamilLetters.size() - 4, tamilLetters.size()).equals(IOLayer.getTamil("காற்றில்"))||tamilLetters.subList(tamilLetters.size() - 4, tamilLetters.size()).equals(IOLayer.getTamil("காற்றின்"))) {
                    stem = tamilLetters.subList(0, tamilLetters.size() - 2);
                    stem.add(TamilLayout.getEntity(tamilLetters.get(tamilLetters.size() - 2).getxLocation(), 4));
                    return stem;
                } else {
                    stem = tamilLetters.subList(0, tamilLetters.size() - 3);
                    stem.add(TamilLayout.getEntity(tamilLetters.get(tamilLetters.size() - 2).getxLocation(), 4));
                    return stem;
                }
            }
            if(tamilLetters.get(tamilLetters.size() - 3).getyLocation() == 8)
            {
                stem = letters.subList(0, tamilLetters.size() - 2);
                return stem;
            }

        }
        if(tamilLetters.size()>2) {
            if (letters.subList(tamilLetters.size() - 2, tamilLetters.size()).equals(IOLayer.getTamil("வின்")) || letters.subList(tamilLetters.size() - 2, tamilLetters.size()).equals(IOLayer.getTamil("வில்"))) {
                stem = letters.subList(0, tamilLetters.size() - 2);
                return stem;
            }

            if (letters.subList(tamilLetters.size() - 2, tamilLetters.size()).equals(IOLayer.getTamil("கின்")) || letters.subList(tamilLetters.size() - 2, tamilLetters.size()).equals(IOLayer.getTamil("கில்")) || letters.subList(tamilLetters.size() - 2, tamilLetters.size()).equals(IOLayer.getTamil("சின்")) || letters.subList(tamilLetters.size() - 2, tamilLetters.size()).equals(IOLayer.getTamil("சில்")) ||tamilLetters.subList(tamilLetters.size() - 2,tamilLetters.size()).equals(IOLayer.getTamil("பில்")) ||tamilLetters.subList(tamilLetters.size() - 2,tamilLetters.size()).equals(IOLayer.getTamil("பின்")) ||tamilLetters.subList(tamilLetters.size() - 2,tamilLetters.size()).equals(IOLayer.getTamil("தில்")) ||tamilLetters.subList(tamilLetters.size() - 2,tamilLetters.size()).equals(IOLayer.getTamil("தின்"))  ) {
                {
                    stem = letters.subList(0, tamilLetters.size() - 2);
                    stem.add(TamilLayout.getEntity(tamilLetters.get(tamilLetters.size() - 2).getxLocation(), 4));
                    return stem;
                }

            }

            int y = tamilLetters.get(tamilLetters.size() - 2).getyLocation();
            if (y == 2 && (letters.subList(tamilLetters.size() - 1, tamilLetters.size()).equals(IOLayer.getTamil("ன்")) || letters.subList(tamilLetters.size() - 1, tamilLetters.size()).equals(IOLayer.getTamil("ல்")))) {
                if (tamilLetters.get(tamilLetters.size() - 3).equals(IOLayer.getTamil("ண்").get(0)) || tamilLetters.get(tamilLetters.size() - 3).equals(IOLayer.getTamil("ல்").get(0)) || tamilLetters.get(tamilLetters.size() - 3).equals(IOLayer.getTamil("ன்").get(0)) || tamilLetters.get(tamilLetters.size() - 3).equals(IOLayer.getTamil("ய்").get(0))) {
                    stem = letters.subList(0, tamilLetters.size() - 2);
                    return stem;
                } else if(tamilLetters.get(tamilLetters.size() - 3).getyLocation() == 2)
                {
                    stem = letters.subList(0, tamilLetters.size() - 2);
                    return stem;
                }
                else
                {
                    int x = tamilLetters.get(tamilLetters.size() - 2).getxLocation();
                    stem = letters.subList(0, tamilLetters.size() - 2);
                    stem.add(TamilLayout.bodies[x]);
                    return stem;
                }
            }
        }
        return null;

    }


}
