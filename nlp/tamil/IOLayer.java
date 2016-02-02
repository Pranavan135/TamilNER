
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

package nlp.tamil;


import java.util.ArrayList;
import java.util.List;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class IOLayer {

    /**
     * if we input the tamil text from a file
     *
     * @param text
     * @return list of Tamil fonts in the text
     */
    public static List<TamilFontEntity> getTamil(String text) {
        List<TamilFontEntity> tamilFontEntities = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {

            String s1 = String.valueOf(text.charAt(i));

            if (TamilLayout.startLetters.contains(s1) && i + 1 < text.length()) {
                String s2 = String.valueOf(text.charAt(i + 1));

                if (TamilLayout.endLetters.contains(s2 + " ")) {
                    tamilFontEntities.add(TamilLayout.tamilLetterMap.get(s1 + s2 + " "));
                    i = i + 1;
                } else {
                    tamilFontEntities.add(TamilLayout.tamilLetterMap.get(s1));
                }
            } else {
                tamilFontEntities.add(new TamilFontEntity(s1));
            }
        }
        return tamilFontEntities;
    }

    /**
     * change list of TamilFontEntity to tamil text
     *
     * @param list
     * @return string of tamil text
     */
    public static StringBuilder getText(List<TamilFontEntity> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (TamilFontEntity tamilFontEntity : list) {
            if (tamilFontEntity.isTamilLetter())
                stringBuilder.append(tamilFontEntity.toString().trim());
            else
                stringBuilder.append(tamilFontEntity.toString());
        }

        return stringBuilder;
    }

    public static void main(String[] args) {
        /**
         * sample tests
         */
        TamilLayout.init();
        String text1 = "நான் கடைக்கு போனேன்";
        String text2 = "நான் கடைக்கு நடந்து கடைக்கு போனேன்";

        List<TamilFontEntity> tamilFontEntities1 = getTamil(text1);
        System.out.println(tamilFontEntities1.size());

        List<TamilFontEntity> tamilFontEntities2 = getTamil(text2);
        System.out.println(tamilFontEntities1.get(0).getxLocation() + " " + tamilFontEntities1.get(0).getyLocation());
        System.out.println(tamilFontEntities2.size());

        System.out.println(TamilLayout.startLetters);
        System.out.println(TamilLayout.endLetters);

        System.out.println(getText(tamilFontEntities1).toString());
        System.out.println(getText(tamilFontEntities2).toString());

    }
}
