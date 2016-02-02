
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

package nlp.morph.test;

import nlp.systeminit.InitSystem;
import nlp.tamil.IOLayer;
import nlp.tamil.TamilFontEntity;

import java.util.List;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This is the class to test the cleaning task before doing the morph analysis
 */
public class CleanTaskTest {
    public static void main(String[] args) {
        InitSystem.init();
        String sentence = "நான் கோவிலுக்குப் போனேன். அவர்கள் என்னைப் பின்தொடர்ந்தார்கள். நானும் பயந்து கொண்டு அதே வழியால் பயணித்தேன்.";

        String[] words = sentence.split("\\s+");
        System.out.println(words.length);
        String cleanedWord = "";
        for(int index = 0; index < words.length-1; index++) {
            System.out.println(words[index].length());
            String temp = words[index].replaceAll("[.,]","");
            List<TamilFontEntity> tamilWord = IOLayer.getTamil(temp);
                System.out.println(tamilWord.size());
                System.out.println(tamilWord.get(tamilWord.size() - 1).getyLocation());

            if (!words[index].contains(".")&& tamilWord.get(tamilWord.size() - 1).getyLocation() == -1 && IOLayer.getTamil(words[index+1]).get(0).getxLocation() == tamilWord.get(tamilWord.size() - 1).getxLocation() ) {
                    tamilWord = tamilWord.subList(0, tamilWord.size() - 1);

                }

                cleanedWord += IOLayer.getText(tamilWord).toString() + " ";
        }
        cleanedWord += words[words.length-1];
        System.out.println("Cleaned word : " + cleanedWord);

    }
}
