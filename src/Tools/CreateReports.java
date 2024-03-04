package Tools;

import Analyzers.Scanner;
import Analyzers.token;
import Errores.Errores;
import TableSymb.Information;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java_cup.runtime.Symbol;

public class CreateReports {

    // Reporte de Tokens
    public static void saveReportTok(String reporteHTML, String name) throws IOException {
        String fileName = "Reports/reporte_" + name + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reporteHTML);
        }
    }

    public static String TokenReport(ArrayList<token> lexemas) throws IOException {

        String report = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <script src=\"https://cdn.tailwindcss.com\"></script>\n"
                + "    <title>Document</title>\n"
                + "    <link href=\"./output.css\" rel=\"stylesheet\">\n"
                + "    <style>\n"
                + "         body{"
                + "            background: #151515;"
                + "            }"
                + "        .table {\n"
                + "            border-spacing: 0 15px;\n"
                + "        }\n"
                + "\n"
                + "        .table tr {\n"
                + "            border-radius: 20px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <div class=\"flex w-full justify-center h-screen bg-[#151515] py-6\">\n"
                + "        <div class=\"flex flex-col w-5/6 h-fit gap-y-6\">\n"
                + "            <h1 class=\"text-3xl font-bold  text-white\">\n"
                + "                Reporte de Tokens\n"
                + "            </h1>\n"
                + "            <div class=\"w-full bg-[#1f1f24] py-2 px-4 rounded-lg\">\n"
                + "                <table class=\"table w-full text-white border-spacing-4 text-lg border-neutral-200\">\n"
                + "                    <thead class=\"\">\n"
                + "                        <tr class=\"text-left px-6 py-4 border-b border-b-slate-500\">\n"
                + "                            <th class=\"px-6 py-4\">No </th>\n"
                + "                            <th class=\"px-6 py-4\">Lexema</th>\n"
                + "                            <th class=\"px-6 py-4\">Tipo</th>\n"
                + "                            <th class=\"px-6 py-4\">Línea</th>\n"
                + "                            <th class=\"px-6 py-4\">Columna</th>\n"
                + "                        </tr>\n"
                + "                    </thead>";

        for (int i = 0; i < lexemas.size(); i++) {
            int no = i + 1;
            report += "<tr class=\"border-b last:border-b-0 border-b-[#434343] text-[#9ca3af]\">";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + no + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + lexemas.get(i).getLexema() + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + lexemas.get(i).gettokenType() + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + lexemas.get(i).getLine() + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + lexemas.get(i).getColumn() + "</td>\n";
            report += "</tr>\n";
        }
        report += "</table>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        return report;
    }

    public static String ErrorsReport(ArrayList<Errores> eLexicos, ArrayList<Errores> eSintacticos) throws IOException {

        String report = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <script src=\"https://cdn.tailwindcss.com\"></script>\n"
                + "    <title>Document</title>\n"
                + "    <link href=\"./output.css\" rel=\"stylesheet\">\n"
                + "    <style>\n"
                + "         body{"
                + "            background: #151515;"
                + "            }"
                + "        .table {\n"
                + "            border-spacing: 0 15px;\n"
                + "        }\n"
                + "\n"
                + "        .table tr {\n"
                + "            border-radius: 20px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <div class=\"flex w-full justify-center h-screen bg-[#151515] py-6\">\n"
                + "        <div class=\"flex flex-col w-5/6 h-fit gap-y-6\">\n"
                + "            <h1 class=\"text-3xl font-bold  text-white\">\n"
                + "                Reporte de Errores\n"
                + "            </h1>\n"
                + " <div class=\"w-full bg-[#1f1f24] py-2 px-4 rounded-lg\">\n"
                + "                <h2 class=\"text-2xl font-bold text-[#ff9393] w-full px-2 py-2 mt-3 \">\n"
                + "                    <i class=\"icon-[material-symbols--error]\"></i>\n"
                + "                    Léxicos\n"
                + "                </h2>\n"
                + "                <table class=\"table w-full text-white border-spacing-4 text-lg border-neutral-200\">\n"
                + "                    <thead class=\"\">\n"
                + "                        <tr class=\"text-left px-6 py-4 border-b border-b-[#434343]\">\n"
                + "                            <th class=\"px-6 py-4\">No </th>\n"
                + "                            <th class=\"px-6 py-4\">Descripción</th>\n"
                + "                            <th class=\"px-6 py-4\">Línea</th>\n"
                + "                            <th class=\"px-6 py-4\">Columna</th>\n"
                + "                        </tr>\n"
                + "                    </thead>";

        for (int i = 0; i < eLexicos.size(); i++) {
            int no = i + 1;
            report += "<tr class=\"border-b last:border-b-0 border-b-[#434343] text-[#9ca3af]\">";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + no + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + eLexicos.get(i).getDescripcion() + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + eLexicos.get(i).getLinea() + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + eLexicos.get(i).getColumna() + "</td>\n";
            report += "</tr>\n";
        }

        report += "</table>\n"
                + "            </div>\n"
                + "<div class=\"w-full bg-[#1f1f24] py-2 px-4 rounded-lg\">\n"
                + "                <h2 class=\"text-2xl font-bold text-[#ff9393] w-full px-2 py-2 mt-3 \">\n"
                + "                    <i class=\"icon-[material-symbols--error]\"></i>\n"
                + "                    Sintácticos\n"
                + "                </h2>"
                + " <table class=\"table w-full text-white border-spacing-4 text-lg border-neutral-200\">\n"
                + "                    <thead class=\"\">\n"
                + "                        <tr class=\"text-left px-6 py-4 border-b border-b-[#434343]\">\n"
                + "                            <th class=\"px-6 py-4\">No </th>\n"
                + "                            <th class=\"px-6 py-4\">Descripción</th>\n"
                + "                            <th class=\"px-6 py-4\">Línea</th>\n"
                + "                            <th class=\"px-6 py-4\">Columna</th>\n"
                + "                        </tr>\n"
                + "                    </thead>";

        for (int i = 0; i < eSintacticos.size(); i++) {
            int no = i + 1;
            report += "<tr>";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + no + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + eSintacticos.get(i).getDescripcion() + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + eSintacticos.get(i).getLinea() + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + eSintacticos.get(i).getColumna() + "</td>\n";
            report += "</tr>\n";
        }
        report += "</table>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        return report;
    }

    public static String symbolReport(TableSymb.TableSymb table) throws IOException {
        String report = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <script src=\"https://cdn.tailwindcss.com\"></script>\n"
                + "    <title>Document</title>\n"
                + "    <link href=\"./output.css\" rel=\"stylesheet\">\n"
                + "    <style>\n"
                + "         body{"
                + "            background: #151515;"
                + "            }"
                + "        .table {\n"
                + "            border-spacing: 0 15px;\n"
                + "        }\n"
                + "\n"
                + "        .table tr {\n"
                + "            border-radius: 20px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <div class=\"flex w-full justify-center h-screen bg-[#151515] py-6\">\n"
                + "        <div class=\"flex flex-col w-5/6 h-fit gap-y-6\">\n"
                + "            <h1 class=\"text-3xl font-bold  text-white\">\n"
                + "                Tabla de Simbolos\n"
                + "            </h1>\n"
                + " <div class=\"w-full bg-[#1f1f24] py-2 px-4 rounded-lg\">\n"
                + "                <table class=\"table w-full text-white border-spacing-4 text-lg border-neutral-200\">\n"
                + "                    <thead class=\"\">\n"
                + "                        <tr class=\"text-left px-6 py-4 border-b border-b-[#434343]\">\n"
                + "                            <th class=\"px-6 py-4\">No </th>\n"
                + "                            <th class=\"px-6 py-4\">Descripción</th>\n"
                + "                            <th class=\"px-6 py-4\">Línea</th>\n"
                + "                            <th class=\"px-6 py-4\">Columna</th>\n"
                + "                        </tr>\n"
                + "                    </thead>";
        int i = 0;
        report += table.addRows();
        report += "</table>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "\n"
                + "</html>";

        return report;
    }

}
