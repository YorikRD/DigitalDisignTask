import service.StringUnpacker;


/**
 * The main class only used for invoking methods
 */
public class TaskMain {
    public static void main(String[] args) {
        String string = "2[3[xyz]4[abc]]";
        String str=StringUnpacker.unpack(string);
        System.out.println(str);


    }


}
