
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
import nlp.morph.noun.stemExtraction.ExtractNounStemILayer;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * To test the functionality of the class ExtractNounStermILayer
 */
public class ExtractNounStemILayerTest {

    /**
     * Testing the each possibilities with (ஐ)
     * @param args
     */
    public static void main(String[] args){
        InitSystem.init();
        System.out.println(ExtractNounStemILayer.extractNounStemI("விண்ணை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("அவனை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("மரத்தை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("கரங்களை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("புடைவையை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("பலாவை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("நாயை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("நெய்யை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("ஆணை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("குரங்கை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("பாம்பை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("மாட்டை"));
        System.out.println(ExtractNounStemILayer.extractNounStemI("காற்றை"));

    }
}
