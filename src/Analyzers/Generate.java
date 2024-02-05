package Analyzers;

public class Generate {

    public Generate() {
        try {
            String ruta = "src/Analyzers/";
            //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "Scanner.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            //String opcCUP[] = {"-destdir", ruta, "-parser", "parser", ruta + "Parser.cup"};
            //java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
