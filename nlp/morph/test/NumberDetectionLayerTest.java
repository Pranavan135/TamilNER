
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
import nlp.morph.noun.NumberDetectorLayer;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This class is used to check the numbers in the text
 */
public class NumberDetectionLayerTest {

    public static void main(String[] args) {
        InitSystem.init();

        System.out.println(NumberDetectorLayer.isNumber(IOLayer.getTamil("ஒன்று")));
        System.out.println(NumberDetectorLayer.isNumber(IOLayer.getTamil("நூற்றுப்பத்து")));
        System.out.println(NumberDetectorLayer.isNumber(IOLayer.getTamil("இருபத்துநான்கு")));
        System.out.println(NumberDetectorLayer.isNumber(IOLayer.getTamil("நாண்கு")));
        System.out.println(NumberDetectorLayer.isNumber(IOLayer.getTamil("சத்து")));
        System.out.println(NumberDetectorLayer.isNumber(IOLayer.getTamil("ஐம்பத்திரண்டு")));
        System.out.println(NumberDetectorLayer.isNumber(IOLayer.getTamil("மூன்று")));

    }
}
