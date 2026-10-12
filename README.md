CONVERSOR NUMERICO

Aplicación de escritorio en Java y JavaFX que convierte cantidades entre los sistemas binario, octal, decimal y hexadecimal. El usuario escribe en cualquiera de los cuatro campos, presiona ENTER y obtiene las equivalencias en los otros tres.

CARACTERISTICAS
Cuatro campos simultáneos (Binario, Octal, Decimal, Hexadecimal); cualquiera puede ser el origen de la conversión.
Validación mientras se escribe: un carácter que no pertenece al sistema del campo no llega a mostrarse.
Acepta letras minúsculas en hexadecimal (ff equivale a FF).
Botón LIMPIAR que vacía los cuatro campos.
Tooltips de ayuda con los caracteres permitidos en cada campo.
Mensajes de error en pantalla para campos vacíos y valores demasiado grandes, sin excepciones sin controlar.
Algoritmos de conversión propios: no se usan Integer.parseInt, Long.toBinaryString ni funciones nativas similares.

REQUISITOS:
JDK 25
Maven (o Apache NetBeans, que lo incluye)

Cómo ejecutarlo:
Desde la terminal, en la carpeta del proyecto:

mvn javafx:run

Desde Apache NetBeans: clic derecho en el proyecto → Run Maven → Goals… → escribir javafx:run.
