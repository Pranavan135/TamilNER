

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
package nlp.evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class PrecisionRecallCalc {

    private static final String[] NE = {"INDIVIDUAL", "ORGANIZATION", "COUNT", "PERIOD", "PLACE", "TIME", "QUANTITY", "MONEY"};

    /**
     * find namedEntity wise Precision and recall
     * @param l1 - list of words 
     * @param l2
     * @param namedEntity 
     */
    public static void calc(List<String> l1, List<String> l2, String namedEntity) {
        double precision = 0.0;
        double recall = 0.0;

        double truePositives = 0;
        double falsePositives = 0;
        double falseNegatives = 0;

        for (int i = 0; i < l2.size(); i++) {
            if (l1.get(i).contains(namedEntity) && !l2.get(i).contains(namedEntity)) {
                falseNegatives = falseNegatives + 1.0;
            } else if (!l1.get(i).contains(namedEntity) && l2.get(i).contains(namedEntity)) {
                falsePositives = falsePositives + 1.0;
            } else if (l1.get(i).contains(namedEntity) && l2.get(i).contains(namedEntity)) {
                truePositives = truePositives + 1.0;
            }
        }

        if ((truePositives == 0 && falsePositives == 0) || (truePositives == 0 && falseNegatives == 0)) {
            return;
        }
        
        /**
         * calculate precision
         */
        precision = truePositives / (truePositives + falsePositives);

        /**
         * calculate recall
         */
        recall = truePositives / (truePositives + falseNegatives);

        System.out.println("Named Entity " + namedEntity);
        System.out.println("Precision : " + precision);
        System.out.println("Recall : " + recall);
    }


    /**
     * calculate precision and recall NE wise
     * @param l1
     * @param l2 
     */
    public static void compute(List<String> l1, List<String> l2) {
        for (int i = 0; i < NE.length; i++) {
            calc(l1, l2, NE[i]);
        }
    }
}
