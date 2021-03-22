package service;


/**
 * An auxiliary class responsible for checking input String for matching the template
 */
public class StringValidator {

    /**
     * Statick method, returns boolean of all symbols in string matches either latin letters, digits, or brackets(
     * number of opening ones must match to the closing ones quantity)
     * @param string String for validation
     * @return boolean of it is valid for unpacking.
     */
    public static boolean validate(String string) {
        char[] undercheck = string.toCharArray();
        int openingBr = 0;
        int closingBr = 0;
        for (char c : undercheck) {
            if (cIsOpening(c) || cIsClosing(c) || (Character.isLetterOrDigit(c))) {
                if (cIsOpening(c)) openingBr++;
                if (cIsClosing(c)) closingBr++;
            } else {
                return false;
            }
        }
        return openingBr == closingBr;
    }


    static boolean cIsOpening(char c) {
        return (c == '[');
    }

    static boolean cIsClosing(char c) {
        return (c == ']');
    }


}
