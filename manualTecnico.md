<h1 align="center">Proyecto 1</h1>
<p align="center">
    <a href="#"><img src="img/main.png"></a>
</p>
<p align="center"></p>

<div align="center">
🙍‍♂️ Joab Israel Ajsivinac Ajsivinac 🆔 202200135
</div>
<div align="center">
📕 Organización de Lenguajes y Compiladores 1
</div>
<div align="center"> 🏛 Universidad San Carlos de Guatemala</div>
<div align="center"> 📆 Primer Semestre 2024</div>

<br/> 

<h1 align="center">📍 Manual Técnico</h1>

<br/> 

## ⚙ Tecnologías Utilizadas

<div align="center" style="display:flex;justify-content:center;gap:20px">
 <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,vscode,git,html,tailwind" />
  </a>
</div>
<ul>
  <li>Java</li>
  <li>Librerias de Java</li>
   <ul>
        <li>FlatLaf 3.0</li>
        <li>flatlaf-intellij-themes-3.0</li>
        <li>jflex-full 1.7.0</li>
        <li>java-cup 11b</li>
        <li>java-cup 11b-runtime</li>
    </ul>
  <li>Visual Studio Code</li>
  <li>Git</li>
  <li>Html</li>
  <li>NetBeans</li>
  <li>tailwindcss</li>
  </ul>
</ul>

## 🧮 Como funciona

La apliacación consta de una ventana principal y una secundaria donde se visualizan las gráficas

<h3>Principal</h3>

La ventana principal tiene la tarea de manejar las pestañas, la apertura de archivos, el guardado de archivos, la creación de reportes, y la tarea más importante la de analizar el código.

**Manejo de pestañas**
Para esto se hace uso de un arraylist, y un JTabbedPane, el primero es para guardar las rutas absolutas de los archivos, y la segunda es para crear el elemento visual de las pestañas, esto se hace mediante el siguiente codigo

```java
tabbedPane.setTabComponentAt(index, tabComponent);

if (index >= rutes.size()) {
    for (int i = rutes.size(); i < index; i++) {
        rutes.add(null);
    }
    rutes.add(index, selectedFile.getAbsolutePath());
} else {
    rutes.add(index, selectedFile.getAbsolutePath());
}
```

**Guardaado de archivos**
El programa puede guardar archivos con extenión .df, esto lo hace realizando primero una validación de que exista una pesataña seleccionada, para luego crear un archivo en la ruta indicada, finalmente agregando la nueva ruta al arraylist de rutas.

**Creación de reportes**
Para la creación primero se crea una instancia de la ventana Charts.
 ```java
 Charts c=new Charts();
 ```

 luego cuando en el listado de instrucciones encuentra una declaración de una gráfica, se procede a tomar que tipo de gráfica es necesitada.

 ```java
if (graphType == TypeVariableG.BARRAS) {
    createBarChart(temp);
} else if (graphType == TypeVariableG.PIE) {
    createPieChart(temp);
} else if (graphType == TypeVariableG.LINEA) {
    createLineChart(temp);
} else if (graphType == TypeVariableG.HISTOGRAMA) {
    createHistogram(temp);
}
 ```

una vez seleccionado el tipo de gráfica que se va a mostrar, se procede a llamar a los métodos respectivos de la ventana Charts.

* addPieChart
    ```java
    c.addPieChart(val, labels, titulo.replaceAll("\"", ""));
    ```
* addBarChart
    ```java
    c.addBarChart(titulo.replaceAll("\"", ""), tituloy.replaceAll("\"", ""), titulox.replaceAll("\"", ""), ejey, ejex);
    ```
* addLineChart
    ```java
    c.addLineChart(titulo.replaceAll("\"", ""), tituloy.replaceAll("\"", ""), titulox.replaceAll("\"", ""), ejey, ejex);

En el caso del histograma se usa `addBarChart`, ya que tienen el mismo comportamiento, pero sin algunas etiquetas

### Analisis
Para analizar el código se hace uso de `jflex` y `cup`, el primero se encarga de analizar lexicamente el codigo y el segundo se encarga de analizar sintacticamente los tokens devueltos por jflex.

**Análisis Léxico**

Dentro del analisis léxico se definieron los siguientes tokens

| Descripción                 | Patrón                                                                                | Expresión regular           | Ejemplo       | Nombre del Token |
| :-------------------------- | :------------------------------------------------------------------------------------ | :-------------------------- | :------------ | :--------------- |
| Reservada program           | Palabra program                                                                       | Program                     | Program       | TK_program       |
| Reservada end               | Palabra end                                                                           | End                         | End           | TK_end           |
| Reservada double            | Palabra double                                                                        | Double                      | Double        | TK_double        |
| Reservada char              | Palabra char                                                                          | Char                        | Char          | TK_char          |
| Reservada var               | Palabra var                                                                           | Var                         | Var           | TK_var           |
| Reservada arr               | Palabra arr                                                                           | Arr                         | Arr           | TK_arr           |
| Reservada sum               | Palabra sum                                                                           | Sum                         | Sum           | TK_sum           |
| Reservada res               | Palabra res                                                                           | Res                         | Res           | TK_res           |
| Reservada mul               | Palabra mul                                                                           | Mul                         | Mul           | TK_mul           |
| Reservada div               | Palabra div                                                                           | Div                         | Div           | TK_div           |
| Reservada mod               | Palabra mod                                                                           | Mod                         | Mod           | TK_mod           |
| Reservada media             | Palabra media                                                                         | Media                       | Media         | TK_media         |
| Reservada mediana           | Palabra mediana                                                                       | Mediana                     | Mediana       | TK_mediana       |
| Reservada moda              | Palabra moda                                                                          | Moda                        | Moda          | TK_moda          |
| Reservada varianza          | Palabra varianza                                                                      | Varianza                    | Varianza      | TK_varianza      |
| Reservada max               | Palabra max                                                                           | Max                         | Max           | TK_max           |
| Reservada min               | Palabra min                                                                           | Min                         | Min           | TK_min           |
| Reservada print             | Palabra print                                                                         | Print                       | Print         | TK_print         |
| Reservada column            | Palabra column                                                                        | Columna                     | Columna       | TK_column        |
| Reservada console           | Palabra console                                                                       | Console                     | Console       | TK_console       |
| Reservada graphbar          | Palabra graphbar                                                                      | Graphbar                    | Graphbar      | TK_graphbar      |
| Reservada ejex              | Palabra ejex                                                                          | Ejex                        | Ejex          | TK_ejex          |
| Reservada ejey              | Palabra ejey                                                                          | Ejey                        | Ejey          | TK_ejey          |
| Reservada titulox           | Palabra titulox                                                                       | Titulox                     | Titulox       | TK_titulox       |
| Reservada tituloy           | Palabra tituloy                                                                       | Tituloy                     | Tituloy       | TK_tituloy       |
| Reservada titulo            | Palabra titulo                                                                        | Titulo                      | Titulo        | TK_titulo        |
| Reservada exec              | Palabra exec                                                                          | Exec                        | Exec          | TK_exec          |
| Reservada graphline         | Palabra graphline                                                                     | graphline                   | graphline     | TK_graphline     |
| Reservada graphpie          | Palabra graphpie                                                                      | Graphpie                    | Graphpie      | TK_graphpie      |
| Reservada label             | Palabra label                                                                         | Label                       | Label         | TK_label         |
| Reservada values            | Palabra values                                                                        | Values                      | Values        | TK_values        |
| Reservada histogram         | Palabra histogram                                                                     | Histogram                   | Histogram     | TK_histogram     |
| Dos puntos                  | Palabra program                                                                       | :                           | :             | TK_colon         |
| Corchete de apertura        | Palabra program                                                                       | [                           | [             | TK_lbracket      |
| Corchete de cerradura       | Palabra program                                                                       | ]                           | ]             | TK_rbracket      |
| Igual                       | carácter =                                                                            | =                           | =             | TK_equal         |
| Guión                       | carácter -                                                                            | -                           | -             | TK_minus         |
| Menor que                   | carácter <                                                                            | <                           | <             | TK_lt            |
| Mayor que                   | carácter >                                                                            | >                           | >             | TK_gt            |
| Punto y coma                | carácter ;                                                                            | ;                           | ;             | TK_semicolon     |
| Coma                        | carácter ,                                                                            | ,                           | ,             | TK_comma         |
| Paréntesis de apertura      | carácter (                                                                            | (                           | (             | TK_lparen        |
| Paréntesis de cerradura     | carácter )                                                                            | )                           | )             | TK_rparen        |
| Identificadores             | Secuencia que inicia con un carácter alfanumérico o guion seguido de numeros o letras | (\_)*[a-zA-Z][a-zA-Z0-9\_]* | variable      | TK_id            |
| Cadenas de texto            | Secuencia de caracteres encerrados entre comillas                                     | [\"][^\"\n]+[\"]            | “cadena”      | TK_string        |
| Números enteros y decimales | Secuencia de numeros que pueden tener punto decimal                                   | [0-9]+(\.[0-9])*            | 2, 2.2, 3.333 | TK_double_v      |

**Análisis Sintáctico**

El analizador sintáctico sigue la siguiente grámatica
[Gramática](grammar.txt)
_____

**Funciónes Aritmeticas**

Para la realización de operaciones se hace uso de lo siguiente:

```java
private double evaluateArith(ArithmeticExp data) {
    double resultado = 0.0f;
    VariableValue v1 = (VariableValue) data.getV1();
    VariableValue v2 = (VariableValue) data.getV2();
    String op = data.getOp();
    double operando1, operando2;
    if (v1.getType() == TypeVariable.AR) {
        ArithmeticExp temp = (ArithmeticExp) v1.getValue();
        operando1 = evaluateArith(temp);
    } else if (v1.getType() == TypeVariable.ID) {
        String temp = (String) v1.getValue();
        Information info = (Information) table.get(temp);
        Object resp = info.getValue();
        operando1 = (double) resp;
    } else if (v1.getType() == TypeVariable.ST) {
        StatisticalExp e = (StatisticalExp) v1.getValue();
        operando1 = evaluateStats(e);
    } else {
        operando1 = (double) v1.getValue();
    }
    if (v2.getType() == TypeVariable.AR) {
        ArithmeticExp temp = (ArithmeticExp) v2.getValue();
        operando2 = evaluateArith(temp);
    } else if (v2.getType() == TypeVariable.ID) {
        String temp = (String) v2.getValue();
        Information info = (Information) table.get(temp);
        Object resp = info.getValue();
        operando2 = 0;
        operando2 = (double) resp;
    } else if (v2.getType() == TypeVariable.ST) {
        StatisticalExp e = (StatisticalExp) v2.getValue();
        operando2 = evaluateStats(e);
    } else {
        operando2 = (double) v2.getValue();
    }

    switch (op) {
        case "/":
            resultado = operando1 / operando2;
            break;
        case "*":
            resultado = operando1 * operando2;
            break;
        case "%":
            resultado = operando1 % operando2;
            break;
        case "+":
            resultado = operando1 + operando2;
            break;
        case "-":
            resultado = operando1 - operando2;
            break;
        default:
            resultado = 0;
    }

    return resultado;
}
```

Lo primero que se realiza es buscar los valroes de los operandos, ya que pueden ser, otras operaciones, numeros, expresiones estadisticas, o variables, en el caso de encontrar otra expresion aritmetica entra en un recursión. Lueogo con el switch case se procede a verificar que operación se debe realizar, y eso lo guarda en la variable resultado.

Operaciones disponibles:
* Suma
* Resta
* Multiplicación
* División
* Módulo

**Funciónes Estadisticas**

Para la realización de funciones estadisticas es importante conocer que solo permite arreglos como parametros y se usa la siguiente función:

```java
private double evaluateStats(StatisticalExp data) {
        String op = data.getType_s();
        ArrayList<Double> arrayListDeDoubles = new ArrayList<>();

        if (data.getValues() instanceof String) {
            String temp = (String) data.getValues();
            Information info = (Information) table.get(temp);
            arrayListDeDoubles = (ArrayList<Double>) info.getValue();
        } else {
            VariableValue v = (VariableValue) data.getValues();
            ArrayList<VariableValue> array = (ArrayList<VariableValue>) v.getValue();

            for (VariableValue variableValue : array) {
                if (variableValue.getType() == TypeVariable.DOUBLE) {
                    arrayListDeDoubles.add((Double) variableValue.getValue());
                } else if (variableValue.getType() == TypeVariable.AR) {
                    ArithmeticExp temp = (ArithmeticExp) variableValue.getValue();
                    arrayListDeDoubles.add(evaluateArith(temp));
                } else if (variableValue.getType() == TypeVariable.ID) {
                    String id = (String) variableValue.getValue();
                    Information info = (Information) table.get(id);
                    Object resp = info.getValue();
                    if (resp instanceof Double) {
                        arrayListDeDoubles.add((double) resp);
                    }
                }
            }
        }

        return operate(arrayListDeDoubles, op);
    }
```

En este caso se crea un array de tipo double donde se guardan los valores recibidos, para luego validar el tipo de variable con la que se esta trabajando, para luego llamar a operate y recibir el valor del resultado. La función operate es la siguiente:

```java
public double operate(ArrayList<Double> list, String op) {
    double resultado = 0.0f;
    switch (op) {
        case "Media":
            resultado = Statistics.Mean(list);
            break;
        case "Mediana":
            resultado = Statistics.Median(list);
            break;
        case "Moda":
            resultado = Statistics.Mode(list);
            break;
        case "Varianza":
            resultado = Statistics.Variance(list);
            break;
        case "Max":
            resultado = Statistics.Maximum(list);
            break;
        case "Min":
            resultado = Statistics.Minimum(list);
            break;
        default:
            throw new AssertionError();
    }
    return resultado;
}
```

Esta función se encarga de llamar a las funciones correctas de la clase Statistics, la cual calcula las diferentes operaciones disponibles.

Operaciones Disponibles:
* Media
* Mediana
* Moda
* Varianza
* Max
* Min