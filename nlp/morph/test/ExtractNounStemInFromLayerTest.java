
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

package nlp.morph.test;

import nlp.systeminit.InitSystem;
import nlp.morph.noun.stemExtraction.ExtractNounStemInFromLayer;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * To test the functionality of the class ExtractNounStemInFromLayer class
 */
public class ExtractNounStemInFromLayerTest {
    public static void main(String[] args) {
        InitSystem.init();
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("மரத்தினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("விண்ணினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("மலர்களினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("பலாவினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("காலியினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("செம்பினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("நாயினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("நெய்யினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("மாட்டினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("காற்றினிலிருந்து"));
        System.out.println(ExtractNounStemInFromLayer.extractStemNounInFrom("குரங்கினிலிருந்து"));

    }
}
