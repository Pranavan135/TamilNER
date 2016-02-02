
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

import nlp.tamil.*;

import java.util.ArrayList;
import java.util.List;
import nlp.morph.database.NounListCheck;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * grammar rules and corpus
 */
public class NounDetectionLayer {

    private final String word;
    private boolean isNoun;
    private final List<TamilFontEntity> tamilLetters;

    /**
     * Constructs a noun detection layer for a particular word
     *
     * @param word Word to check whether it is a noun or not
     */
    public NounDetectionLayer(String word) {
        this.word = word;
        tamilLetters = IOLayer.getTamil(word);

    }

    /**
     * Checking the noun for patterns noun + ஆளி noun + இயல் noun + காரன் / காரி
     * noun + தனம் noun + துவம் noun + இயம் noun + ஆளன்
     */
    public void checkPatternNounPlusLatter() {

        if (tamilLetters.size() > 2) {

            ArrayList<TamilFontEntity> fPart = new ArrayList<>(tamilLetters);
            List<TamilFontEntity> firstPart = fPart.subList(0, tamilLetters.size() - 2);

            int x = tamilLetters.get(tamilLetters.size() - 2).getxLocation();

            firstPart.add(TamilLayout.bodies[x]);

            if (tamilLetters.get(tamilLetters.size() - 1).equals(IOLayer.getTamil("ளி").get(0)) && tamilLetters.get(tamilLetters.size() - 2).getyLocation() == 1) {
                if (NounListCheck.isExist(IOLayer.getText(firstPart).toString())) {

                    isNoun = true;
                    return;
                }
            }

        }

        if (tamilLetters.size() >= 3) {
            List<TamilFontEntity> firstPart = tamilLetters.subList(tamilLetters.size() - 3, tamilLetters.size());

            if (firstPart.equals(IOLayer.getTamil("காரன்"))) {
                isNoun = true;
                return;
            }

        }
        if (tamilLetters.size() >= 2) {
            List<TamilFontEntity> firstPart = tamilLetters.subList(tamilLetters.size() - 2, tamilLetters.size());

            if (firstPart.equals(IOLayer.getTamil("காரி"))) {
                isNoun = true;
            }

        }

    }

    /**
     * To check whether the given word in a noun
     *
     * @return true if the word is noun, false if the word is not noun
     */
    public boolean isNoun() {
        return isNoun;
    }

    public static void main(String[] args) {
        TamilLayout.init();
        NounDetectionLayer ndl = new NounDetectionLayer("தொழிலாளி");
        ndl.checkPatternNounPlusLatter();
        System.out.println(ndl.isNoun());
        NounDetectionLayer ndl2 = new NounDetectionLayer("பணக்காரி");
        ndl2.checkPatternNounPlusLatter();
        System.out.println(ndl2.isNoun());
        NounDetectionLayer ndl3 = new NounDetectionLayer("பணக்காரன்");
        ndl3.checkPatternNounPlusLatter();
        System.out.println(ndl3.isNoun());

    }
}
