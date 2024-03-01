package Tools;

import Analyzers.Scanner;
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
        String fileName = "Reports/reporte_tokens_" + name + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reporteHTML);
        }
    }

    public static String TokenReport(String input, Symbol token, String name) throws IOException {
        Scanner scanner = new Scanner(
                new BufferedReader(
                        new StringReader(input)
                )
        );
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
        int i = 1;
        do {
            token = scanner.next_token();
            report += "<tr>";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + i + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + token.value + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + Analyzers.ParserSym.terminalNames[token.sym] + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + token.left + "</td>\n";
            report += "\n\t\t\t\t<td class=\"px-6 py-5\">" + token.right + "</td>\n";
            report += "</tr>\n";
            i++;
            // reporte += token.value + " ".repeat(35 - String.valueOf(token.value).length()) + token.left + " ".repeat(6 - String.valueOf(token.left).length()) + token.right + " ".repeat(8 - String.valueOf(token.right).length()) + Language.TOK.terminalNames[token.sym];
        } while (token.value != null);
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
            report += "<tr>";
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
