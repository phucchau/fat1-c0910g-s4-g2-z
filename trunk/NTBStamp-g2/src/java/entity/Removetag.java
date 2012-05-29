/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author DuMaster
 */
public class Removetag {

    public static String StripAllTags(String input) {
        char[] array = new char[input.length()];
        char[] ainput = input.toCharArray();
        int arrayIndex = 0;
        boolean inside = false;

        for (int i = 0; i < input.length(); i++) {
            char let = ainput[i];
            if (let == '<') {
                inside = true;
                continue;
            }

            if (let == '>') {
                inside = false;
                continue;
            }
            if (!inside) {
                array[arrayIndex] = let;
                arrayIndex++;
            }
        }

        return new String(array, 0, arrayIndex);
    }
}
